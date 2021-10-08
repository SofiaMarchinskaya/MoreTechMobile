package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class gameFragment extends Fragment implements RuleAdapter.OnItemClicked {
    private TextView showAllText;
    ArrayList<Rule> rules = new ArrayList<Rule>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.game_fragment, container, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Button nov = result.findViewById(R.id.novichek);
        Button prod = result.findViewById(R.id.prodvinutiy);
        Button professional = result.findViewById(R.id.professional);
        nov.setBackgroundResource(R.drawable.rounded_button_blue);
        nov.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        prod.setBackgroundResource(R.drawable.rounded_button_grey);
        prod.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        professional.setBackgroundResource(R.drawable.rounded_button_grey);
        professional.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        preferences.edit().putInt(Constant.LVL, Constant.LVL_NOVICHEK).apply();
        nov.setOnClickListener(v -> {
            nov.setBackgroundResource(R.drawable.rounded_button_blue);
            nov.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            prod.setBackgroundResource(R.drawable.rounded_button_grey);
            prod.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            professional.setBackgroundResource(R.drawable.rounded_button_grey);
            professional.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            preferences.edit().putInt(Constant.LVL, Constant.LVL_NOVICHEK).apply();
        });
        prod.setOnClickListener(v -> {
            nov.setBackgroundResource(R.drawable.rounded_button_grey);
            nov.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            prod.setBackgroundResource(R.drawable.rounded_button_blue);
            prod.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            professional.setBackgroundResource(R.drawable.rounded_button_grey);
            professional.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            preferences.edit().putInt(Constant.LVL, Constant.LVL_PRODVIN).apply();
        });
        professional.setOnClickListener(v -> {
            nov.setBackgroundResource(R.drawable.rounded_button_grey);
            nov.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            prod.setBackgroundResource(R.drawable.rounded_button_grey);
            prod.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            professional.setBackgroundResource(R.drawable.rounded_button_blue);
            professional.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            preferences.edit().putInt(Constant.LVL, Constant.LVL_PROFFESIONAL).apply();
        });
        showAllText = result.findViewById(R.id.view_rules);
        Spannable span = new SpannableString(getString(R.string.view_all));
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#3A83F1")), 0, showAllText.getText().length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        showAllText.setText(span);
        RecyclerView recyclerView = result.findViewById(R.id.list);
        // создаем адаптер
        RuleAdapter adapter = new RuleAdapter(getContext(), rules);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        adapter.setOnClick(this);


        rules.add(new Rule("Цель игры", R.drawable.ic_crown_2));
        rules.add(new Rule("События", R.drawable.ic_note));
        rules.add(new Rule("Необязательные расходы", R.drawable.ic_wallet));
        rules.add(new Rule("Счастье",R.drawable.ic_emoji_happy));


        return result;
    }

    @Override
    public void onItemClick(int position) {


    }
}

