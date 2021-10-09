package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NewsFragment extends Fragment {
    public static NewsAdapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.news_fragment, container, false);
        adapter = new NewsAdapter(getContext());
        RecyclerView recyclerView = result.findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        return result;
    }

}
