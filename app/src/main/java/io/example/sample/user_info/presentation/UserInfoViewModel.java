package io.example.sample.user_info.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.example.sample.base.AppException;
import io.example.sample.base.AuthException;
import io.example.sample.base.Mapper;
import io.example.sample.base.Response;
import io.example.sample.base.ServerException;
import io.example.sample.user_info.domain.UserInfoGateway;
import io.example.sample.user_info.domain.model.UserInfoModel;
import io.example.sample.user_info.presentation.model.ErrorData;
import io.example.sample.user_info.presentation.model.UserInfoUIModel;

public class UserInfoViewModel extends ViewModel {

    @NonNull
    private UserInfoGateway userInfoGateway;
    @Nullable
    private UserInfoModel userInfoModel;
    @NonNull
    private Mapper<UserInfoModel, UserInfoUIModel> userModelToUiMapper;

    @NonNull
    private MutableLiveData<UserInfoUIModel> userInfoLiveData = new MutableLiveData<>();
    @NonNull
    private MutableLiveData<ErrorData> errorLiveData = new MutableLiveData<>();

    public UserInfoViewModel(@NonNull UserInfoGateway userInfoGateway,
                             @NonNull Mapper<UserInfoModel, UserInfoUIModel> userModelToUiMapper) {
        this.userInfoGateway = userInfoGateway;
        this.userModelToUiMapper = userModelToUiMapper;
        startUserLoading(1L);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @NonNull
    public LiveData<UserInfoUIModel> getUserInfoLiveData() {
        return userInfoLiveData;
    }

    @NonNull
    public LiveData<ErrorData> getErrorLiveData() {
        return errorLiveData;
    }

    public void onChangeSubscriptionStatusClicked() {
        UserInfoModel model = userInfoModel;
        if (model == null) {
            return;
        }
        switchStatus(model);
    }

    //TODO BL
    private void startUserLoading(long userId) {
        Response<UserInfoModel> response = this.userInfoGateway.getUserInfo(userId);
        onUserInfoLoaded(response);
    }

    //TODO BL
    private void switchStatus(@NonNull UserInfoModel user) {
        UserInfoModel.SubscriptionStatus status;
        if (user.getSubscriptionStatus() == UserInfoModel.SubscriptionStatus.NOT_SUBSCRIBED) {
            status = UserInfoModel.SubscriptionStatus.SUBSCRIBED;
        } else {
            status = UserInfoModel.SubscriptionStatus.NOT_SUBSCRIBED;
        }
        Response<UserInfoModel.SubscriptionStatus> response = userInfoGateway.setStatus(user.getId(), status);
        if (!response.isSuccess()) {
            AppException error = ((Response.Failure)response).getException();
            onStatusSwitched(new Response.Failure<>(error));
            return;
        }
        UserInfoModel changedUser = new UserInfoModel(user.getId(), user.getName(), status, user.getCreationTime(), user.getDescription());
        onStatusSwitched(new Response.Success<>(changedUser));
    }


    private void onUserInfoLoaded(@NonNull Response<UserInfoModel> response) {
        if (!response.isSuccess()) {
            showError(((Response.Failure)response).getException());
            return;
        }
        onUserInfoSuccessLoaded(((Response.Success<UserInfoModel>)response).getData());
    }

    private void showError(@NonNull AppException exception) {
        if (exception instanceof ServerException) {
            errorLiveData.postValue(new ErrorData("Server error"));
        } else if (exception instanceof AuthException) {
            errorLiveData.postValue(new ErrorData("Authorization error"));
        }
    }

    private void onUserInfoSuccessLoaded(@NonNull UserInfoModel userInfo) {
        this.userInfoModel = userInfo;
        userInfoLiveData.postValue(userModelToUiMapper.map(userInfo));
    }

    private void onStatusSwitched(@NonNull Response<UserInfoModel> response) {
        if (!response.isSuccess()) {
            showError(((Response.Failure)response).getException());
            return;
        }
        onUserInfoSuccessLoaded(((Response.Success<UserInfoModel>)response).getData());
    }
}
