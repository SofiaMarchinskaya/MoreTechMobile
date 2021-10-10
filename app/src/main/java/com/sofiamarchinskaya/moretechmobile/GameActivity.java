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

import java.time.Year;
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
    private BottomNavigationView navigation;
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
                case R.id.news:
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.investFragment,  new NewsFragment());
                    ft2.commit();
                    return true;
                case R.id.news1:
                    FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                    ft4.replace(R.id.investFragment,  new SecondNewsFragment());
                    ft4.commit();
                    return true;
                case R.id.dividenti:
                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.investFragment,  new DividentsFragment());
                    ft3.commit();
                    return true;
                case R.id.stock_market:
                    FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                    ft5.replace(R.id.investFragment,  new StockMarketFragment());
                    ft5.commit();
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
        topTextView.setText("Шаг  " + (preferences.getInt(Constant.YEAR, 0) +1)+ " из 10");
        dotsLayout = findViewById(R.id.dots_layout);
        investedText = findViewById(R.id.invested_money);
        investedText.setText(preferences.getInt(Constant.DEPOSIT, 0) + " ア");
        close = findViewById(R.id.close);
        budgetText = findViewById(R.id.budget);
        happyText = findViewById(R.id.percent_happy);

        budgetText.setText(preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY) + " ア");
        happyText.setText(preferences.getInt(Constant.HAPPY, Constant.START_HAPPY) + " %");
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
        for (int i = 0; i < preferences.getInt(Constant.YEAR, 0); i++) {
            dot = new View(this);
            dot.setBackgroundResource(R.drawable.ic_active_circle);
            lParams.setMargins(0, 0, (int) m_px, 0);
            dot.setLayoutParams(lParams);
            topDots.add(dot);
            dotsLayout.addView(dot, lParams);
        }
        LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(
                (int) px*2, (int) px);
        dot = new View(this);
        dot.setBackgroundResource(R.drawable.ic_current_year);
        lParams1.setMargins((int)m_px, 0, (int) m_px, 0);
        dot.setLayoutParams(lParams1);
        topDots.add(dot);
        dotsLayout.addView(dot, lParams1);
        for (int i = preferences.getInt(Constant.YEAR, 0); i < 9; i++) {

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
        navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
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

    public void nextYear(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        investedText.setText(preferences.getInt(Constant.DEPOSIT, 0) + "");
        budgetText.setText(preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY) + "");
        happyText.setText( preferences.getInt(Constant.HAPPY, Constant.START_HAPPY)+"%");
        dotsLayout.removeAllViews();
        int dip = 9;
        int margin = 6;
        //перевод в dp
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                (int) px, (int) px);
        float m_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin,
                getResources().getDisplayMetrics());
        for (int i = 0; i < preferences.getInt(Constant.YEAR, 0); i++) {
            dot = new View(this);
            dot.setBackgroundResource(R.drawable.ic_active_circle);
            lParams.setMargins(0, 0, (int) m_px, 0);
            dot.setLayoutParams(lParams);
            topDots.add(dot);
            dotsLayout.addView(dot, lParams);
        }
        LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(
                (int) px*2, (int) px);
        dot = new View(this);
        dot.setBackgroundResource(R.drawable.ic_current_year);
        lParams1.setMargins((int)m_px, 0, (int) m_px, 0);
        dot.setLayoutParams(lParams1);
        topDots.add(dot);
        dotsLayout.addView(dot, lParams1);
        for (int i = preferences.getInt(Constant.YEAR, 0); i < 9; i++) {

            dot = new View(this);
            dot.setBackgroundResource(R.drawable.ic_circle);
            lParams.setMargins(0, 0, (int) m_px, 0);
            dot.setLayoutParams(lParams);
            topDots.add(dot);
            dotsLayout.addView(dot, lParams);
        }
        topTextView.setText("Шаг  " + (preferences.getInt(Constant.YEAR, 0)+1 )+ " из 10");
        switchBackBottomNavMenu();
    }

    @Override
    public void showBottomSheet(Company company, int[] dots) {
        if (tabLayoutMediator.isAttached()) return;
        adapter = new FragmentAdapter(this);
        adapter.setData(company, dots, this);
        pager.setAdapter(adapter);
        tabLayoutMediator.attach();
        bottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void updateData() {

    }
    public void switchBottomNavMenu(){
        navigation.getMenu().clear();
        navigation.inflateMenu(R.menu.bottom_nav_menu_stock_market);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.investFragment, new StockMarketFragment());
        ft.commit();
    }
    public void switchBackBottomNavMenu(){
        navigation.getMenu().clear();
        navigation.inflateMenu(R.menu.bottom_nav_menu_game);

    }
}