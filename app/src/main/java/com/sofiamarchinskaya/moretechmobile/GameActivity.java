package com.sofiamarchinskaya.moretechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private TextView topTextView;
    private List<View> topDots;
    private View dot;
    private LinearLayout dotsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        topTextView = findViewById(R.id.top_text);
        topTextView.setText("Шаг  "+preferences.getString(Constant.YEAR,"1")+" из 10");
        dotsLayout = findViewById(R.id.dots_layout);
        topDots = new ArrayList<>();

        for(int i = 0; i<10; i++){

            dot=new View(this);
            dot.setBackgroundResource(R.drawable.ic_circle);
            dot.getLayoutParams().height=9;
            dot.getLayoutParams().width=9;
            topDots.add(dot);
            dotsLayout.addView(dot);
        }



    }
}