package io.example.sample.user_info.data.core.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoDataModel {

    @JsonProperty("id")
    public long id;
    @JsonProperty("name")
    @NonNull
    public String name;
    @JsonProperty("ustatus")
    public int status;
    @JsonProperty("creationTime")
    public String time;
    @JsonProperty("desc")
    @NonNull
    public String description;
}
