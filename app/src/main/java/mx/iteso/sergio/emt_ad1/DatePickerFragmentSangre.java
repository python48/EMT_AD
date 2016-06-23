package mx.iteso.sergio.emt_ad1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by SergioAdán on 5/20/2016.
 */
public class DatePickerFragmentSangre extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    Button txtDate;
    /*public DatePickerFragment(View view){
        txtDate = (EditText)view;
    }*/
    public DatePickerFragmentSangre(){
    }

    public void ponerleView(View view){
        txtDate = (Button)view;
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
    int coun = 0;
    public void onDateSet(DatePicker view, int year, int month, int day) {
        if(coun++>0)
        {
            coun = 0;
            return;
        }
        // Do something with the date chosen by the user
        String date = day + "-" + (++month) + "-" + year ;
        txtDate.setText(date);

        String msg = "Vas a agendar una visita el día: "+ date;
        MainActivity.levantarCita(msg,view, year, month, day);


    }
}
