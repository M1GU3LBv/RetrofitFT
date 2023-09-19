package com.upeu.retrofitft.api.adapter;

import android.content.Context;
import android.view.*;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.upeu.retrofitft.R;
import com.upeu.retrofitft.api.manager.CategoriaApiManager;
import com.upeu.retrofitft.api.service.CategoriaApiService;
import com.upeu.retrofitft.model.Categoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {
    private List<Categoria> categorias;
    private Context context;
    private OnItemClickListener listener;
    private CategoriaApiService categoriaApiService;
    private CategoriaApiManager categoriaApiManager;
    public CategoriaAdapter(Context context, CategoriaApiManager apiManager) {
        this.context = context;
        this.categoriaApiManager = apiManager;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CategoriaAdapter(Context context) {
        this.context = context;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        notifyDataSetChanged(); // Notifica al RecyclerView que los datos han cambiado
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.textViewId.setText("ID: " + categoria.getId());
        holder.textViewNombreCategoria.setText("Nombre: " + categoria.getNombre());
        holder.textViewDescripcionCategoria.setText("Descripción: " + categoria.getDescripcion());

        // Configura el OnClickListener para el elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupMenu(v, position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorias != null ? categorias.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreCategoria;
        TextView textViewId;
        TextView textViewDescripcionCategoria;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombreCategoria = itemView.findViewById(R.id.textViewNombreCategoria);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewDescripcionCategoria = itemView.findViewById(R.id.textViewDescripcionCategoria);
        }
    }
    private void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.item_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_edit) {
                    // Implementa la lógica para editar el elemento en la posición dada
                } else if (item.getItemId() == R.id.action_delete) {
                    int id = categorias.get(position).getId();
                    Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();


                    categoriaApiManager.eliminarCategoria(id, new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            if (response.isSuccessful()) {
                                // Eliminación exitosa, realiza las acciones necesarias
                                categorias.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(context, "Categoría eliminada", Toast.LENGTH_SHORT).show();
                            } else {
                                // Maneja errores de eliminación (por ejemplo, categoría no encontrada)
                                Toast.makeText(context, "Error al eliminar la categoría", Toast.LENGTH_SHORT).show();
                            }
                        }

                                @Override
                                public void onFailure(Call<Object> call, Throwable t) {

                                }


                            }
                            );

                }
                return true;
            }
        });


        popupMenu.show();}


}
