package io.example.sample.user_info.domain.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class UserInfoModel {

    public enum SubscriptionStatus {
        NOT_SUBSCRIBED, SUBSCRIBED
    }

    private long id;
    @NonNull
    private String name;
    @NonNull
    private Date creationTime;
    @NonNull
    private SubscriptionStatus subscriptionStatus;
    @NonNull
    private String description;

    public UserInfoModel(long id,
                         @NonNull String name,
                         @NonNull SubscriptionStatus subscriptionStatus,
                         @NonNull Date creationTime,
                         @NonNull String description) {
        this.id = id;
        this.name = name;
        this.subscriptionStatus = subscriptionStatus;
        this.creationTime = creationTime;
        this.description = description;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public Date getCreationTime() {
        return creationTime;
    }

    @NonNull
    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    @NonNull
    public String getDescription() {
        return description;
    }
}
