package io.example.sample.user_info.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.example.sample.base.Mapper;
import io.example.sample.user_info.data.cloud.CloudUserInfoGateway;
import io.example.sample.user_info.data.core.DefaultUserInfoGateway;
import io.example.sample.user_info.domain.UserInfoGateway;
import io.example.sample.user_info.domain.model.UserInfoModel;
import io.example.sample.user_info.presentation.card_view.UserInfoCardViewMapper;
import io.example.sample.user_info.presentation.model.UserInfoUIModel;
import io.example.sample.user_info.presentation.screen.UserInfoScreenMapper;

public class UserInfoViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private Type type;

    public enum Type {
        SCREEN, CARD
    }

    public UserInfoViewModelFactory() {
        this.type = Type.SCREEN;
    }

    public UserInfoViewModelFactory(@NonNull Type type) {
        this.type = type;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserInfoViewModel(getUserInfo(), getMapperByType());
    }

    @NonNull
    private UserInfoGateway getUserInfo() {
        if (type != Type.CARD) {
            return new DefaultUserInfoGateway();
        }
        return new CloudUserInfoGateway();
    }

    @NonNull
    private Mapper<UserInfoModel, UserInfoUIModel> getMapperByType() {
        if (type != Type.CARD) {
            return new UserInfoScreenMapper();
        }
        return new UserInfoCardViewMapper();
    }
}