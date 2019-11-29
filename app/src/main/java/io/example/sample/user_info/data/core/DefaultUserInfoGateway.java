package io.example.sample.user_info.data.core;

import androidx.annotation.NonNull;

import java.util.Date;

import io.example.sample.base.AppException;
import io.example.sample.base.Response;
import io.example.sample.base.ServerException;
import io.example.sample.user_info.data.core.model.UserInfoDataModel;
import io.example.sample.user_info.domain.UserInfoGateway;
import io.example.sample.user_info.domain.model.UserInfoModel;

public class DefaultUserInfoGateway implements UserInfoGateway {

    @NonNull
    @Override
    public Response<UserInfoModel> getUserInfo(long id) {
        UserInfoDataModel dataModel;
        UserInfoModel.SubscriptionStatus subscriptionStatus;
        try {
            dataModel = loadUser(id);
        } catch (AppException exception) {
            return new Response.Failure<>(exception);
        }
        try {
            subscriptionStatus = convertStatus(dataModel.status);
        } catch (IllegalStateException exception) {
            return new Response.Failure<>(new ServerException());
        }
        return new Response.Success<>(
                new UserInfoModel(
                        dataModel.id,
                        dataModel.name,
                        subscriptionStatus,
                        convertToDate(dataModel.time),
                        dataModel.description
                )
        );
    }

    @NonNull
    @Override
    public Response<UserInfoModel.SubscriptionStatus> setStatus(long userId,
                                                                @NonNull UserInfoModel.SubscriptionStatus status) {
        return new Response.Success<>(status);
    }


    @NonNull
    private UserInfoDataModel loadUser(long id) throws AppException {
        UserInfoDataModel model = new UserInfoDataModel();
        model.id = id;
        model.name = "Test";
        model.time = "";
        model.status = 1;
        model.description = "";

        return model;
    }

    @NonNull
    private Date convertToDate(@NonNull String date) {
        return new Date();
    }

    @NonNull
    private UserInfoModel.SubscriptionStatus convertStatus(int status) throws IllegalStateException {
        switch (status) {
        case 0:
            return UserInfoModel.SubscriptionStatus.NOT_SUBSCRIBED;
        case 1:
            return UserInfoModel.SubscriptionStatus.SUBSCRIBED;
        default:
            throw new IllegalStateException("Incorrect status");
        }
    }
}
