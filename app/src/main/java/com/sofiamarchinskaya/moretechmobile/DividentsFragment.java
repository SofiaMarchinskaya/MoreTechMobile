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

public class DividentsFragment extends Fragment {
    public static DividentsAdapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.dividents_fragment, container, false);
        adapter = new DividentsAdapter(getContext());
        RecyclerView recyclerView = result.findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        TextView step = result.findViewById(R.id.step);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        step.setText("Шаг "+preferences.getInt(Constant.STEP, 1)+" из 3");
        return result;
    }

}
