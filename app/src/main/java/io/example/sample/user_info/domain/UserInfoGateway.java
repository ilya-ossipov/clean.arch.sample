package io.example.sample.user_info.domain;

import androidx.annotation.NonNull;

import io.example.sample.base.Response;
import io.example.sample.user_info.domain.model.UserInfoModel;

public interface UserInfoGateway {

    @NonNull
    Response<UserInfoModel> getUserInfo(long id);

    @NonNull
    Response<UserInfoModel.SubscriptionStatus> setStatus(long userId, @NonNull UserInfoModel.SubscriptionStatus status);
}



