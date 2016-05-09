package mx.iteso.sergio.emt_ad;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.EventListener;
import java.util.Iterator;

import kriyatma.Event;

/**
 * Created by SergioAdán on 5/5/2016.
 */
public class ApiConnector extends AsyncTask<RequestPackage, String, String>    {

    private static String token;
    public String getToken(){
        return token;
    }
    private static int randData;
    private static UserData ActiveUser;
    public UserData getActiveUser(){
            return ActiveUser;
        }
    private void setActiveUser(UserData value){
            ActiveUser = value;
        }
        //{ get; private set; }
    public static ApiConnector Instance;
    private static boolean loggedIn;
    public boolean isLoggedIn(){
        return loggedIn;
    }

    public ApiConnector(){
        /*if (settings.Contains("loggedUser"))
        {
            String tToken = (String)settings["loggedUser"];
            token = tToken;
            loggedIn = true;
        }
        else
        {
            //return false;
            loggedIn = false;
            //throw;
        }*/
        ActiveUser =  new UserData();
    }

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
        if(ActiveUser == null) {
            ActiveUser = new UserData();
        }
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
                    throw new Exception();

                    //token = JsonDe.codigo;
                    //loggedIn = true;
                    //SetLocalStorage()
                }if (key.equals("codigo")) {
                    //login succesful.
                    token = value;
                    loggedIn = true;
                    //Attempt to get user data.
                    //GetUserData();
                    //ApiConnector.getInstance().execute(p);
                    RegistroFragment.setbandera(true);
                    RegisterUserActivity.setbandera(true);
                    MainActivity.setbandera(true);//para q cargue el perfil en lugar del login.
                    //MainActivity.getInstance().goToPerfil();
                    return;
                    //SetLocalStorage()
                }if (key.equals("tipo_sangre")) {
                    ActiveUser.setTipo_sangre(value);
                    continue;
                }if (key.equals("nombre")) {
                    ActiveUser.setNombre(value);
                    continue;
                }if (key.equals("apellidomat")) {
                    ActiveUser.setApellidomat(value);
                    continue;
                }if (key.equals("usuario")) {
                    ActiveUser.setUsuario(value);
                    continue;
                }if (key.equals("apellidopat")) {
                    ActiveUser.setApellidopat(value);
                    continue;
                }if (key.equals("telefono")) {
                    ActiveUser.setTelefono(value);
                    continue;
                }if (key.equals("correo")) {
                    ActiveUser.setCorreo(value);
                    continue;
                }if (key.equals("respuesta")) {
                    if (value.equals("0"))
                        //login failed ;
                        System.err.println("Login failed");
                    else if (value.equals("1"))
                    {
                        //token = JsonDe.codigo;
                        //SetLocalStorage()
                    }
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void GetUserData() {
        /*if (!loggedIn)
        {
            return null;
        }*/



        /*
        var result = await client.ExecuteTaskAsync(r);




        String InJson = "", res = result.Content;

        InJson = res.substring(res.indexOf(">{") + 1, res.indexOf("}<") - res.indexOf(">{"));

        var JsonDe = JsonConvert.DeserializeObject<UserDataRes>(InJson);


        var User = new UserData(JsonDe);
        ActiveUser = User;

        return JsonDe;
        */

    }

    public static class UserData
    {
        public UserData(){

        }
        public String getCorreo(){
            return _correo;
        }
        public void setCorreo(String value){
            changedCorreo = true;
            _correo = value;
        }

        public String secreto;
        public void setSecreto(String value){
            changedSecreto = true;
            _secreto = value;
        }

        public String usuario;
        public void setUsuario(String value){
            _usuario = value;
        }

        public String nombre;

        public String get_nombre() {
            return _nombre;
        }

        public void setNombre(String value){
            changedNombre = true;
            _nombre = value;
        }

        public String tipoidentificacion;
        public void setTipoidentificacion(String value){
            changedTipoidentificacion = true;
            _tipoidentificacion = value;
        }

        public String folioidentificacion;
        public void setFolioidentificacion(String value){
            changedFolioidentificacion = true;
            _folioidentificacion = value;
        }

        public String fechanacimento;
        public void setFechanacimento(String value){
            changedFechanacimento = true;
            _fechanacimento = value;
        }

        public String edo_civil;
        public void setEdo_civil(String value){
            changedEdo_civil = true;
            _edo_civil = value;
        }

        public String sexo;

        public String get_sexo() {
            return _sexo;
        }

        public void setSexo(String value){
            changedSexo = true;
            _sexo = value;
        }

        public String telefono;

        public String get_telefono() {
            return _telefono;
        }

        public void setTelefono(String value){
            changedTelefono = true;
            _telefono = value;
        }

        public String tipo_sangre;

        public String get_tipo_sangre() {
            return _tipo_sangre;
        }

        public void setTipo_sangre(String value){
            changedTipo_sangre = true;
            _tipo_sangre = value;
        }

        public String apellidopat;

        public String get_apellidopat() {
            return _apellidopat;
        }

        public void setApellidopat(String value){
            changedApellidopat = true;
            _apellidopat = value;
        }

        public String apellidomat;

        public String get_apellidomat() {
            return _apellidomat;
        }

        public void setApellidomat(String value){
            changedApellidomat = true;
            _apellidomat = value;
        }


        public boolean changedCorreo;
        public boolean changedSecreto;
        public boolean changedNombre;
        public boolean changedTipoidentificacion;
        public boolean changedFolioidentificacion;
        public boolean changedFechanacimento;
        public boolean changedEdo_civil;
        public boolean changedSexo;
        public boolean changedTelefono;
        public boolean changedTipo_sangre;
        public boolean changedApellidopat;
        public boolean changedApellidomat;


        private String _correo;
        private String _secreto;
        private String _usuario;
        private String _nombre;
        private String _tipoidentificacion;
        private String _folioidentificacion;
        private String _fechanacimento;
        private String _edo_civil;
        private String _sexo;
        private String _telefono;
        private String _tipo_sangre;
        private String _apellidopat;
        private String _apellidomat;

    }


}



