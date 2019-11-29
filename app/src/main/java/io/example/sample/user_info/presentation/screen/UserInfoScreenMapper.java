package io.example.sample.user_info.presentation.screen;

import androidx.annotation.NonNull;

import java.util.Date;

import io.example.sample.R;
import io.example.sample.base.Mapper;
import io.example.sample.user_info.domain.model.UserInfoModel;
import io.example.sample.user_info.presentation.model.StatusButton;
import io.example.sample.user_info.presentation.model.UserInfoUIModel;

public class UserInfoScreenMapper implements Mapper<UserInfoModel, UserInfoUIModel> {

    private static long ONE_DAY = 1000 * 60 * 60 * 24;

    @NonNull
    @Override
    public UserInfoUIModel map(@NonNull UserInfoModel object) {
        String title = getTitle(object.getName(), object.getCreationTime());
        StatusButton status = getButtonStatus(object.getSubscriptionStatus());
        return new UserInfoUIModel(title, status, object.getDescription());
    }

    @NonNull
    private String getTitle(@NonNull String name, @NonNull Date time) {
        String timeStatus = getTimeStatus(time);
        return name + ", " + timeStatus;
    }

    @NonNull
    private String getTimeStatus(@NonNull Date date) {
        if (Math.abs(date.getTime() - System.currentTimeMillis()) < ONE_DAY) {
            return "Yesterday";
        } else {
            return "Format date";
        }
    }

    @NonNull
    private StatusButton getButtonStatus(@NonNull UserInfoModel.SubscriptionStatus status) {
        int color = R.color.colorAccent;
        String desc = "Subscribe";
        if (status == UserInfoModel.SubscriptionStatus.SUBSCRIBED) {
            color = R.color.appWhite;
            desc = "Unsubscribe";
        }

        return new StatusButton(desc, color);
    }
}
