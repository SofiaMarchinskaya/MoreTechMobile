package com.sofiamarchinskaya.moretechmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sofiamarchinskaya.moretechmobile.models.Company;
import com.sofiamarchinskaya.moretechmobile.utils.GraphUtils;
import com.sofiamarchinskaya.moretechmobile.utils.NewsUtils;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter {
    private ViewActivity viewActivity;
    private ViewDeposit viewDeposit;
    private List<Company> companyList;

    public GamePresenter(ViewActivity viewActivity,
                         ViewDeposit viewDeposit, Context context) {
        this.viewActivity = viewActivity;
        this.viewDeposit = viewDeposit;
        companyList = new ArrayList<>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Taobao", "", "", preferences.getInt("Taobaodeposit", 0),
                R.drawable.taobao, new ArrayList<>(), context));

        companies
                .add(new Company("Tmall", "", "", preferences.getInt("Tmalldeposit", 0),
                        R.drawable.tmall, new ArrayList<>(), context));
        for (int i = 0; i < 10; i++) {
            companyList.add(new Company("Alibaba", "BABA", "Alibaba Group Holding " +
                    "Limited - китайская компания, представляет собой эдакую комбинацию Amazon, eBay " +
                    "и PayPal и на ее долю приходится 80% всей интернет-торговли в КНР. " +
                    "Многомиллиардный бизнес включает в себя торговую площадку Alibaba.com и два " +
                    "шопинг-ресурса: Taobao и Tmall, не считая последних приобретений. Taobao, " +
                    "площадка для мелких торговцев, которые платят за рекламу и другие возможности " +
                    "сайта.", preferences.getInt("Alibaba"+"deposit", 0), R.drawable.aibaba,
                    companies, context));
            companyList.get(i).setUpPrice(
                    NewsUtils.isGoodForThisCompanyYear(companyList.get(i).getTitle())
                            .equals(Constant.GOOD_NEWS));
        }
    }

    /**
     * @param fromClick Ожидаю одно из значений Constant.STOCKS, Constant.DEPOSIT, Constant.BONDS
     * @param position  Позиция в Recycler
     **/
    public void onCompanyClick(String fromClick, int position) {
        viewActivity.showBottomSheet(companyList.get(position), getGraphDots(position));
    }

    public void closeBottomSheet() {
        viewDeposit.updateData();
        viewActivity.updateData();

    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    private int[] getGraphDots(int position) {
        if (companyList.get(position).isUpPrice()) return GraphUtils.generateUp();
        else return GraphUtils.generateDown();
    }

    interface ViewActivity {
        void showBottomSheet(Company company, int[] dots);

        void updateData();
    }

    interface ViewDeposit {
        void updateData();
    }

}
