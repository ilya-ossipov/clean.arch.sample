package io.example.sample.user_info.presentation.card_view;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;

import io.example.sample.R;
import io.example.sample.base.Mapper;
import io.example.sample.user_info.domain.model.UserInfoModel;
import io.example.sample.user_info.presentation.model.StatusButton;
import io.example.sample.user_info.presentation.model.UserInfoUIModel;

public class UserInfoCardViewMapper implements Mapper<UserInfoModel, UserInfoUIModel> {


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
        return "mr. " + name + ", " + timeStatus;
    }

    @NonNull
    private String getTimeStatus(@NonNull Date date) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(date);
    }

    @NonNull
    private StatusButton getButtonStatus(@NonNull UserInfoModel.SubscriptionStatus status) {
        @ColorRes int color = R.color.appGreen;
        String desc = "+";
        if (status == UserInfoModel.SubscriptionStatus.SUBSCRIBED) {
            color = R.color.appRed;
            desc = "-";
        }

        return new StatusButton(desc, color);
    }
}
