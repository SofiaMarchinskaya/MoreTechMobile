package com.sofiamarchinskaya.moretechmobile;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sofiamarchinskaya.moretechmobile.models.Company;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private Company company;
    private int[] point;
    private Context context;

    public FragmentAdapter(
            @NonNull FragmentActivity fragment) {
        super(fragment);
    }

    public void setData(Company company, int [] point, Context context) {
       this.company = company;
        this.point = point;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new AboutCompanyFragment(company.getImageResources(), company.getTitle(),
                        company.getDescription(), company.getAffiliatedCompanies());
            default:
                return new GraphFragment(company.getTitle(), company.getImageResources(),
                        (int)(company.getDeposit()*Math.pow(Constant.INF,
                                PreferenceManager.getDefaultSharedPreferences(context).getInt(Constant.YEAR,0))), point);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}


