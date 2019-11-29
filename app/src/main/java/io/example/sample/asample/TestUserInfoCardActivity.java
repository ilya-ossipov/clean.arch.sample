package io.example.sample.asample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import io.example.sample.R;
import io.example.sample.user_info.presentation.UserInfoViewModel;
import io.example.sample.user_info.presentation.UserInfoViewModelFactory;
import io.example.sample.user_info.presentation.card_view.UserInfoCardView;

public class TestUserInfoCardActivity extends AppCompatActivity {

    @NonNull
    private UserInfoCardView userInfoCardView;
    @NonNull
    private UserInfoViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_card);
        userInfoCardView = findViewById(R.id.user_info_card_view);
        initViewModel();
    }

    private void initViewModel() {
        userViewModel = ViewModelProviders.of(this, new UserInfoViewModelFactory(UserInfoViewModelFactory.Type.CARD))
                .get(UserInfoViewModel.class);
        userInfoCardView.setOnClickListener(userViewModel::onChangeSubscriptionStatusClicked);
        userViewModel.getErrorLiveData().observe(this, errorData -> {
            if (errorData == null) return;
            Toast.makeText(this, errorData.getMessage(), Toast.LENGTH_LONG).show();
        });
        userViewModel.getUserInfoLiveData().observe(this, model -> {
            if (model == null) return;
            userInfoCardView.setupModel(model);
        });
    }
}
