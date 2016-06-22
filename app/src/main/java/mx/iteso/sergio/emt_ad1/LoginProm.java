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
    public static EditText username =  null;
    public static EditText passprom = null;

    public LoginProm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //(ImageView) getView().findViewById(R.id.foo);
        View view = inflater.inflate(R.layout.fragment_login_prom, container, false);

        username = (EditText) view.findViewById(R.id.UserNameProm);
        passprom = (EditText) view.findViewById(R.id.PasswordProm);

        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login_prom, container, false);
    }

    public static void updatePass(){
        UserName = username.getText().toString();
        Secret = passprom.getText().toString();
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
