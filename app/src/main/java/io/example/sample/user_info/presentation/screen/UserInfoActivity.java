package io.example.sample.user_info.presentation.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import io.example.sample.R;
import io.example.sample.user_info.presentation.UserInfoViewModel;
import io.example.sample.user_info.presentation.UserInfoViewModelFactory;
import io.example.sample.user_info.presentation.model.StatusButton;

public class UserInfoActivity extends AppCompatActivity {

    @NonNull
    private UserInfoViewModel userViewModel;
    @NonNull
    private TextView titleTextView;
    @NonNull
    private TextView descriptionsTextView;
    @NonNull
    private TextView subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTextView = findViewById(R.id.title);
        descriptionsTextView = findViewById(R.id.description);
        subscribeButton = findViewById(R.id.subscribe);
        initViewModel();
    }

    private void initViewModel() {
        userViewModel = ViewModelProviders.of(this, new UserInfoViewModelFactory())
                .get(UserInfoViewModel.class);
        userViewModel.getErrorLiveData().observe(this, errorData -> {
            if (errorData == null) return;
            Toast.makeText(this, errorData.getMessage(), Toast.LENGTH_LONG).show();
        });
        userViewModel.getUserInfoLiveData().observe(this, model -> {
            if (model == null) return;
            titleTextView.setText(model.getTitle());
            descriptionsTextView.setText(model.getDescription());
            setupSubscribeButton(model.getButtonStatus());
            subscribeButton.setOnClickListener(v -> userViewModel.onChangeSubscriptionStatusClicked());
        });
    }

    private void setupSubscribeButton(@NonNull StatusButton status) {
        subscribeButton.setText(status.getDescription());
        subscribeButton.setBackgroundColor(ContextCompat.getColor(this, status.getColor()));
    }
}
