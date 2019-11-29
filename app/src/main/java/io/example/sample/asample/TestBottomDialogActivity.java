package io.example.sample.asample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.example.sample.R;
import io.example.sample.user_info.presentation.screen.UserInfoBottomFragment;

public class TestBottomDialogActivity extends AppCompatActivity {
    private static String USER_INFO_FRAGMENT_TAG = "tag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bottom_dialog);
        findViewById(R.id.show_user).setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(new UserInfoBottomFragment(), USER_INFO_FRAGMENT_TAG)
                    .commit();
        });
    }
}
