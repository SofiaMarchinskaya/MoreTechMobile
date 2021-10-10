package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondNewsFragment extends Fragment {
    public static NewsAdapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_second_news, container, false);
        adapter = new NewsAdapter(getContext());
        RecyclerView recyclerView = result.findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        TextView step = result.findViewById(R.id.step);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        step.setText("Шаг "+preferences.getInt(Constant.STEP, 1)+" из 3");
        return result;
    }

}
