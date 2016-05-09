package mx.iteso.sergio.emt_ad;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import mx.iteso.sergio.emt_ad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginProm extends Fragment {

    //public fields.
    public static String UserName = "";
    public static String Secret = "";
    private static EditText PassPr;

    public LoginProm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //(ImageView) getView().findViewById(R.id.foo);
        View view = inflater.inflate(R.layout.fragment_login_prom, container, false);

        final EditText username = (EditText) view.findViewById(R.id.UserNameProm);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    LoginProm.UserName = username.getText().toString();
                } else {
                        username.setText("");
                }
            }
        });

        final EditText passprom = (EditText) view.findViewById(R.id.PasswordProm);
        passprom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    LoginProm.Secret = passprom.getText().toString();
                }
            }
        });
        PassPr = passprom;
        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login_prom, container, false);
    }

    public static void updatePass(){
        LoginProm.Secret = PassPr.getText().toString();
    }

    /*
final EditText UserNametxtEdit = (EditText) getView().findViewById(R.id.UserNameProm);
    UserNametxtEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                // code to execute when EditText loses focus
                LoginProm.UserName = UserNametxtEdit.getText().toString();
            }
        }
    });

    final EditText PassTxtEdit = (EditText) getView().findViewById(R.id.PasswordProm);

    PassTxtEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                // code to execute when EditText loses focus
                LoginProm.Secret = PassTxtEdit.getText().toString();
            }
        }
    });*/


}
