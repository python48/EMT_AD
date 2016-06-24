package mx.iteso.sergio.emt_ad1;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import mx.iteso.sergio.emt_ad1.R;

public class BloodTypeSelectionActivity extends AppCompatActivity {

    ///Buttons
    private Button buttonA;
    private Button buttonAB;
    private Button buttonB;
    private Button buttonO;
    private Button buttonPlus;
    private Button buttonMinus;

    TextView sangreSeleccionada;

    String selection = "+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonA = (Button) findViewById(R.id.boton_sangre_a);
        buttonB = (Button) findViewById(R.id.boton_sangre_b);
        buttonAB = (Button) findViewById(R.id.boton_sangre_ab);
        buttonO = (Button) findViewById(R.id.boton_sangre_o);
        buttonPlus = (Button) findViewById(R.id.boton_sangre_plus);
        buttonMinus = (Button) findViewById(R.id.boton_sangre_minus);

        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        //sangreSeleccionada.setText("N/D");


        setContentView(R.layout.activity_blood_type_selection);
    }


    public void buttonAClicked(View view)
    {
        String sign = selection.substring(selection.length()-1,selection.length());
        selection = "A" + sign;
        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        sangreSeleccionada.setText(selection);
    }
    public void buttonBClicked(View view)
    {
        String sign = selection.substring(selection.length()-1,selection.length());
        selection = "B" + sign;
        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        sangreSeleccionada.setText(selection);
    }
    public void buttonABClicked(View view)
    {
        String sign = selection.substring(selection.length()-1,selection.length());
        selection = "C" + sign;
        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        sangreSeleccionada.setText("AB"+sign);
    }
    public void buttonOClicked(View view)
    {
        String sign = selection.substring(selection.length()-1,selection.length());
        selection = "O" + sign;
        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        sangreSeleccionada.setText(selection);
    }


    public void buttonPlusClicked(View view)
    {
        String letter = selection.substring(0,selection.length()-1);
        if (letter.equals("C"))
            letter = "AB";
        selection = letter + "+";
        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        sangreSeleccionada.setText(selection);
    }
    public void buttonMinusClicked(View view)
    {
        String letter = selection.substring(0,selection.length()-1);
        if (letter.equals("C"))
            letter = "AB";
        selection = letter + "-";
        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        sangreSeleccionada.setText(selection);
    }


    public void guardarCambioClicked(View view)
    {
        sangreSeleccionada = (TextView) findViewById(R.id.textViewSangre);
        String sangre = selection ;
        if (sangre.length()<=1)
        {
            new AlertDialog.Builder(view.getContext())
                    .setTitle("Alerta")
                    .setMessage("El tipo de sangre no quedo definido")
                    .show();
            RegistroFragment.Sangre = "N/D";
        }
        else
        {
            RegistroFragment.Sangre = sangre;
            Intent intent = new Intent(this, Registro2.class);
            startActivity(intent);
        }


    }

}
