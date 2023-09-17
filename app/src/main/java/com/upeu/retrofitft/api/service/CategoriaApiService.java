package com.upeu.retrofitft.api.service;

import com.upeu.retrofitft.model.Categoria;
import com.upeu.retrofitft.model.CategoriaList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


import java.util.List;

public interface CategoriaApiService {
@GET("/categoria")
Call <List<Categoria>> getAlldata();
}
