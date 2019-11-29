package io.example.sample.user_info.presentation.card_view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.example.sample.user_info.presentation.model.UserInfoUIModel;

public interface UserInfoCardView {

    void setupModel(@NonNull UserInfoUIModel model);

    void setOnClickListener(@Nullable OnClickListener listener);

    interface OnClickListener {

        void onSubscribeClicked();
    }
}
