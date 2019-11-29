package io.example.sample.user_info.presentation.model;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

public class StatusButton {
    @NonNull
    private String description;
    @ColorRes
    private int color;

    public StatusButton(@NonNull String description, @ColorRes int color) {
        this.description = description;
        this.color = color;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @ColorRes
    public int getColor() {
        return color;
    }
}

