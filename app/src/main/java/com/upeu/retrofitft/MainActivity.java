package com.upeu.retrofitft;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.upeu.retrofitft.api.adapter.CategoriaAdapter;
import com.upeu.retrofitft.api.manager.CategoriaApiManager;
import com.upeu.retrofitft.model.Categoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CategoriaAdapter categoriaAdapter;
    private CategoriaApiManager categoriaApiManager;
    private Button btnListarCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriaAdapter = new CategoriaAdapter(this);
        recyclerView.setAdapter(categoriaAdapter);

        categoriaApiManager = new CategoriaApiManager();
        btnListarCategorias = findViewById(R.id.btnListarCategorias);

        // Configura el clic del botón
        btnListarCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerCategorias();
            }
        });
    }

    private void obtenerCategorias() {
        categoriaApiManager.obtenerCategorias(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful()) {
                    List<Categoria> categorias = response.body();
                    categoriaAdapter.setCategorias(categorias);
                } else {
                    Log.d("Error", "onResponse: Error");
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.d("Error", "onResponse: Error"+t);
            }
        });
    }
}