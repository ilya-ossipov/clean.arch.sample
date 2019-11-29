package io.example.sample.user_info.presentation.model;

import androidx.annotation.NonNull;

public class ErrorData {
    @NonNull
    private String message;

    public ErrorData(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public String getMessage() {
        return message;
    }
}
