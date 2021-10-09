package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        cards = new ArrayList<>();
        cards.add(new BudgetCard(R.drawable.ic_wallet,"Минимальные траты " +
                "на жизнь за год",R.string.min_trati,Constant.PRICE_FOR_YEAR,16));
        cards.add(new BudgetCard(R.drawable.ic_car,"Путешествие " +
                "со всей семьёй",R.string.travel,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_gift,"Подарки для коллег " +
                "на работе",R.string.gifts,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_cake,"Купить новый айфон для дочери"
                ,R.string.iphone,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_ticket_2,"Развлекательная " +
                "программа",R.string.entertainment,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_tick_square,"Основные решения " +
                "приняты",R.string.final_card,Constant.PRICE_FOR_YEAR,16));
        counter=0;

    acceptButton = result.findViewById(R.id.tick_square);
    finalButton = result.findViewById(R.id.finalButton);
    finalButton.setVisibility(View.INVISIBLE);
    abortButton = result.findViewById(R.id.close_square);
    price = result.findViewById(R.id.price_on_card);
    happyPercent = result.findViewById(R.id.happy_on_card);
    img = result.findViewById(R.id.image_on_card);
    title= result.findViewById(R.id.title_on_card);
    description = result.findViewById(R.id.subtitle_on_card);
    abortButton.setEnabled(false);

//    img.setBackgroundResource(0);
      img.setBackgroundResource(cards.get(counter).getImage());
    title.setText(cards.get(counter).getTitle());
    description.setText(cards.get(counter).getDescription());
    happyPercent.setText("+"+cards.get(counter).getHappy_percent()+"%");
    price.setText(cards.get(counter).getPrice()+"ア");


    acceptButton.setOnClickListener(view -> {
        counter++;
        img.setBackgroundResource(cards.get(counter).getImage());
        title.setText(cards.get(counter).getTitle());
        description.setText(cards.get(counter).getDescription());
        happyPercent.setText(cards.get(counter).getHappy_percent()+"");
        price.setText(cards.get(counter).getPrice()+"");
        abortButton.setEnabled(true);
        if(counter==cards.size()-1 ){
            abortButton.setVisibility(View.INVISIBLE);
            acceptButton.setVisibility(View.INVISIBLE);
            price.setVisibility(View.INVISIBLE);
            happyPercent.setVisibility(View.INVISIBLE);
            finalButton.setVisibility(View.VISIBLE);
        }
    });
    abortButton.setOnClickListener(view -> {
        counter++;
        img.setBackgroundResource(cards.get(counter).getImage());
        title.setText(cards.get(counter).getTitle());
        description.setText(cards.get(counter).getDescription());
        happyPercent.setText(cards.get(counter).getHappy_percent()+"");
        price.setText(cards.get(counter).getPrice()+"");
        if(counter==cards.size()-1 ){
            abortButton.setVisibility(View.INVISIBLE);
            acceptButton.setVisibility(View.INVISIBLE);
            price.setVisibility(View.INVISIBLE);
            happyPercent.setVisibility(View.INVISIBLE);
            finalButton.setVisibility(View.VISIBLE);
        }
    });


        return result;
    }
}