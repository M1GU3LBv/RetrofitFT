package com.upeu.retrofitft.api.service;

import com.upeu.retrofitft.model.Categoria;
import retrofit2.Call;
import retrofit2.http.*;


import java.util.List;

public interface CategoriaApiService {
@GET("/categoria")
Call <List<Categoria>> getAlldata();

@GET("/categoria/{id}")
Call <Categoria> getOneData(@Path("id") int id);

@POST("/categoria")
Call <Categoria> postData(@Body Categoria categoria);

@DELETE("/categoria/{id}")
Call<Object> eliminarCategoria(@Path("id") int id);
    @DELETE("/categoria/{id}")
    String deleteItem(@Path("id") int itemId);


}
