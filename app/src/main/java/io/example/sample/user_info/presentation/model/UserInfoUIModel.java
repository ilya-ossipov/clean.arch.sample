package io.example.sample.user_info.presentation.model;

import androidx.annotation.NonNull;

public class UserInfoUIModel {

    @NonNull
    private String title;
    @NonNull
    private StatusButton buttonStatus;
    @NonNull
    private String description;

    public UserInfoUIModel(@NonNull String title,
                           @NonNull StatusButton buttonStatus,
                           @NonNull String description) {
        this.title = title;
        this.buttonStatus = buttonStatus;
        this.description = description;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public StatusButton getButtonStatus() {
        return buttonStatus;
    }

    @NonNull
    public String getDescription() {
        return description;
    }
}
