package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
        title.setText(this.title);
        deposit.setText(this.deposit+" ₽");
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
        all.setOnClickListener(v -> {
            int total = preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY);
            preferences.edit().putInt(Constant.TOTAL_MONEY, 0)
                    .putInt(Constant.DEPOSIT, total).apply();
            InvestFragment.adapter.notifyDataSetChanged();
            ((GameActivity)getActivity()).close();
        });
        set.setOnClickListener(v -> {
            int total = preferences.getInt(Constant.TOTAL_MONEY, Constant.START_MONEY);
            if (editText.getText().toString().equals(""))
                editText.setText("0");
            int minus  = Integer.parseInt(editText.getText().toString());
            if (minus>0&& total-minus>0) {
                preferences.edit().putInt(Constant.TOTAL_MONEY, total - minus)
                        .putInt(Constant.DEPOSIT, preferences.getInt(Constant.DEPOSIT, 0) + minus)
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