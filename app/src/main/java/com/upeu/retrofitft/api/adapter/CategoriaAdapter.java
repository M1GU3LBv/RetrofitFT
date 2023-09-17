package com.upeu.retrofitft.api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.upeu.retrofitft.R;
import com.upeu.retrofitft.model.Categoria;
import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {
    private List<Categoria> categorias;
    public int rowlayout;
    private Context context;


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
            textViewNombreCategoria = itemView.findViewById(R.id.textViewNombreCategoria); // Reemplaza con tu ID de TextView
            textViewId = itemView.findViewById(R.id.textViewId); // Reemplaza con tu ID de TextView
            textViewDescripcionCategoria = itemView.findViewById(R.id.textViewDescripcionCategoria); // Reemplaza con tu ID de TextView
        }
    }
}
