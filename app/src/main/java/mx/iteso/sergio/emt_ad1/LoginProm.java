package mx.iteso.sergio.emt_ad1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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


    String value = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //(ImageView) getView().findViewById(R.id.foo);
        View view = inflater.inflate(R.layout.fragment_login_prom, container, false);

        final EditText username = (EditText) view.findViewById(R.id.UserNameProm);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = username.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    //Name = name.getText().toString();
                    if (value.equals("Nombre de usuario"))
                    {
                        username.setText("");
                    }
                    else
                    {
                        username.setText(UserName);
                    }

                    //aint got focus.
                } else {
                    //name.setText("");
                    if (value.length() < 1)
                    {
                        username.setText("Nombre de usuario");
                    }
                    UserName = value;
                }
            }
        });

        final EditText passprom = (EditText) view.findViewById(R.id.PasswordProm);
        passprom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = passprom.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    //Name = name.getText().toString();
                    if (value.equals("secreto"))
                    {
                        passprom.setText("");
                    }
                    else
                    {
                        passprom.setText(Secret);
                    }

                    //aint got focus.
                } else {
                    //name.setText("");
                    if (value.length() < 1)
                    {
                        passprom.setText("secreto");
                    }
                    Secret = value;
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
