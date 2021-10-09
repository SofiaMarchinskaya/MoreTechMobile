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
        allNews.add(new News("В третем квартале этого года более миллиона пользователей не смогли" +
                " войти в своми акканты в Instagram и WhatApp. Данный технический сбой болезнено " +
                "сказался на мировой экономике.", Constant.BAD_NEWS, "Instagram"));
        allNews.add(new News("Intel анансоривала выход собсвенной игры в 2023 году. Она будет " +
                "расчитана под их новую игровую карту", Constant.GOOD_NEWS, "Intel"));
        allNews.add(new News("По расчетам астрологов ретроградный Меркурий переходит в фазу " +
                "Венеры, поэтому следующий год обещает быть удачным для роста выработки нефти и " +
                "добычи газа.  id good", Constant.GOOD_NEWS, "Лукоил"));
        allNews.add(new News("В третем квартале этого года более миллиона пользователей не смогли" +
                " войти в своми акканты в Instagram и WhatApp. Данный технический сбой болезнено " +
                "сказался на мировой экономике.", Constant.BAD_NEWS, "Instagram"));
        allNews.add(new News("Intel анансоривала выход собсвенной игры в 2023 году. Она будет " +
                "расчитана под их новую игровую карту", Constant.GOOD_NEWS, "Intel"));
        allNews.add(new News("По расчетам астрологов ретроградный Меркурий переходит в фазу " +
                "Венеры, поэтому следующий год обещает быть удачным для роста выработки нефти и " +
                "добычи газа.  id good", Constant.GOOD_NEWS, "Лукоил"));
        allNews.add(new News("В третем квартале этого года более миллиона пользователей не смогли" +
                " войти в своми акканты в Instagram и WhatApp. Данный технический сбой болезнено " +
                "сказался на мировой экономике.", Constant.BAD_NEWS, "Instagram"));
        allNews.add(new News("Intel анансоривала выход собсвенной игры в 2023 году. Она будет " +
                "расчитана под их новую игровую карту", Constant.GOOD_NEWS, "Intel"));
        allNews.add(new News("По расчетам астрологов ретроградный Меркурий переходит в фазу " +
                "Венеры, поэтому следующий год обещает быть удачным для роста выработки нефти и " +
                "добычи газа.  id good", Constant.GOOD_NEWS, "Лукоил"));
        allNews.add(new News("В третем квартале этого года более миллиона пользователей не смогли" +
                " войти в своми акканты в Instagram и WhatApp. Данный технический сбой болезнено " +
                "сказался на мировой экономике.", Constant.BAD_NEWS, "Instagram"));
        allNews.add(new News("Intel анансоривала выход собсвенной игры в 2023 году. Она будет " +
                "расчитана под их новую игровую карту", Constant.GOOD_NEWS, "Intel"));
        allNews.add(new News("По расчетам астрологов ретроградный Меркурий переходит в фазу " +
                "Венеры, поэтому следующий год обещает быть удачным для роста выработки нефти и " +
                "добычи газа.  id good", Constant.GOOD_NEWS, "Лукоил"));
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
