package mx.iteso.sergio.emt_ad1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;

import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private List<Donator> donatorList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static boolean mibandera = false;
    private static boolean mibandera2 = false;
    private Object GlobalView;
    private ListView GlobalListView;

    public static void setbandera(boolean value){
        mibandera = value;
    }

    public boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog =
                    GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "No podemos conectarnos con el servicio de mapa", Toast.LENGTH_SHORT);
        }

        return false;
    }

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://mx.iteso.sergio.emt_ad1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://mx.iteso.sergio.emt_ad1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    enum states {INITIAL, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, FINAL, ERROR}
    states currentState;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //GlobalListView = (ListView) findViewById(R.id.listViewAnalisis);
        //populateListView();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Iniciar la maquina de estados.
        currentState = states.INITIAL;

        if (servicesOK()) {

        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



    }

    static int randData = 0;
    private void requestData(String uri) {

        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        //p.setUri("http://requestb.in/1kp9q0p1");

        //Name es el nombre. first name es el primer apellido, last name es el segundo. che jairo wey! me confundiste no mames!! xD.
        String userName = "furfag2004", pass = "hola4", email = "user1@mail.com", name = "thomas" , firstName = "alba", lastName = "edison";

        String json = String.format("{\"funcion\":\"ingreso\", \"usuario\":\"%s\",\"palabrasecreta\":\"%s\"}", userName, pass);

        //String json = String.format("{\"funcion\":\"registro\", \"correo\":\"%s\",\"usuario\":\"%s\",\"palabrasecreta\":\"%s\",\"nombre\":\"%s\",\"APELLIDOPAT\":\"%s\",\"APELLIDOMAT\":\"%s\"}", email, userName, pass, name, firstName, lastName);
        p.setParam("Info", json);
        p.setParam("Junk", Integer.toString(randData++));

        ApiConnector.getInstance().execute(p);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_profile:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_info:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_map:
                //Intent intent3 = new Intent(this, NewMapActivity.class);
                Intent intent3 = new Intent(this, MapsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.action_about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // boton que abrira la ventanan de login.
    public void iniciarSesionClick(View view) {
                                        //http://services.hanselandpetal.com/feeds/flowers.json      //http://services.hanselandpetal.com/restful.php
        if (isOnline()){
            //Aqui poner el cuadro de dialogo pidiendo las pinches putas credenciales!!!!. y luego que haga el login.
            refreshPage();
            mibandera2 = true;
        }
        else{
            //Toast.makeText(MainActivity.this, "La red no está dispobible", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setMessage("No hay conexión o la red no está disponible.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // OK
                        }
                    })
                    .setIcon(android.R.drawable.alert_dark_frame)
                    .show();

        }

    }


    // login prom button event.
    public void iniciarSesionClick1(View view) {
        LoginProm.updatePass();
        login(LoginProm.UserName, LoginProm.Secret);
    }


