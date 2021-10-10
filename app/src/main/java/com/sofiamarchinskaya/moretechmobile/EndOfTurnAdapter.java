package com.sofiamarchinskaya.moretechmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EndOfTurnAdapter extends  RecyclerView.Adapter<EndOfTurnAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<EndOfTurnElement> elems;
    private Context context;
    SharedPreferences preferences ;

    EndOfTurnAdapter(List<EndOfTurnElement> elems, Context context) {
        this.elems = elems;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EndOfTurnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.news_element, parent, false);
        return new EndOfTurnAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EndOfTurnAdapter.ViewHolder holder, int position) {
        EndOfTurnElement elem = elems.get(position);
            holder.image.setBackgroundResource(elem.getImage());
            holder.title.setText(elem.getTitle());
            holder.summary.setText(elem.getSummary());
        }





    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        TextView summary;
        ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title_stock);
            image = view.findViewById(R.id.image_stock);
            summary = view.findViewById(R.id.deposite);

        }
    }
}
