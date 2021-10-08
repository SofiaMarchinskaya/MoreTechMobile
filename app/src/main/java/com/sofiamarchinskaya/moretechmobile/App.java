package com.sofiamarchinskaya.moretechmobile;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        // TODO:  Поменять ссылку
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://l12.scripthub.ru/test/")//ставим снову url
                .addConverterFactory(GsonConverterFactory.create(gson))//добавляем конвертор
                .build();
        api = retrofit.create(Api.class);

    }
}
