package com.sofiamarchinskaya.moretechmobile;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        TextView title = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.image);
        TextView deposit = view.findViewById(R.id.deposit);
        title.setText(this.title);
        deposit.setText(this.deposit);
        imageView.setImageResource(imageRes);
        GraphView graph = view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
            @Override
            public String[] getMonths() {
                return new String[]{"", "Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг",
                        "Сен", "Окт", "Ноя", "Дек"};
            }
        };
        DateFormat dateFormat = new SimpleDateFormat("MMM.", myDateFormatSymbols);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(),
                dateFormat));
        for (int i = 0; i < points.length; i++) {
            series.appendData(new DataPoint(new Date(2021, i, 1), points[i]), false, points.length);
        }
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.green));
        graph.setLayerPaint(paint);
        graph.addSeries(series);
        graph.getGridLabelRenderer().setNumHorizontalLabels(points.length);
        graph.getGridLabelRenderer().setNumVerticalLabels(0);
        return view;
    }
}