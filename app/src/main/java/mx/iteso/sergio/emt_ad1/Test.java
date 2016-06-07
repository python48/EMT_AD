package mx.iteso.sergio.emt_ad1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Test extends Fragment {

    enum states {INITIAL,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,FINAL,ERROR};
    states currentState;

    public Test() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Iniciar la maquina de estados.
        currentState =  states.INITIAL;

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);


    }


    public void siClicked(View view) {
        // Kabloey
        switch (currentState) {
            //click en hombre luego se convierte en "si".
            case INITIAL:
                Button bt = (Button) getView().findViewById(R.id.button_si);
                bt.setText("Sí");
                Button bt1 = (Button) getView().findViewById(R.id.button_no);
                bt1.setText("No");

                ImageView image = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image.setImageResource(R.drawable.test_09);//la imagen es un piercing.

                TextView tv = (TextView) getView().findViewById(R.id.questionText);
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


    public void noClicked(View view) {
        // Kabloey
        switch (currentState) {
            //click en mujer, luego se convierte en "NO".
            // Muestra la pregunta del embarazo.
            case INITIAL:
                Button bt = (Button) getView().findViewById(R.id.button_si);
                bt.setText("Sí");
                Button bt1 = (Button) getView().findViewById(R.id.button_no);
                bt1.setText("No");

                ImageView image = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image.setImageResource(R.drawable.test_06);//la imagen es la del embarazo.

                TextView tv = (TextView) getView().findViewById(R.id.questionText);
                tv.setText("¿Estás embarazada o lo tuviste en los últimos seis meses?");
                currentState =  states.ONE;
                break;
            case ONE:
                ImageView image1 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image1.setImageResource(R.drawable.test_09);

                TextView tv1 = (TextView) getView().findViewById(R.id.questionText);
                tv1.setText("¿Tú o tu pareja se han hecho algún tatuaje, piercing o acupuntura en los últimos 12 meses?");
                currentState =  states.TWO;
                break;
            case TWO:
                ImageView image2 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image2.setImageResource(R.drawable.test_07);//

                TextView tv2 = (TextView) getView().findViewById(R.id.questionText);
                tv2.setText("¿Has padecido algún problema hemorrágico o enfermedad de la sangre como anemia o exceso de glóbulos rojos?");
                currentState =  states.THREE;
                break;
            case THREE:
                ImageView image3 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image3.setImageResource(R.drawable.test_10);//

                TextView tv3 = (TextView) getView().findViewById(R.id.questionText);
                tv3.setText("¿Tienes alguna enfermedad grave o crónica de pulmones, corazón, cerebro, riñones, tiroides, o aparato digestivo?");
                currentState =  states.FOUR;
                break;
            case FOUR:
                ImageView image4 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image4.setImageResource(R.drawable.test_08);//

                TextView tv4 = (TextView) getView().findViewById(R.id.questionText);
                tv4.setText("¿Has tenido Hepatitis B después de los diez años de edad?");
                currentState =  states.FIVE;
                break;
            case FIVE:
                ImageView image5 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image5.setImageResource(R.drawable.test_04);//

                TextView tv5 = (TextView) getView().findViewById(R.id.questionText);
                tv5.setText("¿Has tenido Hepatitis C?");
                currentState =  states.SIX;
                break;
            case SIX:
                ImageView image6 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image6.setImageResource(R.drawable.test_05);//

                TextView tv6 = (TextView) getView().findViewById(R.id.questionText);
                tv6.setText("¿Estás bajo tratamiento médico actualmente?");
                currentState =  states.SEVEN;
                break;
            case SEVEN:
                ImageView image7 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image7.setImageResource(R.drawable.test_02);//

                TextView tv7 = (TextView) getView().findViewById(R.id.questionText);
                tv7.setText("¿Tienes diabetes que requiera insulina como tratamiento?");
                currentState =  states.EIGHT;
                break;
            case EIGHT:
                ImageView image8 = (ImageView) getView().findViewById(R.id.imagenDeArriba);
                image8.setImageResource(R.drawable.test_03);//

                TextView tv8 = (TextView) getView().findViewById(R.id.questionText);
                tv8.setText("¿Te consideras sano?");
                currentState =  states.FINAL;
                break;
            case FINAL:
                // mandarlo a la goma.
                currentState =  states.ERROR;
                break;


        }
    }



}