/*
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            //ya stuff here.
            LoginProm.updatePass();
        }
        return super.onKeyDown(keyCode, event);
    }*/

    //aqui va a obtener el token. iniciar la sesion.
    public void login(String un, String ps){
        //final String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";//pruebas
        final String uri = "http://cetsgdl.redlab.com.mx:8085/wshematixnet.asmx/accion";//produccion.
        //final String uri = "http://requestb.in/wyx8r2wy";
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        //p.setUri("http://requestb.in/1kp9q0p1");

        //Name es el nombre. first name es el primer apellido, last name es el segundo. che jairo wey! me confundiste no mames!! xD.
        //String userName = "", pass = "hola4", email = "user1@mail.com", name = "thomas" , firstName = "alba", lastName = "edison";

        //String userName="user1", pass="user1";
        String json = String.format("{\"funcion\":\"ingreso\", \"usuario\":\"%s\",\"palabrasecreta\":\"%s\"}", un, ps);

        //String json = String.format("{\"funcion\":\"registro\", \"correo\":\"%s\",\"usuario\":\"%s\",\"palabrasecreta\":\"%s\",\"nombre\":\"%s\",\"APELLIDOPAT\":\"%s\",\"APELLIDOMAT\":\"%s\"}", email, userName, pass, name, firstName, lastName);
        p.setParam("Info", json);
        p.setParam("Junk", Integer.toString(randData++));

        try {
            //ApiConnector.getInstance().execute(p);
            ApiConnector apicon = new ApiConnector();
            apicon.execute(p);

            Log.i("MESSAGE:", "esperando al token..");
        }catch (Exception e){
            toastThis("Error al iniciar sesión, inténtalo otra vez.");
            e.printStackTrace();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mibandera) {
                    Log.i("DELAYED MESSAGE:", "se debe ya haber recibido el token");
                    String token = ApiConnector.getInstance().getToken();
                    String json = String.format("{\"funcion\":\"obten\", \"codigo\":\"%s\"} ", token);
                    RequestPackage r = new RequestPackage();
                    r.setMethod("POST");
                    r.setUri(uri);
                    r.setParam("Info", json);

                    //Intento de Login.
                    ApiConnector a = new ApiConnector();
                    a.execute(r);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("DELAYED MESSAGE:", "Hemos esperado por");
                            //aqui hace el login correctamente y vuelve a cargar GUI. para cargar con el perfil en lugar de la ventana de login.
                            refreshPage();
                        }
                    }, 2000);

                } else{
                    //toastThis("No se pudo inicar la sesión :( ");
                    printAlert("No se pudo iniciar la sesión, intenta de nuevo");

                }


            }
        }, 2000);


    }

    private void printAlert(String message){
        new AlertDialog.Builder(this)
                .setTitle("Atención")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })/*
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void toastThis(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }

    void refreshPage(){
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    static MainActivity Instance;
    public static MainActivity getInstance()
    {
        if(Instance == null) {
            Instance = new MainActivity();
        }
        return Instance;

    }

    public void registroClick(View view) {
        //mandarlo a la ventana de hacer nuevo registro. main2 es el test activity.

        new AlertDialog.Builder(this)
                .setTitle("Espera")
                .setMessage("Para hacer un registro nuevo es necesario hacer un test, ¿desas hacerlo ahora?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        goToTest();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        /*Intent intent = new Intent(this, TestActivityN.class);
        startActivity(intent);*/
    }


    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }






    private static boolean hayCita = false;
    public static void setHayCita(boolean value){ hayCita=value; }
    public static boolean getHayCita(){return hayCita;}

    public void verSiHayCita(final View view){
        hayCita = false;
        String token = ApiConnector.getInstance().getToken();
        //final String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";//pruebas.
        final String uri = "http://cetsgdl.redlab.com.mx:8085/wshematixnet.asmx/accion";//produccion.
        //final String uri = "http://requestb.in/wyx8r2wy";
        String json = String.format("{\"funcion\":\"ver_cita\", \"codigo\":\"%s\" } ", token);
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        p.setParam("Info", json);
        //p.setParam("Junk", Integer.toString(randData++));

        //Intento de ver una cita.
        ApiConnector.getInstance().execute(p);
        //this sheet is going to refresh the hayCita.
        //esperar poqiuto.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Log.i("DELAYED MESSAGE:", message);
                //printAlert(message);
                if (hayCita) {
                    //// si hay alguna cita preguntar si desea cancelarla.
                    preguntarSiQuiereCancelarCita();
                } else {
                    //// sino mostrar luego el dialogo de seleccion de fecha.
                    DatePickerFragment dialog = new DatePickerFragment(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "Selecciona la fecha");
                    //el datepickerfragment toma la respuesta en onDateSet.
                }

            }
        }, 3000);
    }

    public static void levantarCita(String msg, final View view, final int year,final  int month,final int day){
        //Pedir al usuario que asegure la cita.
        new AlertDialog.Builder(view.getContext())
                .setTitle("Atención")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // OK. Hacer cita nueva.
                        //hacer el cagadero con el apiconector.
                        String token = ApiConnector.getInstance().getToken();
                        String json = String.format("{\"funcion\":\"asigna_cita\", \"codigo\":\"%s\" , \"fecha\":\"%s\"} ", token, Integer.toString(year) + ((month < 10 ? "0" : "") + Integer.toString(month)) + ((day < 10 ? "0" : "") + Integer.toString(day)));
                        //{"funcion":"asignaCita", "codigo":"A000000001","fecha":"20160529"}
                        //final String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";//pruebas.
                        final String uri = "http://cetsgdl.redlab.com.mx:8085/wshematixnet.asmx/accion";//produccion.
                        //final String uri = "http://requestb.in/wyx8r2wy";
                        RequestPackage p = new RequestPackage();
                        p.setMethod("POST");
                        p.setUri(uri);
                        p.setParam("Info", json);
                        //p.setParam("Junk", Integer.toString(randData++));

                        //Intento de levantar cita en el Server.
                        ApiConnector ap = new ApiConnector();
                        ap.setHaciendoCita(true);
                        ap.execute(p);
                        //hacer la mierda esta de esperar la respuesta.. ¬¬
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (message.equals("Cita Agendada")) {
                                    new AlertDialog.Builder(view.getContext())
                                            .setTitle("Muy bien")
                                            .setMessage(message);
                                    ApiConnector.getInstance().setHaciendoCita(false);
                                }
                                else {
                                    new AlertDialog.Builder(view.getContext())
                                            .setTitle("Atención")
                                            .setMessage(message + " intenta nuevamente más tarde");
                                    ApiConnector.getInstance().setHaciendoCita(false);
                                }
                            }
                        }, 2000);


                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancelar. No hacer nada.
                    }
                })
                .setIcon(android.R.drawable.alert_dark_frame)
                .show();

    }


    private static String message = "";
    public static void setMessage(String value){
        message=value;
    }

    //dialogo de fecha.
    public void showDatePickerDialog(final View view) {
        //Checar que este logueado el usuario sino mandarlo a logearse.
        if (!ApiConnector.getInstance().isLoggedIn())
        {
            new AlertDialog.Builder(this)
                    .setTitle("Aviso")
                    .setMessage("Necesitas iniciar sesión.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // OK
                            refreshPage();
                        }
                    })
                    .setIcon(android.R.drawable.alert_dark_frame)
                    .show();
            return;
        }

        ///// Si si esta logeado entonces ver si tiene ya citas.callback
        verSiHayCita(view);

        /*
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    DatePickerFragment dialog = new DatePickerFragment(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"Agendar visita");
                }
            }
        });*/
    }

    private void preguntarSiQuiereCancelarCita() {
        new AlertDialog.Builder(this)
                .setTitle("Ya hay cita agendada")
                .setMessage("¿Quieres cancelar tu cita? " + message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // OK. quiere cancelar la cita a la chingada.
                        cancelarCita();
                    }
                })
                .setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel. no quiere cancelar la cita.

                    }
                })
                .setIcon(android.R.drawable.alert_dark_frame)
                .show();
    }

    private void cancelarCita() {
        String token = ApiConnector.getInstance().getToken();
        String json = String.format("{\"funcion\":\"cancelar_cita\", \"codigo\":\"%s\" } ", token);
        //final String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";//pruebas
        final String uri = "http://cetsgdl.redlab.com.mx:8085/wshematixnet.asmx/accion";//produccion.
        //final String uri = "http://requestb.in/wyx8r2wy";
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        p.setParam("Info", json);
        //p.setParam("Junk", Integer.toString(randData++));
        //Intento de cancelar una cita.
        ApiConnector ap = new ApiConnector();
        ap.execute(p);

        //esperar la respuesta.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("DELAYED MESSAGE:", message);
                printAlert(message);
                if (message.equals("Cita cancelada")){
                    hayCita = false;
                }
            }
        }, 2000);


    }

    private void goToTest() {

        //showDatePickerDialog();
        Intent intent = new Intent(this, TestActivityN.class);
        startActivity(intent);
    }

    void aLaGoma(){
        Intent intent = new Intent(this, TestReprobado.class);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     *//*
    public static class PlaceholderFragment extends Fragment {
        *//**
     * The fragment argument representing the section number for this
     * fragment.
     *//*
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        */

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     *//*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }*/
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    if (!mibandera){
                        if (mibandera2)
                            return new LoginProm();
                        else
                            return new Login();
                    }
                    else
                        return new Perfil();
                case 1:
                    return new TestAprobado();
                    //return new Test();
                case 2:
                    return new Analisis();
                default:
                    return new Perfil();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Perfil";
                case 1:
                    return "Test";
                case 2:
                    return "Análisis";
            }
            return null;
        }
    }


