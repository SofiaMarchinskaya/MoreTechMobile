package com.sofiamarchinskaya.moretechmobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sofiamarchinskaya.moretechmobile.models.News;
import com.sofiamarchinskaya.moretechmobile.utils.NewsUtils;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private final LayoutInflater inflater;


    NewsAdapter(Context context) {

        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.news_element, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        News [] news = NewsUtils.getCurrentNews().toArray(new News[0]);
        holder.news_text.setText(news[position].getText());
        if (news[position].getType().equals(Constant.BAD_NEWS)){
            holder.stick.setBackgroundResource(R.drawable.red);
        }else {
            holder.stick.setBackgroundResource(R.drawable.blue);
        }
    }

    @Override
    public int getItemCount() {
        return NewsUtils.getCurrentNews().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView news_text;
        final View stick;
        ViewHolder(View view){
            super(view);
            news_text = (TextView)view.findViewById(R.id.text);
            this.stick = view.findViewById(R.id.stick);

        }
    }


}
