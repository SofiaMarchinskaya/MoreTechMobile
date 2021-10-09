package com.sofiamarchinskaya.moretechmobile;

import android.app.Application;


import com.sofiamarchinskaya.moretechmobile.utils.NewsUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static Api api;

    public static Api getApi() {
        return api;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO:  Поменять ссылку
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://google.com/")//ставим снову url
                .addConverterFactory(GsonConverterFactory.create())//добавляем конвертор
                .build();
        api = retrofit.create(Api.class);
        NewsUtils.init();
    }
}
