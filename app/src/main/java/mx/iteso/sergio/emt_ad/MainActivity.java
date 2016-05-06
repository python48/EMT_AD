package mx.iteso.sergio.emt_ad;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private List<Donator> donatorList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //private String token;

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
                Uri.parse("android-app://mx.iteso.sergio.emt_ad/http/host/path")
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
                Uri.parse("android-app://mx.iteso.sergio.emt_ad/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    enum states {INITIAL, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, FINAL, ERROR}

    ;
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

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline())//http://services.hanselandpetal.com/feeds/flowers.json      //http://services.hanselandpetal.com/restful.php
                    requestData("http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion");//http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion
                else
                    Toast.makeText(MainActivity.this, "Red no está dispobible", Toast.LENGTH_SHORT).show();
            }
        });

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
        String userName = "user16", pass = "user16", email = "user16@mail.com", name = "thomas" , firstName = "alba", lastName = "edison";

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
                Intent intent3 = new Intent(this, NewMapActivity.class);
                startActivity(intent3);
                return true;
            case R.id.action_about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void noClicked(View view) {
        // Kabloey
        switch (currentState) {
            //click en mujer, luego se convierte en "NO".
            // Muestra la pregunta del embarazo.
            case INITIAL:
                Button bt = (Button) findViewById(R.id.button_si);
                bt.setText("Sí");
                Button bt1 = (Button) findViewById(R.id.button_no);
                bt1.setText("No");

                ImageView image = (ImageView) findViewById(R.id.imagenDeArriba);
                image.setImageResource(R.drawable.test_06);//la imagen es la del embarazo.

                TextView tv = (TextView) findViewById(R.id.questionText);
                tv.setText("¿Estás embarazada o lo tuviste en los últimos seis meses?");
                currentState = states.ONE;
                break;
            case ONE:
                ImageView image1 = (ImageView) findViewById(R.id.imagenDeArriba);
                image1.setImageResource(R.drawable.test_09);

                TextView tv1 = (TextView) findViewById(R.id.questionText);
                tv1.setText("¿Tú o tu pareja se han hecho algún tatuaje, piercing o acupuntura en los últimos 12 meses?");
                currentState = states.TWO;
                break;
            case TWO:
                ImageView image2 = (ImageView) findViewById(R.id.imagenDeArriba);
                image2.setImageResource(R.drawable.test_07);//

                TextView tv2 = (TextView) findViewById(R.id.questionText);
                tv2.setText("¿Has padecido algún problema hemorrágico o enfermedad de la sangre como anemia o exceso de glóbulos rojos?");
                currentState = states.THREE;
                break;
            case THREE:
                ImageView image3 = (ImageView) findViewById(R.id.imagenDeArriba);
                image3.setImageResource(R.drawable.test_10);//

                TextView tv3 = (TextView) findViewById(R.id.questionText);
                tv3.setText("¿Tienes alguna enfermedad grave o crónica de pulmones, corazón, cerebro, riñones, tiroides, o aparato digestivo?");
                currentState = states.FOUR;
                break;
            case FOUR:
                ImageView image4 = (ImageView) findViewById(R.id.imagenDeArriba);
                image4.setImageResource(R.drawable.test_08);//

                TextView tv4 = (TextView) findViewById(R.id.questionText);
                tv4.setText("¿Has tenido Hepatitis B después de los diez años de edad?");
                currentState = states.FIVE;
                break;
            case FIVE:
                ImageView image5 = (ImageView) findViewById(R.id.imagenDeArriba);
                image5.setImageResource(R.drawable.test_04);//

                TextView tv5 = (TextView) findViewById(R.id.questionText);
                tv5.setText("¿Has tenido Hepatitis C?");
                currentState = states.SIX;
                break;
            case SIX:
                ImageView image6 = (ImageView) findViewById(R.id.imagenDeArriba);
                image6.setImageResource(R.drawable.test_05);//

                TextView tv6 = (TextView) findViewById(R.id.questionText);
                tv6.setText("¿Estás bajo tratamiento médico actualmente?");
                currentState = states.SEVEN;
                break;
            case SEVEN:
                ImageView image7 = (ImageView) findViewById(R.id.imagenDeArriba);
                image7.setImageResource(R.drawable.test_02);//

                TextView tv7 = (TextView) findViewById(R.id.questionText);
                tv7.setText("¿Tienes diabetes que requiera insulina como tratamiento?");
                currentState = states.EIGHT;
                break;
            case EIGHT:
                ImageView image8 = (ImageView) findViewById(R.id.imagenDeArriba);
                image8.setImageResource(R.drawable.test_03);//

                TextView tv8 = (TextView) findViewById(R.id.questionText);
                tv8.setText("¿Te consideras sano?");
                currentState = states.FINAL;
                break;
            case FINAL:
                // mandarlo a la goma.
                currentState = states.ERROR;
                break;


        }
    }

    public void siClicked(View view) {
        // Kabloey
        switch (currentState) {
            //click en hombre luego se convierte en "si".
            case INITIAL:
                Button bt = (Button) findViewById(R.id.button_si);
                bt.setText("Sí");
                Button bt1 = (Button) findViewById(R.id.button_no);
                bt1.setText("No");

                ImageView image = (ImageView) findViewById(R.id.imagenDeArriba);
                image.setImageResource(R.drawable.test_09);//la imagen es un piercing.

                TextView tv = (TextView) findViewById(R.id.questionText);
                tv.setText("¿Tú o tu pareja se han hecho algún tatuaje, piercing o acupuntura en los últimos 12 meses?");
                currentState = states.TWO;
                break;
            case ONE:
                // a la burguer.
                currentState = states.ERROR;
                break;
            case TWO:
                //a la burguer.
                currentState = states.ERROR;
                break;
            case THREE:
                //a la burguer.
                currentState = states.ERROR;
                break;
            case FOUR:
                //a la burguer.
                currentState = states.ERROR;
                break;
            case FIVE:
                //a la burguer.
                currentState = states.ERROR;
                break;
            case SIX:
                //a la burguer.
                currentState = states.ERROR;
                break;
            case SEVEN:
                //a la burguer.
                currentState = states.ERROR;
                break;
            case EIGHT:
                //a la burguer.
                currentState = states.ERROR;
                break;
            case FINAL:
                //Mandarlo a la pantalla de TEST APROBADO.
                currentState = states.FINAL;
                break;


        }
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
                    return new Perfil();
                case 1:
                    return new Test();
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

            /*for (int i = 0; i<params.length; i++){
                publishProgress("Working with" + params[i]);
            }
            return "task completed";*/
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


           /* if (JsonDe.respuesta == "0")
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

            }*/

            //donatorList = JSONParser.parseFeed(result);

            //assert donatorList != null;
            //for (Donator don : donatorList
            //        ) {
            //    System.out.println(don.getName());
            //}
            //System.out.println(result);
        }
    }
}
