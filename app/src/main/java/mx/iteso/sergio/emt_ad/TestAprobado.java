package mx.iteso.sergio.emt_ad;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mx.iteso.sergio.emt_ad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestAprobado extends Fragment {


    public static String HeaderTitle = "Test Aprobado";
    public static String ButtonTitle = "hacerlo de nuevo";

    public TestAprobado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_aprobado, container, false);

        final TextView tv = (TextView) view.findViewById(R.id.textView6);
        tv.setText(HeaderTitle);

        final Button button = (Button) view.findViewById(R.id.button);
        button.setText(ButtonTitle);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goToTest();
            }
        });

        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_test_aprobado, container, false);
    }

    private void goToTest() {
        Intent activityChangeIntent = new Intent(getActivity(), TestActivityN.class);
        startActivity(activityChangeIntent);
    }


}
