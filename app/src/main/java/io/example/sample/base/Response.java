package io.example.sample.base;

import androidx.annotation.NonNull;

public abstract class Response<T> {

    private boolean isSuccess;

    private Response(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public static class Success<T> extends Response<T> {
        @NonNull
        private T data;

        public Success(@NonNull T data) {
            super(true);
            this.data = data;
        }

        @NonNull
        public T getData() {
            return data;
        }
    }

    public static class Failure<T> extends Response<T> {
        @NonNull
        private AppException exception;

        public Failure(@NonNull AppException exception) {
            super(false);
            this.exception = exception;
        }

        @NonNull
        public AppException getException() {
            return exception;
        }
    }
}
