package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
        TextView title  = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.image);
        TextView deposit = view.findViewById(R.id.deposit);
        title.setText(this.title);
        deposit.setText(this.deposit);
        imageView.setImageResource(imageRes);
        GraphView graph = view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < points.length; i++) {
            series.appendData(new DataPoint(i, points[i]), false, points.length);
        }
        graph.addSeries(series);
        return view;
    }
}