/*
    //<Params, Progress, Result>
    private class MyTask extends AsyncTask<RequestPackage, String, String> {

        private  String token;

        @Override
        protected void onPreExecute() {
            System.out.println("Starting task...");
        }

        @Override
        protected String doInBackground(RequestPackage... params) {

            String content = HttpManager.getData(params[0]);
            return content;

            *//*for (int i = 0; i<params.length; i++){
                publishProgress("Working with" + params[i]);
            }
            return "task completed";*//*
        }

        @Override
        protected void onProgressUpdate(String... values) {
            System.out.println("Working with dis sh!t --> " + values[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            //System.out.println(result);

            String InJson = "", res = result;
            InJson = res.substring(res.indexOf(">{") + 1, res.indexOf("}<")+1);

            //JSONDeserializer
            String inputJSONString = InJson; // Your string JSON here
            try {
                JSONObject jObject = new JSONObject(inputJSONString);
                Iterator<String> keys = jObject.keys();



                while( keys.hasNext() ) {
                    String key = keys.next();
                    String value = jObject.getString(key);
                    if (key.equals("respuesta")) {
                        if (value.equals("0"))
                            //login failed ;
                            System.err.println("Login failed");
                        else if (value.equals("1"))
                        {
                            //token = JsonDe.codigo;
                            //loggedIn = true;
                            //SetLocalStorage()
                        }
                    }if (key.equals("codigo")) {
                        token = value;
                        //token = JsonDe.codigo;
                        //loggedIn = true;
                        //SetLocalStorage()
                    }

                }
            }
            catch (Exception e){
                e.printStackTrace();
            }


           *//* if (JsonDe.respuesta == "0")
            {
                //LoginFailed
            }
            else
            {
                if (JsonDe.respuesta == "1")
                {
                    token = JsonDe.codigo;
                    loggedIn = true;
                    //SetLocalStorage()
                }

            }*//*

            //donatorList = JSONParser.parseFeed(result);

            //assert donatorList != null;
            //for (Donator don : donatorList
            //        ) {
            //    System.out.println(don.getName());
            //}
            //System.out.println(result);
        }
    }*/
}
