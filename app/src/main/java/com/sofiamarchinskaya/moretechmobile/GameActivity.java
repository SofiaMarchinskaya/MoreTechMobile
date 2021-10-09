package com.sofiamarchinskaya.moretechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
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
    private TextView investedText;
    private TextView budgetText;
    private TextView happyText;
    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        topTextView = findViewById(R.id.top_text);
        topTextView.setText("Шаг  "+preferences.getString(Constant.YEAR,"1")+" из 10");
        dotsLayout = findViewById(R.id.dots_layout);
        investedText = findViewById(R.id.invested_money);
        investedText.setText(preferences.getInt(Constant.DEPOSIT,0)+"");

        budgetText = findViewById(R.id.budget);
        happyText = findViewById(R.id.percent_happy);

        budgetText.setText(preferences.getInt(Constant.TOTAL_MONEY,Constant.START_MONEY)+"");
        happyText.setText(preferences.getInt(Constant.HAPPY,Constant.START_HAPPY)+"");
        topDots = new ArrayList<>();
        int dip = 9;
        int margin = 6;
        //перевод в dp
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,  getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                (int)px, (int)px);
        float m_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin,  getResources().getDisplayMetrics());

       for(int i = 0; i<10; i++){

            dot=new View(this);
            dot.setBackgroundResource(R.drawable.ic_circle);
           lParams.setMargins(0,0,(int)m_px,0);
          dot.setLayoutParams(lParams);
            topDots.add(dot);
            dotsLayout.addView(dot,lParams);
        }



    }
}