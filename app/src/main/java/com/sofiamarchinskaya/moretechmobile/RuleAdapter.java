package com.sofiamarchinskaya.moretechmobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RuleAdapter  extends RecyclerView.Adapter<RuleAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Rule> rules;
    private Context context;
    private OnItemClicked onClick;

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }

    RuleAdapter(Context context, ArrayList<Rule> rules) {
        this.rules = rules;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Rule rule = rules.get(position);
        holder.ruleImageView.setImageResource(rule.getImage());
        holder.ruleNameView.setText(rule.getRuleName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rules.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ruleImageView;
        final TextView ruleNameView;
        ViewHolder(View view){
            super(view);
            ruleImageView = (ImageView)view.findViewById(R.id.rule_img);
            ruleNameView = (TextView) view.findViewById(R.id.rule_name);
        }
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}