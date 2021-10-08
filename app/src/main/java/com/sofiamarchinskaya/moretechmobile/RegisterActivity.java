package com.sofiamarchinskaya.moretechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private View backButton;
    private TextView bottomText;
    private EditText mailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backButton = findViewById(R.id.back_button);
        bottomText = findViewById(R.id.bottom_text);
        mailEditText = findViewById(R.id.mail_edit);
        passwordEditText=findViewById(R.id.password_edit);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, FirstRegisterActivity.class);
            startActivity(intent);
            finish();
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
}