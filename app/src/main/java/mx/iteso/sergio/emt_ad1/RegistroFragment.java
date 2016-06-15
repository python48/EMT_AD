package mx.iteso.sergio.emt_ad1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegistroFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    private ImageView GlobalImageView;


    public RegistroFragment() {
    }

    private static EditText PassPr;

    //public fields.
    public static String Name = "";
    public static String UserName = "";
    public static String Secret = "";
    public static String LastName = "";
    public static String LastName2 = "";
    public static String Email = "";
    public static String Telefono = "";
    public static String Sangre = "";

    private static int RESULT_LOAD_IMAGE = 1;

    String value = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       //return inflater.inflate(R.layout.fragment_registro, container, false);


        final View view = inflater.inflate(R.layout.fragment_registro, container, false);

        final EditText name = (EditText) view.findViewById(R.id.NameNewReg);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = name.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    //Name = name.getText().toString();
                    if (value.equals("Nombre"))
                    {
                        name.setText("");
                    }
                    else
                    {
                        name.setText(Name);
                    }

                //aint got focus.
                } else {
                    //name.setText("");
                    if (value.length() < 1)
                    {
                        name.setText("Nombre");
                    }
                    Name = value;
                }
            }
        });

        final EditText username = (EditText) view.findViewById(R.id.UserNameNewReg);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = username.getText().toString();
                if (hasFocus) {
                    if (value.equals("Nombre de usuario"))
                    {
                        username.setText("");
                    }
                    else
                    {
                        username.setText(UserName);
                    }

                } else {
                    if (value.length() < 1)
                    {
                        username.setText("Nombre de usuario");
                    }
                    UserName = value;
                }
            }
        });

        final EditText passprom = (EditText) view.findViewById(R.id.PassWordNewReg);
        passprom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = passprom.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus.
                    if (value.equals("secreto"))
                    {
                        passprom.setText("");
                    }
                    else
                    {
                        passprom.setText(Secret);
                    }
                }else {
                    if (value.length() < 1)
                    {
                        passprom.setText("secreto");
                    }
                    Secret = value;
                }
            }
        });

        final EditText lastname = (EditText) view.findViewById(R.id.UserLastNameNewReg);
        lastname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = lastname.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus.
                    if (value.equals("Apellido paterno"))
                    {
                        lastname.setText("");
                    }
                    else
                    {
                        lastname.setText(LastName);
                    }

                }else
                    if (value.length() < 1)
                    {
                        lastname.setText("Apellido paterno");
                    }
                    LastName = value;
            }
        });

        final EditText lastname2 = (EditText) view.findViewById(R.id.UserLastName2NewReg);
        lastname2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = lastname2.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    if (value.equals("Apellido materno"))
                    {
                        lastname2.setText("");
                    }
                    else
                    {
                        lastname2.setText(LastName2);
                    }

                }else
                    if (value.length() < 1)
                    {
                        lastname2.setText("Apellido materno");
                    }
                    LastName2 = value;
            }
        });


        final EditText email = (EditText) view.findViewById(R.id.UserEmailNewRegReg2);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = email.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    //Email = email.getText().toString();

                    if (value.equals("Correo electrónico"))
                    {
                        email.setText("");
                    }
                    else
                    {
                        email.setText(Email);
                    }

                }else
                if (value.length() < 1)
                {
                    email.setText("Correo electrónico");
                }
                Email = value;
            }
        });

        final EditText telefono = (EditText) view.findViewById(R.id.telefonoNewReg);
        telefono.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = telefono.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    Telefono = telefono.getText().toString();

                    if (value.equals("teléfono"))
                    {
                        telefono.setText("");
                    }
                    else
                    {
                        telefono.setText(Telefono);
                    }

                }else
                if (value.length() < 1)
                {
                    telefono.setText("teléfono");
                }
                Telefono = value;
            }
        });

        final EditText sangre = (EditText) view.findViewById(R.id.sangreNewReg);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = sangre.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    Sangre = sangre.getText().toString();

                    if (value.equals("tipo de sangre: ej. a+"))
                    {
                        sangre.setText("a+");
                    }
                    else
                    {
                        sangre.setText(Sangre);
                    }

                }else
                if (value.length() < 1)
                {
                    sangre.setText("tipo de sangre: ej. a+");
                }
                Sangre = value;
            }
        });
        PassPr = passprom;

        /// <summary>
        /// El boton para registrar a un nuevo usuario.
        /// </summary>
        final Button button = (Button) view.findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Email = email.getText().toString();
                email.requestFocus();

                ApiConnector.UserData user = ApiConnector.getInstance().getActiveUser();

                //final ApiConnector b = new ApiConnector();
                ApiConnector a = new ApiConnector();
                a.RegisterUser(Email, UserName, Secret, Name, LastName, LastName2);
                //esperar 3 segundos.
                //hay que avisarle al puto usuario porque va a pensar q no anda pasndo nada.. hay q investigar los indicadores de esperar.. esperemos q no funcionen con llamadas asincronas.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ApiConnector.RegisterSuccess)//si el registro fue exitoso.
                        {
                            printAlertConfirmed("Registro exitoso.");

                            //b[0] = a;
                            //b[0].Update("", "", "", Telefono, Sangre);
                        }
                        else
                        {
                            //Aqui valio verg.... el registro por algun motivo que el mensaje del ApiConector guarda.
                            printAlert(ApiConnector.Message + ", intente de nuevo.");
                        }
                        ApiConnector.RegisterSuccess = false;
                    }
                }, 3000);

            }
        });

        ImageButton buttonLoadImage = (ImageButton) view.findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //intent.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult( i, PICK_IMAGE );
            }
        });

        GlobalImageView = (ImageView) view.findViewById(R.id.imageView5);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            Uri pickedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContext().getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();

            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            GlobalImageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));

            cursor.close();

            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("imagepath", pickedImage.toString()).commit();

        }
    }

    private void goToMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }private void refresh() {
        Intent intent = new Intent(getActivity(), Registro2.class);
        startActivity(intent);
    }
    private void printAlert(String message){
        new AlertDialog.Builder(getContext())
                .setTitle("Atención")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // dooo shit over here.
                    }
                })/*
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void printAlertConfirmed(String message){
        new AlertDialog.Builder(getContext())
                .setTitle("Atención")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        goToMain();
                    }
                })/*
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
