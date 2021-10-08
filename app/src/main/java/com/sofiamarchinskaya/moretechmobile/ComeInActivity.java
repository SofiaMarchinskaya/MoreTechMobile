package com.sofiamarchinskaya.moretechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class ComeInActivity extends AppCompatActivity {
    private View backButton;
    private TextView bottomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_come_in);
        backButton = findViewById(R.id.back_button);
        bottomText = findViewById(R.id.bottom_text);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(ComeInActivity.this, FirstRegisterActivity.class);
            startActivity(intent);
            finish();
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
}