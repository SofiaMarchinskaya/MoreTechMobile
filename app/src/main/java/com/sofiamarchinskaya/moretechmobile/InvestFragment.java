package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class InvestFragment extends Fragment implements GamePresenter.ViewDeposit {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.invest_fragment, container, false);
        GamePresenter gamePresenter = new GamePresenter((GameActivity)getActivity(),this,getContext());
        return result;
    }

    @Override
    public void updateData() {

    }
}
