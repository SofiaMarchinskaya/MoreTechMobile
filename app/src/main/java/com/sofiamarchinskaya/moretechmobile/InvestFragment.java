package com.sofiamarchinskaya.moretechmobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class InvestFragment extends Fragment implements GamePresenter.ViewDeposit,InvestAdapter.OnItemClicked  {
  public static GamePresenter gamePresenter;
    public static InvestAdapter adapter;
    public static InvestFragment newInstance(int page) {
        InvestFragment fragment = new InvestFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.invest_fragment, container, false);
        gamePresenter = new GamePresenter((GameActivity)getActivity(),this,getContext());
        adapter = new InvestAdapter(getContext(),gamePresenter.getCompanyList());
        RecyclerView recyclerView = result.findViewById(R.id.list);
        adapter.setOnClick(this);
        recyclerView.setAdapter(adapter);
        return result;
    }

    @Override
    public void updateData() {

    }

    @Override
    public void onItemClick(int position) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(Constant.LAST_INDEX,
                position).apply();
        gamePresenter.onCompanyClick(Constant.STOCKS,position);
    }
}
