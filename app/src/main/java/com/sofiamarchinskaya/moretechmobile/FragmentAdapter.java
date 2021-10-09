package com.sofiamarchinskaya.moretechmobile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sofiamarchinskaya.moretechmobile.models.Company;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private Company company;
    private int[] point;

    public FragmentAdapter(
            @NonNull FragmentActivity fragment) {
        super(fragment);
    }

    public void setData(Company company, int [] point) {
       this.company = company;
        this.point = point;
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
                        company.getDeposit(), point);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}


