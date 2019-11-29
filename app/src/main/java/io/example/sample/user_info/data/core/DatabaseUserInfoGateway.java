package io.example.sample.user_info.data.core;

import androidx.annotation.NonNull;

import io.example.sample.base.Response;
import io.example.sample.user_info.domain.UserInfoGateway;
import io.example.sample.user_info.domain.model.UserInfoModel;

public class DatabaseUserInfoGateway implements UserInfoGateway {

    @NonNull
    @Override
    public Response<UserInfoModel> getUserInfo(long id) {
        // TODO Load data from database
        return null;
    }

    @NonNull
    @Override
    public Response<UserInfoModel.SubscriptionStatus> setStatus(
            long userId,
            @NonNull UserInfoModel.SubscriptionStatus status
    ) {
        return null;
    }
}
