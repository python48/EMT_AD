package mx.iteso.sergio.emt_ad;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Registro2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//http://stackoverflow.com/questions/19915031/android-how-do-i-start-or-initialize-a-fragment-from-an-activity

        setContentView(R.layout.fragment_registro);
    }
}
