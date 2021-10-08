package com.sofiamarchinskaya.moretechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ComeInActivity extends AppCompatActivity implements ComeInPresenter.ViewComeIn {
    private View backButton;
    private TextView bottomText;
    private TextView error;
    private EditText email;
    private EditText password;
    private Button auth;
    private ComeInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_come_in);
        presenter = new ComeInPresenter(this);
        backButton = findViewById(R.id.back_button);
        bottomText = findViewById(R.id.bottom_text);
        email = findViewById(R.id.mail_edit);
        password = findViewById(R.id.password_edit);
        auth = findViewById(R.id.auth_button);
        error = findViewById(R.id.error);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(ComeInActivity.this, FirstRegisterActivity.class);
            startActivity(intent);
            finish();
        });
        auth.setOnClickListener(v -> {
            presenter.authorize(email.getText().toString(), password.getText().toString());
        });
        bottomText.setOnClickListener(v -> {
            Intent intent = new Intent(ComeInActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
        Spannable span = new SpannableString(getString(R.string.register_if_havent));
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#005EFF")), 14, 31,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bottomText.setText(span);
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(ComeInActivity.this, Homepage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailed() {
        //TODO error.setVisibility(View.VISIBLE);
        Intent intent = new Intent(ComeInActivity.this, Homepage.class);
        startActivity(intent);
        finish();
    }
}