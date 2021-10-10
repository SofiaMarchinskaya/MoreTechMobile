package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EndOfTurnFragment extends Fragment {
    public static EndOfTurnAdapter adapter;
    private  List<EndOfTurnElement> elems;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.end_of_turn_fragment, container, false);
        adapter = new EndOfTurnAdapter(elems,getContext());
        RecyclerView recyclerView = result.findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        elems.add(new EndOfTurnElement("Доход",R.drawable.ic_wallet,preferences.getInt(Constant.TOTAL_MONEY,0)+""));
        elems.add(new EndOfTurnElement("Зарплата",R.drawable.ic_salary,500000+""));

        elems.add(new EndOfTurnElement("Инфляция",R.drawable.ic_discount_shape,0.8+""));
        elems.add(new EndOfTurnElement("Счастье",R.drawable.ic_emoji_happy,preferences.getInt(Constant.HAPPY,0)+""));
        return result;
    }

}

