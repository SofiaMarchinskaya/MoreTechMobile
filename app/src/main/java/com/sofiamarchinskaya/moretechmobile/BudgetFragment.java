package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class BudgetFragment extends Fragment {
List<BudgetCard> cards;
private View acceptButton;
private View abortButton;
private ImageView img;
private TextView title;
    private TextView description;
    int counter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.game_fragment, container, false);
        cards.add(new BudgetCard(R.drawable.ic_wallet,"Минимальные траты" +
                "на жизнь за год",R.string.min_trati,Constant.PRICE_FOR_YEAR,16));
        cards.add(new BudgetCard(R.drawable.ic_car,"Путешествие " +
                "со всей семьёй",R.string.travel,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_gift,"Подарки для коллег " +
                "на работе",R.string.gifts,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_cake,"Купить новый айфон для дочери"
                ,R.string.iphone,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_ticket_2,"Развлекательная" +
                "программа",R.string.entertainment,21550,5));
        cards.add(new BudgetCard(R.drawable.ic_tick_square,"Основные решения" +
                "приняты",R.string.final_card,Constant.PRICE_FOR_YEAR,16));
        counter=0;
    acceptButton = result.findViewById(R.id.tick_square);
    abortButton = result.findViewById(R.id.close_square);
    img = result.findViewById(R.id.image_on_card);
    title= result.findViewById(R.id.title_on_card);
    description = result.findViewById(R.id.subtitle_on_card);
    BudgetCard card;
    acceptButton.setOnClickListener(view -> {

    });

        return result;
    }
}