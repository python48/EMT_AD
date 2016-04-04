package mx.iteso.sergio.emt_ad;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends AppCompatActivity {


    GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 9001;

    public boolean servicesOK()
    {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS)
        {
            return true;
        }else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable))
        {
            Dialog dialog =
                    GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else
        {
            Toast.makeText(this,"No podemos conectarnos con el servicio de mapa",Toast.LENGTH_SHORT);
        }

        return false;
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    enum states {INITIAL,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,FINAL,ERROR};
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Iniciar la maquina de estados.
        currentState =  states.INITIAL;

        if (servicesOK())
        {

        }
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
                currentState =  states.ONE;
                break;
            case ONE:
                ImageView image1 = (ImageView) findViewById(R.id.imagenDeArriba);
                image1.setImageResource(R.drawable.test_09);

                TextView tv1 = (TextView) findViewById(R.id.questionText);
                tv1.setText("¿Tú o tu pareja se han hecho algún tatuaje, piercing o acupuntura en los últimos 12 meses?");
                currentState =  states.TWO;
                break;
            case TWO:
                ImageView image2 = (ImageView) findViewById(R.id.imagenDeArriba);
                image2.setImageResource(R.drawable.test_07);//

                TextView tv2 = (TextView) findViewById(R.id.questionText);
                tv2.setText("¿Has padecido algún problema hemorrágico o enfermedad de la sangre como anemia o exceso de glóbulos rojos?");
                currentState =  states.THREE;
                break;
            case THREE:
                ImageView image3 = (ImageView) findViewById(R.id.imagenDeArriba);
                image3.setImageResource(R.drawable.test_10);//

                TextView tv3 = (TextView) findViewById(R.id.questionText);
                tv3.setText("¿Tienes alguna enfermedad grave o crónica de pulmones, corazón, cerebro, riñones, tiroides, o aparato digestivo?");
                currentState =  states.FOUR;
                break;
            case FOUR:
                ImageView image4 = (ImageView) findViewById(R.id.imagenDeArriba);
                image4.setImageResource(R.drawable.test_08);//

                TextView tv4 = (TextView) findViewById(R.id.questionText);
                tv4.setText("¿Has tenido Hepatitis B después de los diez años de edad?");
                currentState =  states.FIVE;
                break;
            case FIVE:
                ImageView image5 = (ImageView) findViewById(R.id.imagenDeArriba);
                image5.setImageResource(R.drawable.test_04);//

                TextView tv5 = (TextView) findViewById(R.id.questionText);
                tv5.setText("¿Has tenido Hepatitis C?");
                currentState =  states.SIX;
                break;
            case SIX:
                ImageView image6 = (ImageView) findViewById(R.id.imagenDeArriba);
                image6.setImageResource(R.drawable.test_05);//

                TextView tv6 = (TextView) findViewById(R.id.questionText);
                tv6.setText("¿Estás bajo tratamiento médico actualmente?");
                currentState =  states.SEVEN;
                break;
            case SEVEN:
                ImageView image7 = (ImageView) findViewById(R.id.imagenDeArriba);
                image7.setImageResource(R.drawable.test_02);//

                TextView tv7 = (TextView) findViewById(R.id.questionText);
                tv7.setText("¿Tienes diabetes que requiera insulina como tratamiento?");
                currentState =  states.EIGHT;
                break;
            case EIGHT:
                ImageView image8 = (ImageView) findViewById(R.id.imagenDeArriba);
                image8.setImageResource(R.drawable.test_03);//

                TextView tv8 = (TextView) findViewById(R.id.questionText);
                tv8.setText("¿Te consideras sano?");
                currentState =  states.FINAL;
                break;
            case FINAL:
                // mandarlo a la goma.
                currentState =  states.ERROR;
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
                currentState =  states.TWO;
                break;
            case ONE:
                // a la burguer.
                currentState =  states.ERROR;
                break;
            case TWO:
                //a la burguer.
                currentState =  states.ERROR;
                break;
            case THREE:
                //a la burguer.
                currentState =  states.ERROR;
                break;
            case FOUR:
                //a la burguer.
                currentState =  states.ERROR;
                break;
            case FIVE:
                //a la burguer.
                currentState =  states.ERROR;
                break;
            case SIX:
                //a la burguer.
                currentState =  states.ERROR;
                break;
            case SEVEN:
                //a la burguer.
                currentState =  states.ERROR;
                break;
            case EIGHT:
                //a la burguer.
                currentState =  states.ERROR;
                break;
            case FINAL:
                //Mandarlo a la pantalla de TEST APROBADO.
                currentState =  states.FINAL;
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

        *//**
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
            switch (position){
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
}
