package com.sofiamarchinskaya.moretechmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    //TODO заменить адресс и дженерик, возможно поменять тип запроса, изменить параметры
    @GET("api.php")
    Call<Object> getSubjects(@Query("module") String module);
}
