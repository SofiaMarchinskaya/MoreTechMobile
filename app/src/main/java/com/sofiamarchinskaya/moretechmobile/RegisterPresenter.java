package com.sofiamarchinskaya.moretechmobile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private ViewRegister viewRegister;

    public RegisterPresenter(
            ViewRegister viewRegister) {
        this.viewRegister = viewRegister;
    }

    public void register(String email, String password){
        App.getApi().register(email, password).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response != null && response.code() == 200) {
                    viewRegister.onSuccess();
                }else{
                    viewRegister.onFailed();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                viewRegister.onFailed();
            }
        });
    }

    interface ViewRegister {
        void onSuccess();

        void onFailed();
    }
}
