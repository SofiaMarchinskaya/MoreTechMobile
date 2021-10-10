package com.sofiamarchinskaya.moretechmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EndOfTurnFragment extends Fragment {
    public static EndOfTurnAdapter adapter;
    private  List<EndOfTurnElement> elems;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.end_of_turn_fragment, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.edit().putInt(Constant.VIP_VAL, preferences.getInt(Constant.VIP_VAL,0)+5).apply();
        elems = new ArrayList<>();
        int add = 0;
        for (String s: preferences.getStringSet(Constant.STOCKS, new ArraySet<>())
             ) {
            if (preferences.getInt(s+"count",0)!=0&&!preferences.getBoolean(s+"isSell",false)){
                add+= (preferences.getInt(s + "deposit", (int) (preferences.getInt("Alibaba", 22690)*Math.pow(Constant.INF,
                        preferences.getInt(Constant.YEAR,0)))) * preferences
                        .getInt(s + "count", 1) * Constant.KUP);
            }else if(preferences.getBoolean(s+"isSell",false)){
                add += (int) (preferences.getInt("Alibaba", 22690)*Math.pow(Constant.INF,
                        preferences.getInt(Constant.YEAR,0)))*preferences.getInt(s+"count", 1)*(Constant.KUP+1);

                preferences.edit().putInt(s+"count", 0)
                        .putInt(Constant.DEPOSIT, 0)
                        .putStringSet(Constant.STOCKS, new ArraySet<>())
                        .putBoolean(s+"isSell",false).apply();

            }
        }
        elems.add(new EndOfTurnElement("Доход",R.drawable.ic_wallet,add+" ア"));
        elems.add(new EndOfTurnElement("Зарплата",R.drawable.ic_salary,500000+" ア"));

        elems.add(new EndOfTurnElement("Инфляция",R.drawable.ic_discount_shape,
                (Constant.INF*100-100)+
                " %"));
        elems.add(new EndOfTurnElement("Счастье",R.drawable.ic_emoji_happy,2+" %"));
        adapter = new EndOfTurnAdapter(elems,getContext());
        RecyclerView recyclerView = result.findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        preferences.edit().putInt(Constant.TOTAL_MONEY, preferences.getInt(Constant.TOTAL_MONEY,
                0)+add+500000)
                .putInt(Constant.YEAR, preferences.getInt(Constant.YEAR, 0)+1)
                .putInt(Constant.HAPPY, preferences.getInt(Constant.HAPPY, 0)+2)
                .putInt(Constant.COUNTER, 0)
                .apply();
        ((GameActivity)getActivity()).nextYear();

        return result;
    }

}

