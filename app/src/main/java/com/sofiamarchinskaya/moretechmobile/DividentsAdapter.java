package com.sofiamarchinskaya.moretechmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividentsAdapter extends RecyclerView.Adapter<DividentsAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private Context context;
    SharedPreferences preferences;

    DividentsAdapter(Context context) {

        this.inflater = LayoutInflater.from(context);
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    @NonNull
    @Override
    public DividentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dividents_item, parent, false);
        return new DividentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DividentsAdapter.ViewHolder holder, int position) {

        holder.layoutD.setVisibility(View.VISIBLE);
        String[] m = preferences.getStringSet(Constant.STOCKS, new ArraySet<String>())
                .toArray(new String[0]);

        holder.companyName.setText(m[position]);
        holder.image.setBackgroundResource(
                preferences.getInt(m[position] + "image", R.drawable.aibaba));
        holder.divident.setText(
                "+ " + (preferences.getInt(m[position] + "deposit",
                        (int) (preferences.getInt("Alibaba", 22690) * Math.pow(Constant.INF,
                                preferences.getInt(Constant.YEAR, 0)))) * preferences
                        .getInt(m[position] + "count", 1) * Constant.KUP) +
                        "ア");


    }

    @Override
    public int getItemCount() {
        return preferences.getStringSet(Constant.STOCKS, new ArraySet<String>()).size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyName;
        ImageView image;
        TextView divident;
        LinearLayout layoutD;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName = (TextView) itemView.findViewById(R.id.title_stock);
            image = (ImageView) itemView.findViewById(R.id.image_stock);
            divident = (TextView) itemView.findViewById(R.id.deposite);
            layoutD = (LinearLayout) itemView.findViewById(R.id.devident_layout);

        }
    }
}