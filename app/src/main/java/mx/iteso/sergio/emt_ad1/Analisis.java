package mx.iteso.sergio.emt_ad1;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Analisis extends Fragment {


    private static final int  DATE_DIALOG_ID = 0;
    private View GlobalView;

    public static Button citaButton ;
    public static TextView citaHeader ;
    public static String citaButtonTxt = "Cargando...";
    public static String citaHeaderTxt = "Cargando...";

    public Analisis() {
        // Required empty public constructor
    }

    public static void updateText()
    {
        citaHeader.setText(citaHeaderTxt);
        citaButton.setText(citaButtonTxt);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GlobalView = inflater.inflate(R.layout.fragment_analisis, container, false);
        citaHeader = (TextView) GlobalView.findViewById(R.id.citaHeader);
        citaButton = (Button) GlobalView.findViewById(R.id.txtdate);
        citaHeader.setText(citaHeaderTxt);
        citaButton.setText(citaButtonTxt);

        //populateListView();
        /*///// Date Dialog
        EditText txtDate = (EditText) getView().findViewById(R.id.txtdate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){

                }
            }
        });*/

        return GlobalView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_analisis, container, false);
    }



/*

    private void populateListView() {
        String [] myItems = {"Agenda una visita"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                myItems);

        ListView list = (ListView) GlobalView.findViewById(R.id.listViewAnalisis);
        list.setAdapter(adapter);

        //Evento que ve la cita. si hay cita  te dice que dia, sino te lleva a agendar una con un datepicker.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Checar que este logueado el usuario.
                if (!ApiConnector.getInstance().isLoggedIn()) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Alerta")
                            .setMessage("Necesitas iniciar sesión.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // OK
                                }
                            })
                            .setIcon(android.R.drawable.alert_dark_frame)
                            .show();

                    return;
                }

                //Position = position;
                final String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";
                RequestPackage p = new RequestPackage();
                p.setMethod("POST");
                p.setUri(uri);

                String token = ApiConnector.getInstance().getToken();
                //String userName="user1", pass="user1";
                String json = String.format("{\"funcion\":\"ver_cita\", \"codigo\":\"%s\" }", token);
                p.setParam("Info", json);
                //p.setParam("Junk", Integer.toString(randData++));
                ApiConnector.getInstance().execute(p);
//ApiConnector.getInstance().setHaciendoCita(true);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //do shit here.
                    }
                }, 2000);

            }
        });
        }
*/

    }
