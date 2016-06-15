package mx.iteso.sergio.emt_ad1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class EdicionFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    private ImageView GlobalImageView;


    public EdicionFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_edicion, container, false);

        final EditText name = (EditText) view.findViewById(R.id.NameNewReg);
        Name = ApiConnector.getInstance().getActiveUser().get_nombre();
        name.setText(Name);
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

        final EditText lastname = (EditText) view.findViewById(R.id.UserLastNameNewReg);
        LastName = ApiConnector.getInstance().getActiveUser().get_apellidopat();
        lastname.setText(LastName);
        lastname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = lastname.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus.
                    if (value.equals("Apellido paterno")) {
                        lastname.setText("");
                    } else {
                        lastname.setText(LastName);
                    }

                } else if (value.length() < 1) {
                    lastname.setText("Apellido paterno");
                }
                LastName = value;
            }
        });

        final EditText lastname2 = (EditText) view.findViewById(R.id.UserLastName2NewReg);
        LastName2 = ApiConnector.getInstance().getActiveUser().get_apellidomat();
        lastname2.setText(LastName2);
        lastname2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = lastname2.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    if (value.equals("Apellido materno")) {
                        lastname2.setText("");
                    } else {
                        lastname2.setText(LastName2);
                    }

                } else if (value.length() < 1) {
                    lastname2.setText("Apellido materno");
                }
                LastName2 = value;
            }
        });

        final EditText telefono = (EditText) view.findViewById(R.id.telefonoNewReg);
        Telefono = ApiConnector.getInstance().getActiveUser().get_telefono();
        telefono.setText(Telefono);
        telefono.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                value = telefono.getText().toString();
                if (hasFocus) {
                    // code to execute when EditText loses focus
                    Telefono = telefono.getText().toString();

                    if (value.equals("teléfono")) {
                        telefono.setText("");
                    } else {
                        telefono.setText(Telefono);
                    }

                } else if (value.length() < 1) {
                    telefono.setText("teléfono");
                }
                Telefono = value;
            }
        });

        final EditText userName = (EditText) view.findViewById(R.id.UserNameNewReg);
        UserName = ApiConnector.getInstance().getActiveUser().get_usuario();
        userName.setText(UserName);
        final EditText email = (EditText) view.findViewById(R.id.UserEmailNewRegReg2);
        Email = ApiConnector.getInstance().getActiveUser().getCorreo();
        email.setText(Email);
        final EditText sangre = (EditText) view.findViewById(R.id.sangreNewReg);
        Sangre = ApiConnector.getInstance().getActiveUser().get_tipo_sangre();
        sangre.setText(Sangre);

        /// <summary>
        /// El boton para guardar los cambios del usuario.
        /// </summary>
        final Button button = (Button) view.findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Name = name.getText().toString();
                name.requestFocus();

                ApiConnector.UserData user = ApiConnector.getInstance().getActiveUser();
                ApiConnector a = new ApiConnector();
                ApiConnector.getInstance().setActiveUser(user);
                a.Update(Name, LastName, LastName2, Telefono, Sangre);
                //esperar 3 segundos.
                //hay que avisarle al puto usuario porque va a pensar q no anda pasndo nada.. hay q investigar los indicadores de esperar.. esperemos q no funcionen con llamadas asincronas.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ApiConnector.UpdateSuccess)//si la actualizacion fue exitosa.
                        {
                            printAlertConfirmed("Cambios guardados con éxito");
                        }
                        else
                        {
                            //Aqui valio verga la actualizacion por algun motivo que el mensaje del ApiConector nos indica.
                            printAlert(ApiConnector.Message + ", intente de nuevo.");
                        }
                        ApiConnector.UpdateSuccess = false;//para q pueda updatear mas adelante.
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
