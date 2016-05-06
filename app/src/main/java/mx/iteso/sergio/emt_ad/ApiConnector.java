package mx.iteso.sergio.emt_ad;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by SergioAdán on 5/5/2016.
 */
public class ApiConnector extends AsyncTask<RequestPackage, String, String> {

    private static String token;

    public static ApiConnector Instance;
    public static ApiConnector getInstance()
    {
        if(Instance == null) {
            Instance = new ApiConnector();
        }
        return Instance;

    }

    @Override
    protected void onPreExecute() {
        System.out.println("Starting task...");
    }

    @Override
    protected String doInBackground(RequestPackage... params) {

        String content = HttpManager.getData(params[0]);
        return content;

            /*for (int i = 0; i<params.length; i++){
                publishProgress("Working with" + params[i]);
            }
            return "task completed";*/
    }

    @Override
    protected void onProgressUpdate(String... values) {
        System.out.println("Working with dis sh!t --> " + values[0]);
    }

    @Override
    protected void onPostExecute(String result) {

        //System.out.println(result);

        String InJson = "", res = result;
        InJson = res.substring(res.indexOf(">{") + 1, res.indexOf("}<")+1);

        //JSONDeserializer
        String inputJSONString = InJson; // Your string JSON here
        try {
            JSONObject jObject = new JSONObject(inputJSONString);
            Iterator<String> keys = jObject.keys();



            while( keys.hasNext() ) {
                String key = keys.next();
                String value = jObject.getString(key);
                if (key.equals("error")) {
                    System.out.println(value);
                    return;
                    //token = JsonDe.codigo;
                    //loggedIn = true;
                    //SetLocalStorage()
                }if (key.equals("codigo")) {
                    token = value;
                    //token = JsonDe.codigo;
                    //loggedIn = true;
                    //SetLocalStorage()
                }if (key.equals("respuesta")) {
                    if (value.equals("0"))
                        //login failed ;
                        System.err.println("Login failed");
                    else if (value.equals("1"))
                    {
                        //token = JsonDe.codigo;
                        //loggedIn = true;
                        //SetLocalStorage()
                    }
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


           /* if (JsonDe.respuesta == "0")
            {
                //LoginFailed
            }
            else
            {
                if (JsonDe.respuesta == "1")
                {
                    token = JsonDe.codigo;
                    loggedIn = true;
                    //SetLocalStorage()
                }

            }*/

        //donatorList = JSONParser.parseFeed(result);

        //assert donatorList != null;
        //for (Donator don : donatorList
        //        ) {
        //    System.out.println(don.getName());
        //}
        //System.out.println(result);
    }


}
