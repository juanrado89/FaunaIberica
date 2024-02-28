package com.albertorado.faunaiberica.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.albertorado.faunaiberica.entities.Animal;
import com.albertorado.faunaiberica.R;
import com.albertorado.faunaiberica.adapters.MyArrayAdapter;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class AnimalFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AnimalFragment() {
    }

    // TODO: Customize parameter initialization
    public static AnimalFragment newInstance(int columnCount) {
        AnimalFragment fragment = new AnimalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    protected MyArrayAdapter aa;
    private OnAnimalSeleccionadoListener _listener;

    boolean esModoPaisaje;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        esModoPaisaje = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_item_list, container, false);


        List<Animal> listaAnimales = (List<Animal>) getArguments().getSerializable("listaAnimales");


        aa = new MyArrayAdapter(getActivity(), listaAnimales);
        ListView lv = (ListView) result.findViewById(R.id.listView);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Animal a = aa.getItem(position);
                if (_listener != null)
                    _listener.onAnimalSeleccionado(a);

                if (esModoPaisaje) {
                    ResumenAnimalFragment resumenFragment = ResumenAnimalFragment.newInstance(a);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container2, resumenFragment)
                            .commit();
                } else {
                    ResumenAnimalFragment resumenFragment = ResumenAnimalFragment.newInstance(a);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, resumenFragment).addToBackStack(null)
                            .commit();
                }
            }
        });
        return result;
    }

    public interface OnAnimalSeleccionadoListener {
        public void onAnimalSeleccionado(Animal a);
    }

}