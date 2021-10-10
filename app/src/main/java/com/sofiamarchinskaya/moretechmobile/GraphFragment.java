package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class GraphFragment extends Fragment {
    private String title;
    private int imageRes;
    private int deposit;
    private int[] points;

    public GraphFragment(String title, int imageRes, int deposit, int[] points) {
        // Required empty public constructor
        this.title = title;
        this.imageRes = imageRes;
        this.deposit = deposit;
        this.points = points;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        EditText editText = view.findViewById(R.id.edit);
        TextView title = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.image);
        TextView deposit = view.findViewById(R.id.deposit);
        Button all = view.findViewById(R.id.all);
        Button set = view.findViewById(R.id.set);
        TextView possible = view.findViewById(R.id.possible);
        title.setText(this.title);
        deposit.setText(this.deposit+"  ア");
        imageView.setImageResource(imageRes);
        GraphView graph = view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

        for (int i = 0; i < points.length; i++) {
            series.appendData(new DataPoint(i, points[i]), false, points.length);
        }
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.green));
        graph.setLayerPaint(paint);
        graph.getGridLabelRenderer().setVerticalLabelsColor(getResources().getColor(R.color.white));
        graph.getGridLabelRenderer().setHorizontalLabelsColor(getResources().getColor(R.color.white));
        graph.addSeries(series);
        graph.getGridLabelRenderer().setNumHorizontalLabels(points.length);
        graph.getGridLabelRenderer().setNumVerticalLabels(0);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int enable;
        if (preferences.getBoolean(Constant.IS_PAYED, false)) {
            possible.setText("Доступно: " + preferences.getInt(Constant.TOTAL_MONEY, 0));
            enable = preferences.getInt(Constant.TOTAL_MONEY, 0);
        }
        else {
            possible.setText("Доступно: "+(preferences.getInt(Constant.TOTAL_MONEY, 0)-Constant.PRICE_FOR_YEAR));
            enable = (preferences.getInt(Constant.TOTAL_MONEY, 0)-Constant.PRICE_FOR_YEAR);
        }
        all.setOnClickListener(v -> {
            Set<String> newSet = preferences.getStringSet(Constant.STOCKS, new ArraySet<>());
            newSet.add(this.title);
            int count = enable/this.deposit;
            preferences.edit().putInt(Constant.TOTAL_MONEY, preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY)-(count*this.deposit))
                    .putInt(Constant.DEPOSIT, count*this.deposit+preferences.getInt(Constant.DEPOSIT, 0))
                    .putStringSet(Constant.STOCKS, newSet)
                    .putInt(this.title+"count", preferences.getInt(this.title+"count", 0)+count)
                    .putInt(this.title+"deposit", this.deposit)
                    .putInt(this.title+"image", this.imageRes)
                    .apply();

            InvestFragment.adapter.notifyDataSetChanged();
            ((GameActivity)getActivity()).close();
        });
        set.setOnClickListener(v -> {
            if (editText.getText().toString().equals(""))
                editText.setText("0");
            int minus  = Integer.parseInt(editText.getText().toString());
            int count = minus/this.deposit;
            if (minus>0&& enable-minus>=0&&count>0) {
                Set<String> newSet = preferences.getStringSet(Constant.STOCKS, new ArraySet<>());
                newSet.add(this.title);
                preferences.edit().putInt(Constant.TOTAL_MONEY,
                        preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY) - (this.deposit*count))
                        .putInt(Constant.DEPOSIT, preferences.getInt(Constant.DEPOSIT, 0) + this.deposit*count)
                        .putStringSet(Constant.STOCKS, newSet)
                        .putInt(this.title+"count", preferences.getInt(this.title+"count", 0)+count)
                        .putInt(this.title+"deposit", this.deposit)
                        .putInt(this.title+"image", this.imageRes)
                        .apply();
                InvestFragment.adapter.notifyDataSetChanged();
                ((GameActivity)getActivity()).close();
            }
            else {
                editText.setTextColor(getResources().getColor(R.color.red));
            }
        });
        return view;
    }
}