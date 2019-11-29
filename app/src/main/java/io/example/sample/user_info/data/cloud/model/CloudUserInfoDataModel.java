package io.example.sample.user_info.data.cloud.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CloudUserInfoDataModel {

    @JsonProperty("user_id")
    public long id;
    @JsonProperty("uname")
    @NonNull
    public String name;
    @JsonProperty("subscription_status")
    public boolean status;
    @JsonProperty("creation")
    public long time;
    @JsonProperty("description")
    @NonNull
    public String description;
}
