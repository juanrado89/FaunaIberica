package com.albertorado.faunaiberica.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertorado.faunaiberica.entities.Animal;
import com.albertorado.faunaiberica.R;

public class MyArrayAdapter extends ArrayAdapter<Animal> {
    class ViewHolder {
        public ImageView foto;
        public TextView nombre;
    }
    public MyArrayAdapter(Context context, Animal[] animales) {
        super(context, 0, animales);
    }
    public MyArrayAdapter(Context context,
                          java.util.List<Animal> animales) {
        super(context, 0, animales);
    }
    public int getViewTypeCount() {
        return 2;
    }
    public int getItemViewType(int position) {
        return (getItem(position).getNombre() == null)?1:0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Animal a = getItem(position);
        View v = convertView;
        ViewHolder vh;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item, parent, false);
            vh = new ViewHolder();
            vh.foto = v.findViewById(R.id.imagen_animal);
            vh.nombre = v.findViewById(R.id.nombre_animal);
            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }

        vh.nombre.setText(a.getNombre());
        vh.foto.setImageResource(a.getFoto());

        return v;
    } // getView
    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public long getItemId(int position) {
        Animal a = getItem(position);
        return a.getNombre().hashCode();
    }
}
