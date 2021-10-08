package com.sofiamarchinskaya.moretechmobile;

import android.app.Application;


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
                .baseUrl("https://l12.scripthub.ru/test/")//ставим снову url
                .addConverterFactory(GsonConverterFactory.create())//добавляем конвертор
                .build();
        api = retrofit.create(Api.class);

    }
}
