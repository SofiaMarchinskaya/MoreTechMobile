package com.sofiamarchinskaya.moretechmobile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComeInPresenter {
    private ViewComeIn viewComeIn;

    public ComeInPresenter(ViewComeIn viewComeIn) {
        this.viewComeIn = viewComeIn;
    }

    public void authorize(String email, String password) {
        App.getApi().authorize(email, password).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response != null && response.code() == 200) {
                    viewComeIn.onSuccess();
                }else {
                    viewComeIn.onFailed();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                viewComeIn.onFailed();
            }
        });
    }

    interface ViewComeIn {
        void onSuccess();

        void onFailed();
    }
}
