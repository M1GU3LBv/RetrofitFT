package com.upeu.retrofitft.api.config;

import com.upeu.retrofitft.api.service.CategoriaApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


  private static final String BASE_URL = "http://192.168.100.73:8080/";
    public static CategoriaApiService categoriaApiService() {

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(CategoriaApiService.class);
    }

}
