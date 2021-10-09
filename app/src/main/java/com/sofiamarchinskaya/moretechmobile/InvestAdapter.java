package com.sofiamarchinskaya.moretechmobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sofiamarchinskaya.moretechmobile.models.Company;

import java.util.List;

public class InvestAdapter extends  RecyclerView.Adapter<InvestAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private List<Company> companies;
    private InvestAdapter.OnItemClicked onClick;

    public InvestAdapter(Context context, List<Company> companies){
               this.companies = companies;
               this.inflater = LayoutInflater.from(context);
            }
    @NonNull
    @Override
    public InvestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.stock_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Company companies = this.companies.get(position);
        holder.title.setText(companies.getTitle());
        holder.deposite.setText(companies.getDeposit()+"₽");
        holder.subtitle.setText(companies.getSubtitle());
        holder.img.setImageResource(companies.getImageResources());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }
    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView subtitle;
        TextView deposite;
        ImageView img;
        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_stock);
            subtitle = view.findViewById(R.id.subtitle_stock);
            deposite = view.findViewById(R.id.deposite);
            img = view.findViewById(R.id.image_stock);
        }
    }
    public void setOnClick(InvestAdapter.OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}

