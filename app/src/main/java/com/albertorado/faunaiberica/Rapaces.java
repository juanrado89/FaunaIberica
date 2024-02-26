package com.albertorado.faunaiberica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rapaces extends AppCompatActivity implements CarrgarAnimales, AnimalFragment.OnAnimalSeleccionadoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapaces);

        cargarAnimales();
    }

    private void cargarAnimales() {

        int[] idsResumen = {
                R.raw.aguila,
                0,
                0,
                0,
                R.raw.halcon,
                0
        };
        int[] idsRecursos = {
                R.drawable.aguila,
                R.drawable.buho,
                R.drawable.buitre,
                R.drawable.cernicalo,
                R.drawable.halcon,
                R.drawable.quebrantahuesos
        };
        String[] animales = {
                "Aguila",
                "Buho",
                "Buitre",
                "Cernicalo",
                "Halcon",
                "Quebrantahuesos"
        };

        List<Animal> resultado = new ArrayList<>();


        for (int i = 0; i < animales.length; i++) {
            resultado.add(new Animal(animales[i], idsRecursos[i], idsResumen[i]));
        }

        // Llamar a onAnimalesCargados después de cargar los animales
        onAnimalesCargados(resultado);
    }

    @Override
    public void onAnimalesCargados(List<Animal> listaAnimales) {
        AnimalFragment animalFragment = AnimalFragment.newInstance(1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("listaAnimales", (Serializable) listaAnimales);
        animalFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, animalFragment)
                .commit();
    }

    @Override
    public void onAnimalSeleccionado(Animal a) {
        // Este método se llama desde AnimalFragment cuando se selecciona un animal
        Intent intent = new Intent(this, ResumenAnimalActivity.class);
        intent.putExtra("animal", (Parcelable) a);
        startActivity(intent);
    }


}
