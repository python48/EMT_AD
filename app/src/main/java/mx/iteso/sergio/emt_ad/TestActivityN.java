package mx.iteso.sergio.emt_ad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TestActivityN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity_n);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Iniciar la maquina de estados.
        currentState = states.INITIAL;
    }

    states currentState;

    public void chingaTuMadre(View view) {
        Intent intent1 = new Intent(this, Registro2.class);
        startActivity(intent1);
    }


    enum states {INITIAL, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, FINAL, ERROR}

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
                image.setImageResource(R.drawable.test_06_1);//la imagen es la del embarazo.

                TextView tv = (TextView) findViewById(R.id.questionText);
                tv.setText("¿Estás embarazada o lo tuviste en los últimos seis meses?");
                currentState = states.ONE;
                break;
            case ONE:
                ImageView image1 = (ImageView) findViewById(R.id.imagenDeArriba);
                image1.setImageResource(R.drawable.test_09_1);

                TextView tv1 = (TextView) findViewById(R.id.questionText);
                tv1.setText("¿Tú o tu pareja se han hecho algún tatuaje, piercing o acupuntura en los últimos 12 meses?");
                currentState = states.TWO;
                break;
            case TWO:
                ImageView image2 = (ImageView) findViewById(R.id.imagenDeArriba);
                image2.setImageResource(R.drawable.test_07_1);//

                TextView tv2 = (TextView) findViewById(R.id.questionText);
                tv2.setText("¿Has padecido algún problema hemorrágico o enfermedad de la sangre como anemia o exceso de glóbulos rojos?");
                currentState = states.THREE;
                break;
            case THREE:
                ImageView image3 = (ImageView) findViewById(R.id.imagenDeArriba);
                image3.setImageResource(R.drawable.test_10_1);//

                TextView tv3 = (TextView) findViewById(R.id.questionText);
                tv3.setText("¿Tienes alguna enfermedad grave o crónica de pulmones, corazón, cerebro, riñones, tiroides, o aparato digestivo?");
                currentState = states.FOUR;
                break;
            case FOUR:
                ImageView image4 = (ImageView) findViewById(R.id.imagenDeArriba);
                image4.setImageResource(R.drawable.test_08_1);//

                TextView tv4 = (TextView) findViewById(R.id.questionText);
                tv4.setText("¿Has tenido Hepatitis B después de los diez años de edad?");
                currentState = states.FIVE;
                break;
            case FIVE:
                ImageView image5 = (ImageView) findViewById(R.id.imagenDeArriba);
                image5.setImageResource(R.drawable.test_04_1);//

                TextView tv5 = (TextView) findViewById(R.id.questionText);
                tv5.setText("¿Has tenido Hepatitis C?");
                currentState = states.SIX;
                break;
            case SIX:
                ImageView image6 = (ImageView) findViewById(R.id.imagenDeArriba);
                image6.setImageResource(R.drawable.test_05_1);//

                TextView tv6 = (TextView) findViewById(R.id.questionText);
                tv6.setText("¿Estás bajo tratamiento médico actualmente?");
                currentState = states.SEVEN;
                break;
            case SEVEN:
                ImageView image7 = (ImageView) findViewById(R.id.imagenDeArriba);
                image7.setImageResource(R.drawable.test_02_1);//

                TextView tv7 = (TextView) findViewById(R.id.questionText);
                tv7.setText("¿Tienes diabetes que requiera insulina como tratamiento?");
                currentState = states.EIGHT;
                break;
            case EIGHT:
                ImageView image8 = (ImageView) findViewById(R.id.imagenDeArriba);
                image8.setImageResource(R.drawable.test_03_1);//

                TextView tv8 = (TextView) findViewById(R.id.questionText);
                tv8.setText("¿Te consideras sano?");
                currentState = states.FINAL;
                break;
            case FINAL:
                // mandarlo a la goma.
                aLaGoma();
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
                image.setImageResource(R.drawable.test_09_1);//la imagen es un piercing.

                TextView tv = (TextView) findViewById(R.id.questionText);
                tv.setText("¿Tú o tu pareja se han hecho algún tatuaje, piercing o acupuntura en los últimos 12 meses?");
                currentState = states.TWO;
                break;
            case ONE:
                // a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case TWO:
                //a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case THREE:
                //a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case FOUR:
                //a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case FIVE:
                //a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case SIX:
                //a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case SEVEN:
                //a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case EIGHT:
                //a la burguer.
                aLaGoma();
                currentState = states.ERROR;
                break;
            case FINAL:
                //Mandarlo a la pantalla de TEST APROBADO.
                goToTestApproved();
                currentState = states.FINAL;
                break;


        }
    }

    private void aLaGoma() {
        /*Intent intent = new Intent(this, TestReprobado.class);
        startActivity(intent);*/
        setContentView(R.layout.fragment_test_reprobado);
    }

    private void goToTestApproved(){
        /*Intent intent = new Intent(this, TestAprobado.class);
        startActivity(intent);*/
        TestAprobado.HeaderTitle = "Has Aprobado";
        TestAprobado.ButtonTitle = "Hacer registro";
        setContentView(R.layout.fragment_test_aprobado);
    }

    public void res_click(View view) {
        Intent intent = new Intent(this, InfoRestrictions.class);
        startActivity(intent);
    }

    //maneja el evento del boton de registro del fragment_test_aprobado.
    public void chingaTuMadre() {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

}
