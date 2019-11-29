package io.example.sample.user_info.data.cloud;

import androidx.annotation.NonNull;

import java.util.Date;

import io.example.sample.base.Response;
import io.example.sample.user_info.domain.UserInfoGateway;
import io.example.sample.user_info.domain.model.UserInfoModel;

public class CloudUserInfoGateway implements UserInfoGateway {

    @NonNull
    @Override
    public Response<UserInfoModel> getUserInfo(long id) {
        return new Response.Success<>(
                new UserInfoModel(
                        id,
                        "Example",
                        UserInfoModel.SubscriptionStatus.NOT_SUBSCRIBED,
                        new Date(),
                        ""
                )
        );
    }

    @NonNull
    @Override
    public Response<UserInfoModel.SubscriptionStatus> setStatus(long userId, @NonNull UserInfoModel.SubscriptionStatus status) {
        return new Response.Success<>(status);
    }
}
