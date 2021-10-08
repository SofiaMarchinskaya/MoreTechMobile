package com.sofiamarchinskaya.moretechmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;



public class gameFragment extends Fragment {
    private TextView showAllText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.game_fragment, container, false);
        showAllText = result.findViewById(R.id.view_rules);
        Spannable span = new SpannableString(getString(R.string.view_all));
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#3A83F1")), 0, showAllText.getText().length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        showAllText.setText(span);
        return result;
    }
}
