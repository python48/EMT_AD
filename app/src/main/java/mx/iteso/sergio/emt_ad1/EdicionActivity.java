package mx.iteso.sergio.emt_ad1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class EdicionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//http://stackoverflow.com/questions/19915031/android-how-do-i-start-or-initialize-a-fragment-from-an-activity

        setContentView(R.layout.activity_edicion2);
        //setContentView(R.layout.fragment_registro);
    }
}
