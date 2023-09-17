package com.upeu.retrofitft.api.manager;

import android.util.Log;
import com.upeu.retrofitft.api.config.RetrofitClient;
import com.upeu.retrofitft.api.service.CategoriaApiService;
import com.upeu.retrofitft.model.Categoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class CategoriaApiManager {
    private CategoriaApiService categoriaApiService;

    public CategoriaApiManager() {
        categoriaApiService = RetrofitClient.categoriaApiService();
    }

    public void obtenerCategorias(final Callback<List<Categoria>> callback) {
        Call<List<Categoria>> call = categoriaApiService.getAlldata();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful()) {
                    List<Categoria> categorias = response.body();
                    callback.onResponse(call, Response.success(categorias));
                    Log.d("TAG", "onResponse: ");

                } else {
                    callback.onFailure(call, new Throwable("Error en la respuesta de la API"));
                    Log.d("error", "onResponse: asddddddddddddddd");
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
