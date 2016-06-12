package mx.iteso.sergio.emt_ad1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by SergioAdán on 5/20/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText txtDate;
    public DatePickerFragment(View view){
        txtDate = (EditText)view;
    }
    public DatePickerFragment(){
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        day++;//no se puede agendar el mismo día. :(
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        String date = day + "-" + (month+1) + "-" + year ;
        txtDate.setText(date);
        String msg = "Vas a agendar una visita el día: "+ date;
        MainActivity.levantarCita(msg,view, year, month, day);

        /*
        //Pedir al usuario que asegure la cita.
        new AlertDialog.Builder(view.getContext())
                .setTitle("Atención")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // OK. Hacer cita nueva.
                        System.out.println("---Aqui deberia hacer nua nueva cita");//hacer el cagadero con el apiconector.
                    }
                })
                .setIcon(android.R.drawable.alert_dark_frame)
                .show();
*/

    }
}
