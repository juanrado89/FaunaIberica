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

public class Rapaces extends AppCompatActivity implements CarrgarAnimales, AnimalFragment.OnAnimalSeleccionadoListener {

    boolean esModoPaisaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapaces);
        esModoPaisaje = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
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
        if (esModoPaisaje) {
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
