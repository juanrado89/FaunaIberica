package com.albertorado.faunaiberica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ResumenAnimalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_animal);

        // Obtén el animal de los extras
        Animal animal = getIntent().getParcelableExtra("animal");

        // Crea y muestra el fragmento de detalles
        if (animal != null) {
            ResumenAnimalFragment resumenFragment = ResumenAnimalFragment.newInstance(animal);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container2, resumenFragment)
                    .commit();
        }
    }

    public static class ResumenAnimalFragment extends Fragment implements AnimalFragment.OnAnimalSeleccionadoListener{


        private Animal animalSeleccionado;
        ImageView imagen;
        TextView texto;

        public static ResumenAnimalFragment newInstance(Animal animal) {
            ResumenAnimalFragment fragment = new ResumenAnimalFragment();
            Bundle args = new Bundle();
            args.putParcelable("animal", animal);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                animalSeleccionado = getArguments().getParcelable("animal");

            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_resumen_animal, container, false);

            texto = view.findViewById(R.id.resumen_informacion);
            imagen = view.findViewById(R.id.imagen);
            if(animalSeleccionado.getResumen() != 0){
                InputStream inputStream = getResources().openRawResource(animalSeleccionado.getResumen());
                String resumen = convertirAString(inputStream);
                texto.setText(resumen);
                imagen.setImageResource(animalSeleccionado.getFoto());
            }else{
                InputStream inputStream = getResources().openRawResource(R.raw.error);
                String error = convertirAString(inputStream);
                texto.setText(error);
            }

            return view;
        }

        @Override
        public void onAnimalSeleccionado(Animal a) {

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            ResumenAnimalFragment detailFragment = ResumenAnimalFragment.newInstance(a);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container2, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }

        private String convertirAString(InputStream inputStream) {
            StringBuilder resultado = new StringBuilder();
            BufferedInputStream bis = new BufferedInputStream(inputStream);

            try {
                int bytesRead;
                byte[] buffer = new byte[4096];

                while ((bytesRead = bis.read(buffer)) != -1) {
                    resultado.append(new String(buffer, 0, bytesRead));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return resultado.toString();
        }

    }
}