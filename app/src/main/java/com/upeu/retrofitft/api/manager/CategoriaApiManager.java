package com.upeu.retrofitft.api.manager;

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

                } else {
                    callback.onFailure(call, new Throwable("Error en la respuesta de la API"));
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void obtenerCategoriaId(final Callback<Categoria> callback, int id) {
        Call<Categoria> call = categoriaApiService.getOneData(id);
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if (response.isSuccessful()) {
                    Categoria categoria = response.body();
                    callback.onResponse(call, Response.success(categoria));

                } else {
                    callback.onFailure(call, new Throwable("Error en la respuesta de la API"));
                }


            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                callback.onFailure(call, t);
            }

        });


    }





    public void crearCategoria(Categoria categoria, final Callback<Categoria> callback) {
        Call<Categoria> call = categoriaApiService.postData(categoria);
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if (response.isSuccessful()) {
                    Categoria nuevaCategoria = response.body();
                    callback.onResponse(call, Response.success(nuevaCategoria));
                } else {
                    callback.onFailure(call, new Throwable("Error en la respuesta de la API"));
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
    public void eliminarCategoria(int id, Callback<Object> callback) {
        Call<Object> call = categoriaApiService.eliminarCategoria(id);
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
    public void deletecategoria(int id) {
        String response= categoriaApiService.deleteItem(id);
    }


}








