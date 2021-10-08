package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class gameFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.game_fragment, container, false);
        Button nov = result.findViewById(R.id.novichek);
        Button prod = result.findViewById(R.id.prodvinutiy);
        Button professional = result.findViewById(R.id.professional);
        nov.setBackgroundResource(R.drawable.rounded_button_blue);
        nov.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        prod.setBackgroundResource(R.drawable.rounded_button_grey);
        prod.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        professional.setBackgroundResource(R.drawable.rounded_button_grey);
        professional.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        nov.setOnClickListener(v -> {
            nov.setBackgroundResource(R.drawable.rounded_button_blue);
            nov.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            prod.setBackgroundResource(R.drawable.rounded_button_grey);
            prod.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            professional.setBackgroundResource(R.drawable.rounded_button_grey);
            professional.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        });
        prod.setOnClickListener(v -> {
            nov.setBackgroundResource(R.drawable.rounded_button_grey);
            nov.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            prod.setBackgroundResource(R.drawable.rounded_button_blue);
            prod.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            professional.setBackgroundResource(R.drawable.rounded_button_grey);
            professional.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        });
        professional.setOnClickListener(v -> {
            nov.setBackgroundResource(R.drawable.rounded_button_grey);
            nov.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            prod.setBackgroundResource(R.drawable.rounded_button_grey);
            prod.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
            professional.setBackgroundResource(R.drawable.rounded_button_blue);
            professional.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        });
        return result;
    }
}
