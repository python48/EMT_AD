package mx.iteso.sergio.emt_ad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil extends Fragment {


    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ApiConnector connector = ApiConnector.getInstance();
        ApiConnector.UserData user = connector.getActiveUser();

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        TextView tv = (TextView) view.findViewById(R.id.nameTV);
        tv.setText(user.get_nombre());
        TextView tv1 = (TextView) view.findViewById(R.id.tipo_sangreTV);
        tv1.setText(user.get_tipo_sangre());
        TextView tv2 = (TextView) view.findViewById(R.id.correoTV);
        tv2.setText(user.getCorreo());

        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

}
