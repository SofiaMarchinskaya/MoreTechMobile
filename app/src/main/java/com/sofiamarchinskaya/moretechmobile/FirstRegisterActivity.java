package com.sofiamarchinskaya.moretechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FirstRegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private Button comeInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_register);

        registerButton = findViewById(R.id.reg_button);
        comeInButton = findViewById(R.id.bottom_button);

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(FirstRegisterActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        comeInButton.setOnClickListener(view -> {
            Intent intent = new Intent(FirstRegisterActivity.this, ComeInActivity.class);
            startActivity(intent);
        });
    }
}