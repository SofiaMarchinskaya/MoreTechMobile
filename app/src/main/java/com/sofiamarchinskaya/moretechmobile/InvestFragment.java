package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class InvestFragment extends Fragment implements InvestAdapter.OnItemClicked  {
  public  GamePresenter gamePresenter;
    public static InvestAdapter adapter;
    public InvestFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.invest_fragment, container, false);
        gamePresenter = new GamePresenter((GameActivity)getActivity(),getContext());
        adapter = new InvestAdapter(getContext(),gamePresenter.getCompanyList());
        RecyclerView recyclerView = result.findViewById(R.id.list);
        adapter.setOnClick(this);
        recyclerView.setAdapter(adapter);
        return result;
    }

    @Override
    public void onItemClick(int position) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(Constant.LAST_INDEX,
                position).apply();
        gamePresenter.onCompanyClick(Constant.STOCKS,position);
    }
}
