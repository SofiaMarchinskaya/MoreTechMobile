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

public class RegisterActivity extends AppCompatActivity implements RegisterPresenter.ViewRegister{
    private View backButton;
    private TextView bottomText;
    private EditText mailEditText;
    private EditText passwordEditText;
    private EditText nickEditText;
    private Button register;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backButton = findViewById(R.id.back_button);
        bottomText = findViewById(R.id.bottom_text);
        mailEditText = findViewById(R.id.mail_edit);
        passwordEditText=findViewById(R.id.password_edit);
        register = findViewById(R.id.reg_button);
        nickEditText = findViewById(R.id.nick_edit);
        presenter = new RegisterPresenter(this);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, FirstRegisterActivity.class);
            startActivity(intent);
            finish();
        });
        register.setOnClickListener(v -> {
            presenter.register(mailEditText.getText().toString(),
                    nickEditText.getText().toString(), passwordEditText.getText().toString());
        });
        bottomText.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, ComeInActivity.class);
            startActivity(intent);
            finish();
        });

        Spannable span = new SpannableString(getString(R.string.have_acc));
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#005EFF")), 18, 25,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bottomText.setText(span);


    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(RegisterActivity.this, Homepage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailed() {

    }
}