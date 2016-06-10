package mx.iteso.sergio.emt_ad1;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
                //Position = position;
                final String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";
                //final String uri = "http://requestb.in/wyx8r2wy";
                RequestPackage p = new RequestPackage();
                p.setMethod("POST");
                p.setUri(uri);

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


                ApiConnector.getInstance().setHaciendoCita(true);
                String token = ApiConnector.getInstance().getToken();

                //String userName="user1", pass="user1";
                String json = String.format("{\"funcion\":\"ver_cita\", \"codigo\":\"%s\" }", token);
                p.setParam("Info", json);
                //p.setParam("Junk", Integer.toString(randData++));

                ApiConnector.getInstance().execute(p);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //ApiConnector.getInstance().setHaciendoCita(false);

                        Button mPickDate = (Button) getView().findViewById(R.id.myDatePicker);

                        mPickDate.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                            }
                        });

                        // get the current date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        // display the current date
                        updateDisplay();

                    }
                }, 2000);

                //

/*
//                LocalDateTime appoimentDate = new Date(2016,7,4);
                android.text.format.DateFormat df = new android.text.format.DateFormat();
                df.format("yyyy-MM-dd hh:mm:ss", new java.util.Date(2016,7,4));
                //*/

            }
        });
        }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }


    }
