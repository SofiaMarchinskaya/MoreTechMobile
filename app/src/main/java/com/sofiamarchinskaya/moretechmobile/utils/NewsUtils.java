package com.sofiamarchinskaya.moretechmobile.utils;

import android.util.ArraySet;

import com.sofiamarchinskaya.moretechmobile.Constant;
import com.sofiamarchinskaya.moretechmobile.models.News;

import java.util.Set;

public class NewsUtils {
    private static Set<News> allNews = new ArraySet<>();
    private static News[] exam = new News [] { new News("", "", "")};
    public static Set<News> getCurrentNews() {
        return currentNews;
    }

    private static Set<News> currentNews = new ArraySet<>();

    public static void init(){
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        allNews.add(new News("News id good", Constant.GOOD_NEWS, "Alibaba"));
        allNews.add(new News("News is bad", Constant.BAD_NEWS, "Alibaba"));
        nextYear();
    }

    public static void nextYear(){
        for (int i = 0; i < Constant.NUMBER_OF_NEWS; i++) {
            currentNews.add(allNews.toArray(exam)[i]);
        }
    }

    public static String isGoodForThisCompanyYear(String title){
        for (int i = 0; i < Constant.NUMBER_OF_NEWS; i++) {
            if (currentNews.toArray(exam)[i].getTitleCompany().equals(title))
                return currentNews.toArray(exam)[i].getType();
        }
        return Constant.NOTHING;
    }
}
