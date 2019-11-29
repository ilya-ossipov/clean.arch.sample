package io.example.sample.base;

import androidx.annotation.NonNull;

public interface Mapper<F, T> {

    @NonNull
    T map(@NonNull F object);
}
