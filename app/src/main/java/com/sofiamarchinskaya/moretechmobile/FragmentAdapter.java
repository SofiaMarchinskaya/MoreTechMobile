package com.sofiamarchinskaya.moretechmobile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(
            @NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new AboutCompanyFragment();
            default:
                return new GraphFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}


