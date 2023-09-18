package com.upeu.retrofitft.api.service;

import com.upeu.retrofitft.model.Categoria;
import com.upeu.retrofitft.model.CategoriaList;
import retrofit2.Call;
import retrofit2.http.*;


import java.util.List;

public interface CategoriaApiService {
@GET("/categoria")
Call <List<Categoria>> getAlldata();

@GET("/categoria/{id}")
Call <Categoria> getOneData(@Path("id") int id);

@POST("/categoria")
Call <Categoria> postData(@Query("nombre") String nombre, @Query("descripcion") String descripcion);

@DELETE("/categoria/{id}")
Call <Categoria> deleteData(@Path("id") int id);

}
