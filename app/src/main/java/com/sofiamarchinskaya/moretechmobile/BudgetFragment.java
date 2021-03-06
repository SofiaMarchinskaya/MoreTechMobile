package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class BudgetFragment extends Fragment {
    List<BudgetCard> cards;
    private View acceptButton;
    private View abortButton;
    private ImageView img;
    private TextView title;
    private TextView price;
    private TextView happyPercent;
    private Button finalButton;
    private TextView description;
    int counter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_budget, container, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        cards = new ArrayList<>();
        cards.add(new BudgetCard(R.drawable.ic_wallet, "Минимальные траты " +
                "на жизнь за год", R.string.min_trati,
                (int)(Constant.PRICE_FOR_YEAR*Math.pow(Constant.INF,
                preferences.getInt(Constant.YEAR,0))), 16));
        cards.add(new BudgetCard(R.drawable.ic_car, "Путешествие " +
                "со всей семьёй", R.string.travel, 21550, 5));
        cards.add(new BudgetCard(R.drawable.ic_gift, "Подарки для коллег " +
                "на работе", R.string.gifts, 21550, 5));
        cards.add(new BudgetCard(R.drawable.ic_cake, "Купить новый айфон для дочери"
                , R.string.iphone, 21550, 5));
        cards.add(new BudgetCard(R.drawable.ic_ticket_2, "Развлекательная " +
                "программа", R.string.entertainment, 21550, 5));
        cards.add(new BudgetCard(R.drawable.ic_tick_square, "Основные решения " +
                "приняты", R.string.final_card, Constant.PRICE_FOR_YEAR, 16));
        Button pay = result.findViewById(R.id.pay);
        pay.setText((int)(455650*Math.pow(Constant.INF,
                preferences.getInt(Constant.YEAR,0)))+" ア");
        Button pay1 = result.findViewById(R.id.pay1);
        pay1.setText((int)(455650*Math.pow(Constant.INF,
                preferences.getInt(Constant.YEAR,0)))+" ア");
        Button pay2 = result.findViewById(R.id.pay2);
        pay2.setText((int)(455650*Math.pow(Constant.INF,
                preferences.getInt(Constant.YEAR,0)))+" ア");
        counter = preferences.getInt(Constant.COUNTER, 0);

        acceptButton = result.findViewById(R.id.tick_square);
        finalButton = result.findViewById(R.id.finalButton);
        finalButton.setVisibility(View.INVISIBLE);
        abortButton = result.findViewById(R.id.close_square);
        price = result.findViewById(R.id.price_on_card);
        happyPercent = result.findViewById(R.id.happy_on_card);
        img = result.findViewById(R.id.image_on_card);
        title = result.findViewById(R.id.title_on_card);
        description = result.findViewById(R.id.subtitle_on_card);
        if (counter < cards.size()-1) {
            if (counter==0)
            abortButton.setEnabled(false);
            finalButton.setVisibility(View.INVISIBLE);
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText(cards.get(counter).getHappy_percent() + "");
            price.setText(cards.get(counter).getPrice() + "");
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText("+"+cards.get(counter).getHappy_percent()+"%");
            price.setText(cards.get(counter).getPrice()+"ア");
        }else if (counter == cards.size()-1){
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText(cards.get(counter).getHappy_percent() + "");
            price.setText(cards.get(counter).getPrice() + "");
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText("+"+cards.get(counter).getHappy_percent()+"%");
            price.setText(cards.get(counter).getPrice()+"ア");
            abortButton.setVisibility(View.INVISIBLE);
            acceptButton.setVisibility(View.INVISIBLE);
            price.setVisibility(View.INVISIBLE);
            happyPercent.setVisibility(View.INVISIBLE);
            finalButton.setVisibility(View.VISIBLE);
        }else {
            counter = cards.size()-1;
            preferences.edit().putInt(Constant.COUNTER, counter).apply();
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText(cards.get(counter).getHappy_percent() + "");
            price.setText(cards.get(counter).getPrice() + "");
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText("+"+cards.get(counter).getHappy_percent()+"%");
            price.setText(cards.get(counter).getPrice()+"ア");
            abortButton.setVisibility(View.INVISIBLE);
            acceptButton.setVisibility(View.INVISIBLE);
            price.setVisibility(View.INVISIBLE);
            happyPercent.setVisibility(View.INVISIBLE);
            finalButton.setVisibility(View.VISIBLE);
        }
//    img.setBackgroundResource(0);


        if (preferences.getInt(Constant.TOTAL_MONEY,
                0) - cards.get(counter).getPrice() < 0)
            acceptButton.setEnabled(false);
        else acceptButton.setEnabled(true);
        acceptButton.setOnClickListener(view -> {
            if (counter==0) preferences.edit().putBoolean(Constant.IS_PAYED, true).apply();
            int happy;
            if (preferences.getInt(Constant.HAPPY, 0) + cards.get(counter)
                    .getHappy_percent() > 100)
                happy = 100;
            else
                happy = preferences.getInt(Constant.HAPPY, 0) + cards.get(counter)
                        .getHappy_percent();
            preferences.edit()
                    .putInt(Constant.TOTAL_MONEY,
                            preferences.getInt(Constant.TOTAL_MONEY, 0) - cards.get(counter)
                                    .getPrice())
                    .putInt(Constant.HAPPY, happy)
                    .apply();
            ((GameActivity) getActivity()).update();
            counter++;
            preferences.edit().putInt(Constant.COUNTER, counter).apply();
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText(cards.get(counter).getHappy_percent() + "%");
            price.setText(cards.get(counter).getPrice() + "");
            abortButton.setEnabled(true);
            if (counter == cards.size() - 1) {
                abortButton.setVisibility(View.INVISIBLE);
                acceptButton.setVisibility(View.INVISIBLE);
                price.setVisibility(View.INVISIBLE);
                happyPercent.setVisibility(View.INVISIBLE);
                finalButton.setVisibility(View.VISIBLE);
            }
            if (preferences.getInt(Constant.TOTAL_MONEY,
                    0) - cards.get(counter).getPrice() < 0)
                acceptButton.setEnabled(false);
            else acceptButton.setEnabled(true);
        });
        abortButton.setOnClickListener(view -> {
            counter++;
            preferences.edit().putInt(Constant.COUNTER, counter).apply();
            img.setBackgroundResource(cards.get(counter).getImage());
            title.setText(cards.get(counter).getTitle());
            description.setText(cards.get(counter).getDescription());
            happyPercent.setText(cards.get(counter).getHappy_percent() + "");
            price.setText(cards.get(counter).getPrice() + "");
            if (counter == cards.size() - 1) {
                abortButton.setVisibility(View.INVISIBLE);
                acceptButton.setVisibility(View.INVISIBLE);
                price.setVisibility(View.INVISIBLE);
                happyPercent.setVisibility(View.INVISIBLE);
                finalButton.setVisibility(View.VISIBLE);
            }
            if (preferences.getInt(Constant.TOTAL_MONEY,
                    0) - cards.get(counter).getPrice() < 0)
                acceptButton.setEnabled(false);
            else acceptButton.setEnabled(true);
        });
        finalButton.setOnClickListener(v -> {
            ((GameActivity) getActivity()).switchBottomNavMenu();
        });

        return result;
    }
}