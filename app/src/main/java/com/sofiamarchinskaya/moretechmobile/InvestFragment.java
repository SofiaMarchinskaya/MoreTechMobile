package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class InvestFragment extends Fragment implements GamePresenter.ViewDeposit,InvestAdapter.OnItemClicked  {
  public static GamePresenter gamePresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.invest_fragment, container, false);
        gamePresenter = new GamePresenter((GameActivity)getActivity(),this,getContext());
        InvestAdapter adapter = new InvestAdapter(getContext(),gamePresenter.getCompanyList());
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
        gamePresenter.onCompanyClick(Constant.STOCKS,position);
    }
}
