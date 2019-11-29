package io.example.sample.user_info.presentation.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import io.example.sample.R;
import io.example.sample.user_info.presentation.UserInfoViewModel;
import io.example.sample.user_info.presentation.UserInfoViewModelFactory;
import io.example.sample.user_info.presentation.model.StatusButton;

public class UserInfoBottomFragment extends BottomSheetDialogFragment {

    @NonNull
    private UserInfoViewModel userViewModel;

    @NonNull
    private TextView titleTextView;
    @NonNull
    private Button subscribeStatusButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_info_bottom_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleTextView = view.findViewById(R.id.user_info_title_text_view);
        subscribeStatusButton = view.findViewById(R.id.subscribe_status_button);
        initViewModel();
    }

    private void initViewModel() {
        userViewModel = ViewModelProviders.of(this, new UserInfoViewModelFactory())
                .get(UserInfoViewModel.class);
        userViewModel.getErrorLiveData().observe(this, errorData -> {
            if (errorData == null)  {
                return;
            }
            Toast.makeText(getContext(), errorData.getMessage(), Toast.LENGTH_LONG).show();
        });
        userViewModel.getUserInfoLiveData().observe(this, model -> {
            if (model == null) {
                return;
            }
            titleTextView.setText(model.getTitle());
            setupSubscribeButton(model.getButtonStatus());
            subscribeStatusButton.setOnClickListener(v -> userViewModel.onChangeSubscriptionStatusClicked());
        });
    }

    private void setupSubscribeButton(@NonNull StatusButton status) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        subscribeStatusButton.setText(status.getDescription());
        subscribeStatusButton.setBackgroundColor(ContextCompat.getColor(context, status.getColor()));
    }
}
