package mx.iteso.sergio.emt_ad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mx.iteso.sergio.emt_ad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestAprobado extends Fragment {


    public TestAprobado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_aprobado, container, false);
    }


}
