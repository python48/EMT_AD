package mx.iteso.sergio.emt_ad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import mx.iteso.sergio.emt_ad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterNewUserUI extends Fragment {


    private static EditText PassPr;

    //public fields.
    public static String Name = "";
    public static String UserName = "";
    public static String Secret = "";
    public static String LastName = "";
    public static String LastName2 = "";
    public static String Email = "";

    public RegisterNewUserUI() {
        // Required empty public constructor
    }

    public static void updatePass(){
        Secret = PassPr.getText().toString();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register_new_user_ui, container, false);

        final EditText name = (EditText) view.findViewById(R.id.NameNewReg);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    Name = name.getText().toString();
                } else {
                    name.setText("");
                }
            }
        });

        final EditText username = (EditText) view.findViewById(R.id.UserNameNewReg);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    UserName = username.getText().toString();
                } else {
                    username.setText("");
                }
            }
        });

        final EditText passprom = (EditText) view.findViewById(R.id.PassWordNewReg);
        passprom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    Secret = passprom.getText().toString();
                }
            }
        });

        final EditText lastname = (EditText) view.findViewById(R.id.UserLastNameNewReg);
        passprom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    LastName = lastname.getText().toString();
                }
            }
        });

        final EditText lastname2 = (EditText) view.findViewById(R.id.UserLastName2NewReg);
        passprom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    LastName2 = lastname2.getText().toString();
                }
            }
        });

        final EditText email = (EditText) view.findViewById(R.id.UserEmailNewReg);
        passprom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    Email = email.getText().toString();
                }
            }
        });
        PassPr = passprom;
        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_register_new_user_ui, container, false);
    }

}
