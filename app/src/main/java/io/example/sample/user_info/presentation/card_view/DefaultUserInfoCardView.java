package io.example.sample.user_info.presentation.card_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import io.example.sample.R;
import io.example.sample.user_info.presentation.model.StatusButton;
import io.example.sample.user_info.presentation.model.UserInfoUIModel;

public class DefaultUserInfoCardView extends CardView implements UserInfoCardView, View.OnClickListener {

    @NonNull
    private TextView titleTextView;
    @NonNull
    private Button statusButton;
    @Nullable
    private UserInfoCardView.OnClickListener listener;

    public DefaultUserInfoCardView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DefaultUserInfoCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DefaultUserInfoCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        listener.onSubscribeClicked();
    }

    @Override
    public void setupModel(@NonNull UserInfoUIModel model) {
        titleTextView.setText(model.getTitle());
        setupSubscribeButton(model.getButtonStatus());
    }

    @Override
    public void setOnClickListener(@Nullable UserInfoCardView.OnClickListener listener) {
        this.listener = listener;
    }

    private void init(final @NonNull Context context) {
        View innerView = LayoutInflater.from(context).inflate(R.layout.layout_user_info_card_view, this, true);
        titleTextView = innerView.findViewById(R.id.title_text_view);
        statusButton = innerView.findViewById(R.id.subscribe_status_button);
        statusButton.setOnClickListener(this);
    }

    private void setupSubscribeButton(@NonNull StatusButton status) {
        statusButton.setText(status.getDescription());
        statusButton.setBackgroundColor(ContextCompat.getColor(getContext(), status.getColor()));
    }
}
