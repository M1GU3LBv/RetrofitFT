package com.upeu.retrofitft;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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
    private Button btnAgregarCategoria;

    private String NombreTemp;
    private String DescripcionTemp;

    private EditText txtNombre;
    private EditText txtDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriaAdapter = new CategoriaAdapter(this, categoriaApiManager);
        recyclerView.setAdapter(categoriaAdapter);

        categoriaApiManager = new CategoriaApiManager();
        btnListarCategorias = findViewById(R.id.btnListarCategorias);
        btnAgregarCategoria = findViewById(R.id.btnAgregarCategoria);

        txtNombre = findViewById(R.id.insertnombre);
        txtDescripcion = findViewById(R.id.insertdescrip);

        // Configura el clic del botón para listar categorías
        btnListarCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerCategorias();
            }
        });

        // Configura el clic del botón para agregar una categoría
        btnAgregarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NombreTemp = txtNombre.getText().toString();
                DescripcionTemp = txtDescripcion.getText().toString();

                if (NombreTemp.isEmpty() || DescripcionTemp.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campos vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    agregarCategoria();
                    txtNombre.setText("");
                    txtDescripcion.setText("");
                    Toast.makeText(MainActivity.this, "Categoría creada", Toast.LENGTH_SHORT).show();
                    obtenerCategorias();
                }
            }
        });

        // Configura el clic en elementos del RecyclerView para eliminarlos
        categoriaAdapter.setOnItemClickListener(new CategoriaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Implementa la lógica para eliminar la categoría en esta posición

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
                Log.d("Error", "onResponse: Error" + t);
            }
        });
    }

    private void agregarCategoria() {
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(NombreTemp);
        nuevaCategoria.setDescripcion(DescripcionTemp);

        categoriaApiManager.crearCategoria(nuevaCategoria, new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if (response.isSuccessful()) {
                    Categoria categoriaCreada = response.body();
                    obtenerCategorias(); // Actualiza la lista después de agregar
                } else {
                    // Manejar errores de la API
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                // Manejar errores de conexión
            }
        });
    }




}
