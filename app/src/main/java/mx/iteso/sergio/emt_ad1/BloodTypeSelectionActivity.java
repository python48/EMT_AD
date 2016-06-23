package mx.iteso.sergio.emt_ad1;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.iteso.sergio.emt_ad1.R;

public class BloodTypeSelectionActivity extends AppCompatActivity {

    ///Buttons
    Button buttonA;
    Button buttonAB;
    Button buttonB;
    Button buttonO;
    Button buttonPlus;
    Button buttonMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonA = (Button) findViewById(R.id.boton_sangre_a);
        buttonB = (Button) findViewById(R.id.boton_sangre_b);
        buttonAB = (Button) findViewById(R.id.boton_sangre_ab);
        buttonO = (Button) findViewById(R.id.boton_sangre_o);
        buttonPlus = (Button) findViewById(R.id.boton_sangre_plus);
        buttonMinus = (Button) findViewById(R.id.boton_sangre_minus);

        setContentView(R.layout.activity_blood_type_selection);
    }


    public void buttonAClicked(View view)
    {
        //buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_shape_alternate));
    }

}
