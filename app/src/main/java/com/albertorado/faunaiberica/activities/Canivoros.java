package com.albertorado.faunaiberica.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import com.albertorado.faunaiberica.R;
import com.albertorado.faunaiberica.entities.Animal;
import com.albertorado.faunaiberica.fragments.AnimalFragment;
import com.albertorado.faunaiberica.fragments.ResumenAnimalFragment;
import com.albertorado.faunaiberica.helperinterface.CarrgarAnimales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Canivoros extends AppCompatActivity implements CarrgarAnimales, AnimalFragment.OnAnimalSeleccionadoListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canivoros);

        configuration = getResources().getConfiguration();
        esModoPaisaje = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;
        cargarAnimales();
    }
    boolean esModoPaisaje;
    Configuration configuration;

    private void cargarAnimales() {

        int[] idsResumen = {
                0,
                0,
                R.raw.lince,
                R.raw.lobo,
                0,
                0
        };
        int[] idsRecursos = {
                R.drawable.comadreja,
                R.drawable.gineta,
                R.drawable.lince,
                R.drawable.lobo,
                R.drawable.nutria,
                R.drawable.zorro
        };
        String[] animales = {
                "Comadreja",
                "Gineta",
                "Lince",
                "Lobo",
                "Nutria",
                "Zorro"
        };

        List<Animal> resultado = new ArrayList<>();

        for (int i = 0; i < animales.length; i++) {
            resultado.add(new Animal(animales[i], idsRecursos[i], idsResumen[i]));
        }
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
        if (esModoPaisaje || (configuration.screenWidthDp >= 400)) {
            ResumenAnimalFragment resumenFragment = ResumenAnimalFragment.newInstance(a);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container2, resumenFragment)
                    .commit();
        } else {
            ResumenAnimalFragment resumenFragment = ResumenAnimalFragment.newInstance(a);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, resumenFragment).addToBackStack(null)
                    .commit();
        }

    }

}
