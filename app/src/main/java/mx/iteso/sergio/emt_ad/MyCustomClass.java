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
        Event event = new Event(Event.COMPLETE,"my first param");
        Log.d("Event callback","i am about to dispatch event complete");
        dispatchEvent(event);
    }
}
