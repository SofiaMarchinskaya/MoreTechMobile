package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.sofiamarchinskaya.moretechmobile.utils.GraphUtils;

import org.w3c.dom.Text;


public class StockMarketFragment extends Fragment {
    private static int[] points;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_market_fragment, container, false);
        TextView step = view.findViewById(R.id.step);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        TextView count = view.findViewById(R.id.subtitle_stock);
        TextView dividends = view.findViewById(R.id.dividents);
        count.setText(preferences.getInt("Alibaba"+"count", 1)+ " акций(я)");
        dividends.setText("+ " + (preferences.getInt("Alibaba" + "deposit", 22650) * preferences
                .getInt("Alibaba" + "count", 1) * 0.0249) +
                "ア");
        step.setText("Шаг "+preferences.getInt(Constant.STEP, 1)+" из 3");
        GraphView graph = view.findViewById(R.id.graph);
        if (preferences.getInt(Constant.STEP, 1)!=1)
            preferences.edit().putInt(Constant.STEP, 1)
            .putBoolean("Alibaba"+"isSell", false).apply();
        if (preferences.getBoolean("Alibaba"+"isUpPrice", true)){
            points = GraphUtils.generateUp();
        }
        else {
            points = GraphUtils.generateDown();
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

        for (int i = 0; i < points.length/3; i++) {
            series.appendData(new DataPoint(i, points[i]), false, points.length);
        }
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.green));
        graph.setLayerPaint(paint);
        graph.getGridLabelRenderer().setVerticalLabelsColor(getResources().getColor(R.color.white));
        graph.getGridLabelRenderer().setHorizontalLabelsColor(getResources().getColor(R.color.white));
        graph.addSeries(series);
        graph.getGridLabelRenderer().setNumHorizontalLabels(points.length/3);
        graph.getGridLabelRenderer().setNumVerticalLabels(0);
        if (preferences.getInt(Constant.STEP, 1)==2){
            setSecondPiece(series, graph);
        }else if (preferences.getInt(Constant.STEP, 1)==3){
            setThirdPiece(series, graph);
        }
        Button sell = view.findViewById(R.id.sell);
        sell.setOnClickListener(v -> {
            preferences.edit()
                    .putBoolean("Alibaba"+"isSell", true)
                    .putInt(Constant.STEP, 3)
                    .apply();
            setSecondPiece(series, graph);
            setThirdPiece(series, graph);
            step.setText("Шаг "+preferences.getInt(Constant.STEP, 1)+" из 3");
        });
        Button hold = view.findViewById(R.id.hold);
        hold.setOnClickListener(v -> {
            if (preferences.getInt(Constant.STEP, 1)==1){
                setSecondPiece(series, graph);
                preferences.edit().putInt(Constant.STEP, 2).apply();
            }else if (preferences.getInt(Constant.STEP, 1)==2){
                setThirdPiece(series, graph);
                preferences.edit().putInt(Constant.STEP, 3).apply();
            }
            else {

            }
            step.setText("Шаг "+preferences.getInt(Constant.STEP, 1)+" из 3");
        });
        return view;
    }
    private void setSecondPiece(LineGraphSeries<DataPoint> series,GraphView graphView){
        series.resetData(new DataPoint[0]);
        for (int i = 0; i < 2*points.length/3; i++) {
            series.appendData(new DataPoint(i, points[i]), false, points.length);
        }
        graphView.getGridLabelRenderer().setNumHorizontalLabels(2*points.length/3);
    }
    private void setThirdPiece(LineGraphSeries<DataPoint> series,GraphView graphView){
        series.resetData(new DataPoint[0]);
        for (int i = 0; i < points.length; i++) {
            series.appendData(new DataPoint(i, points[i]), false, points.length);
        }
        graphView.getGridLabelRenderer().setNumHorizontalLabels(points.length);
    }

}
