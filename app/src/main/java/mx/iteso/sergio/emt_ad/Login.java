package mx.iteso.sergio.emt_ad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.iteso.sergio.emt_ad.R;

import static mx.iteso.sergio.emt_ad.R.layout.fragment_login;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_login, container, false);
    }

}
