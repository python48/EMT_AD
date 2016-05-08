package mx.iteso.sergio.emt_ad;

import android.util.Log;

import kriyatma.Event;
import kriyatma.EventDIspatcher;


/**
 * Created by sreejeshpillai on 07/07/15.
 */
public class MyCustomClass extends EventDIspatcher {
    private static MyCustomClass ourInstance = new MyCustomClass();

    public static MyCustomClass getInstance() {
        return ourInstance;
    }

    private MyCustomClass() {
    }

    public void myCallback(){

        callApi();

        Event event = new Event(Event.COMPLETE,"my first param");
        Log.d("Event callback","i am about to dispatch event complete");
        dispatchEvent(event);
    }


    public void callApi(){
        String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        //p.setUri("http://requestb.in/1kp9q0p1");

        //Name es el nombre. first name es el primer apellido, last name es el segundo. che jairo wey! me confundiste no mames!! xD.
        String userName = "furfag2004", pass = "hola4", email = "user1@mail.com", name = "thomas" , firstName = "alba", lastName = "edison";

        String json = String.format("{\"funcion\":\"ingreso\", \"usuario\":\"%s\",\"palabrasecreta\":\"%s\"}", userName, pass);

        //String json = String.format("{\"funcion\":\"registro\", \"correo\":\"%s\",\"usuario\":\"%s\",\"palabrasecreta\":\"%s\",\"nombre\":\"%s\",\"APELLIDOPAT\":\"%s\",\"APELLIDOMAT\":\"%s\"}", email, userName, pass, name, firstName, lastName);
        p.setParam("Info", json);
        //p.setParam("Junk", Integer.toString(randData++));

        ApiConnector.getInstance().execute(p);
    }
}
