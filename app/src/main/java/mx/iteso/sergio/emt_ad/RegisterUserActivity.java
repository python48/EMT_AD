package mx.iteso.sergio.emt_ad;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class RegisterUserActivity extends AppCompatActivity {


    public static String email, userName, pass, name, firstName, lastName;

    public static boolean Mibandera = false;
    //private static boolean changedBandera;
    public static void setbandera(boolean value) {
        //changedBandera = true;
        Mibandera = value;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Snackbar.make(view, "Realizando registro, espere.", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                updateStrings();
                RegisterNewUserUI.updatePass();

                String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";
                RequestPackage p = new RequestPackage();
                p.setMethod("POST");
                p.setUri(uri);

                String json = String.format("{\"funcion\":\"registro\", \"correo\":\"%s\",\"usuario\":\"%s\",\"palabrasecreta\":\"%s\",\"nombre\":\"%s\",\"APELLIDOPAT\":\"%s\",\"APELLIDOMAT\":\"%s\"}", email, userName, pass, name, firstName, lastName);
                p.setParam("Info", json);

                ApiConnector.getInstance().execute(p);

                if (Mibandera){
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("DELAYED MESSAGE:", "");
                            Snackbar.make(view, "Registro exitoso.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }, 2000);
                    goToMain();
                }else{
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("DELAYED MESSAGE:", "");
                            Snackbar.make(view, "No se pudo hacer el registro, intenta de nuevo.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }, 2000);
                }


            }
        });



    }



    private void updateStrings() {
        email = RegisterNewUserUI.Email;
        name = RegisterNewUserUI.Name;
        userName = RegisterNewUserUI.UserName;
        lastName = RegisterNewUserUI.LastName2;
        firstName = RegisterNewUserUI.LastName;
        pass = RegisterNewUserUI.Secret;
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
