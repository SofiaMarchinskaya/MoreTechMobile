package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sofiamarchinskaya.moretechmobile.models.Company;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements GamePresenter.ViewActivity {
    private TextView topTextView;
    private List<View> topDots;
    private View dot;
    private LinearLayout dotsLayout;
    private TextView investedText;
    private TextView budgetText;
    private TextView happyText;
    private FragmentAdapter adapter;
    private ViewPager2 pager;
    private TabLayoutMediator tabLayoutMediator;
    private BottomSheetBehavior bottomSheetBehaviour;
    private View close;
    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.budget:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.investFragment, new BudgetFragment());
                    ft.commit();
                    return true;
                case R.id.invest:
                    FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.investFragment,  new InvestFragment());
                    ft1.commit();
                    return true;

            }

            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        topTextView = findViewById(R.id.top_text);
        topTextView.setText("Шаг  " + preferences.getString(Constant.YEAR, "1") + " из 10");
        dotsLayout = findViewById(R.id.dots_layout);
        investedText = findViewById(R.id.invested_money);
        investedText.setText(preferences.getInt(Constant.DEPOSIT, 0) + "");
        close = findViewById(R.id.close);
        budgetText = findViewById(R.id.budget);
        happyText = findViewById(R.id.percent_happy);

        budgetText.setText(preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY) + "");
        happyText.setText(preferences.getInt(Constant.HAPPY, Constant.START_HAPPY) + "");
        topDots = new ArrayList<>();
        int dip = 9;
        int margin = 6;
        //перевод в dp
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                (int) px, (int) px);
        float m_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin,
                getResources().getDisplayMetrics());

        for (int i = 0; i < 10; i++) {

            dot = new View(this);
            dot.setBackgroundResource(R.drawable.ic_circle);
            lParams.setMargins(0, 0, (int) m_px, 0);
            dot.setLayoutParams(lParams);
            topDots.add(dot);
            dotsLayout.addView(dot, lParams);
        }
        pager = (ViewPager2) findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        String [] names = new String[]{"Годовые данные", "О компании"};
        tabLayoutMediator = new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> {
            tab.setText(names[position]);

        });
        bottomSheetBehaviour = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_COLLAPSED){
                    close();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        close.setOnClickListener(v -> {
            close();
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.investFragment, new BudgetFragment());
        ft.commit();
    }
    public void close(){
        bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_HIDDEN);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        investedText.setText(preferences.getInt(Constant.DEPOSIT, 0) + "");
        budgetText.setText(preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY) + "");
        tabLayoutMediator.detach();
    }

    public void update(){
        bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_HIDDEN);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        budgetText.setText(preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY) + "");
        happyText.setText( preferences.getInt(Constant.HAPPY, Constant.START_HAPPY)+"%");
        tabLayoutMediator.detach();
    }

    @Override
    public void showBottomSheet(Company company, int[] dots) {
        if (tabLayoutMediator.isAttached()) return;
        adapter = new FragmentAdapter(this);
        adapter.setData(company, dots);
        pager.setAdapter(adapter);
        tabLayoutMediator.attach();
        bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void updateData() {

    }
}