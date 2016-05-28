package mx.iteso.sergio.emt_ad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Analisis extends Fragment {


    private View GlobalView;

    public Analisis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GlobalView = inflater.inflate(R.layout.fragment_analisis, container, false);

        populateListView();




        return GlobalView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_analisis, container, false);
    }

    private void populateListView() {
        String [] myItems = {"Aún no has agendado alguna visita :("};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                myItems);

        ListView list = (ListView) GlobalView.findViewById(R.id.listViewAnalisis);
        list.setAdapter(adapter);

    }

}
