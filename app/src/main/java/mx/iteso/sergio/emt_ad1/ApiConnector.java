package mx.iteso.sergio.emt_ad1;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by SergioAdán on 5/5/2016.
 */
public class ApiConnector extends AsyncTask<RequestPackage, String, String>    {

    //final String uri = "http://srvcibergdl.redlab.com.mx/wshematixnet.asmx/accion";//pruebas.
    final String uri = "http://cetsgdl.redlab.com.mx:8085/wshematixnet.asmx/accion";//produccion.
    //String uri = "http://requestb.in/wyx8r2wy";
    private static String token;
    private boolean sinCita = false;

    public String getToken(){
        return token;
    }
    //private static int randData;
    public static UserData ActiveUser;
    public UserData getActiveUser(){
            return ActiveUser;
        }
    public void setActiveUser(UserData value){
            ActiveUser = value;
        }
        //{ get; private set; }
    private static ApiConnector Instance = null;
    private static boolean loggedIn;
    public boolean isLoggedIn(){
        if (getToken() != null && !getToken().isEmpty())
            return true;
        else
            return false;
    }

    public ApiConnector(){
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
        /*if(ActiveUser == null) {
            ActiveUser = new UserData();
        }*/
    }

    @Override
    protected String doInBackground(RequestPackage... params) {
        String content = HttpManager.getData(params[0]);
        return content;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        //System.out.println("Working with dis sh!t --> " + values[0]);
    }


    ///revisar estas pendejadas.
    private boolean isHaciendoCita = false;
    public boolean getHaciendoCita(){
        return isHaciendoCita;
    }
    public void setHaciendoCita(boolean value){
        isHaciendoCita = value;
    }
    //hasta auqii...
    @Override
    protected void onPostExecute(String result) {
        String InJson = "", res = result;
        InJson = res.substring(res.indexOf(">{") + 1, res.indexOf("}<")+1);

        //JSONDeserializer
        String inputJSONString = InJson; // Your string JSON here
        try
        {
            JSONObject jObject = new JSONObject(inputJSONString);
            Iterator<String> keys = jObject.keys();
            while(keys.hasNext())
            {
                String key = keys.next();
                String value = jObject.getString(key);

                switch (currentFunc)
                {
                    case REGISTRO:

                        if (key.equals("codigo"))
                        {
                            token = value;
                            RegisterSuccess = true;
                            break;
                        }
                        else if (key.equals("error"))
                        {
                            Message = value;
                            RegisterSuccess = false;
                            break;
                        }

                        break;
                    case INGRESO:

                        if (key.equals("codigo"))
                        {
                            token = value;
                            LoginSuccess = true;
                            break;
                        }
                        else if (key.equals("error"))
                        {
                            Message = value;
                            LoginSuccess = false;
                            break;
                        }

                        break;
                    case ACTUALIZA:

                        if (key.equals("respuesta")){
                            if (value.equals("exito"))
                            {
                                UpdateSuccess = true;
                                break;
                            }
                            else if (value.equals("parcial"))
                            {
                                Message = "Se actualizaron algunos datos";
                                UpdateSuccess = true;
                                break;
                            }
                            else if (value.equals("error"))
                            {
                                Message = "Error al guardar datos";
                                UpdateSuccess = false;
                                break;
                            }
                        }

                        break;
                    case OBTEN_DATOS:

                        if (key.equals("respuesta")) {
                            if (value.equals("1"))
                            {
                                GetDataSuccess = true;
                                continue;
                            }else
                                GetDataSuccess = false;
                                break;
                        }else if (key.equals("exito")) {
                            GetDataSuccess = true;
                            continue;
                        }else if (key.equals("exitoso")) {
                            GetDataSuccess = true;
                            continue;
                        }else if (key.equals("error")) {
                            Message = value;
                            GetDataSuccess = false;
                            break;
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
                        }

                        break;
                    case ASIGNA_CITA:

                        if (key.equals("respuesta")){
                            if (value.equals("SinCita"))
                            {
                                continue;/*
                            Log.i("ApiConnector: ", value);
                            MainActivity.setMessage(value);
                            MainActivity.setHayCita(false);*/
                                //isHaciendoCita=false;
                            }
                        }
                        else if (key.equals("mensaje")) {
                            if (value.equals("No hay cita agendada"))
                            {
                                Message=value;
                                MakeAppointmentSuccess=false;
                            }else{
                                MakeAppointmentSuccess=true;
                                Message=value;
                                Analisis.citaHeaderTxt = value;
                                Analisis.citaButtonTxt = "Cancelar visita";
                                Analisis.updateText();
                            }
                        }

                        break;
                    case VER_CITA:

                        if (key.equals("respuesta"))
                        {
                            if (value.equals("exito"))
                            {
                                continue;
                            }
                        }
                        else if (key.equals("mensaje"))
                        {
                            Message = value;
                            if (value.equals("No hay cita agendada"))
                            {
                                ViewAppointmentSuccessTheresAppointment = false;
                                Analisis.citaButtonTxt = "¡Agenda una visita!";
                                Analisis.citaHeaderTxt = value;
                                Analisis.updateText();
                                //Analisis.citaButton.performClick();
                            }
                            else
                            {
                                ViewAppointmentSuccessTheresAppointment = true;
                                Analisis.citaHeaderTxt = "Ya tienes una visita agendada el día " + value;
                                Analisis.citaButtonTxt = "Cancelar visita";
                                Analisis.updateText();
                                //Analisis.citaButton.performClick();
                            }
                        }
                        else if (key.equals("error"))
                        {
                            Message = value;
                            ViewAppointmentSuccessTheresAppointment = false;
                            break;
                        }

                        break;
                    case CANCELAR:


                        if (key.equals("respuesta"))
                        {
                            if (value.equals("exito"))
                            {
                                Message = "Se ha cancelado tu cita";
                                CancelAppointmentSuccess=true;
                                Analisis.citaButtonTxt = "¡Agenda una visita!";
                                Analisis.citaHeaderTxt = "No hay cita agendada";
                                Analisis.updateText();
                            }else if (key.equals("error"))
                            {
                                CancelAppointmentSuccess=false;
                                Message = value;
                            }
                        }

                        break;
                    case CAMBIA_CONTRASENA:



                        break;
                    case RESETEA_CONTRASENA:

                        if (key.equals("respuesta"))
                        {
                            if (!value.equals("No se encontro correo electronico proporcionado"))
                            {
                                ResetPasswordSuccess = true ;
                                continue;
                            }else if (key.equals("error"))
                            {
                                ResetPasswordSuccess = false ;
                            }else
                            {
                                Message = value;
                                ResetPasswordSuccess = false ;
                            }
                        }else if (key.equals("mensaje"))
                        {
                            Message = value ;
                        }
                        break ;
                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public static String Message = "";
    enum funcs {REGISTRO, INGRESO, ACTUALIZA, OBTEN_DATOS, RESETEA_CONTRASENA, ASIGNA_CITA, VER_CITA, CANCELAR, CAMBIA_CONTRASENA }
    funcs currentFunc;
    public static boolean RegisterSuccess = false;
    public static boolean LoginSuccess = false;
    public static boolean GetDataSuccess = false;
    public static boolean UpdateSuccess = false;
    public static boolean ViewAppointmentSuccessTheresAppointment = false;
    public static boolean MakeAppointmentSuccess = false;
    public static boolean CancelAppointmentSuccess = false;
    public static boolean ResetPasswordSuccess = false;
    public void RegisterUser(String Email, String UserName, String Secret, String Name, String LastName, String LastName2){
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        String json = String.format("{\"funcion\":\"registro\", \"correo\":\"%s\",\"usuario\":\"%s\",\"palabrasecreta\":\"%s\",\"nombre\":\"%s\",\"APELLIDOPAT\":\"%s\",\"APELLIDOMAT\":\"%s\"}", Email, UserName, Secret, Name, LastName, LastName2);
        p.setParam("Info", json);
        currentFunc = funcs.REGISTRO;
        execute(p);
    }
    public void Login(String un, String ps){
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        String json = String.format("{\"funcion\":\"ingreso\", \"usuario\":\"%s\",\"palabrasecreta\":\"%s\"}", un, ps);
        p.setParam("Info", json);
        currentFunc = funcs.INGRESO;
        execute(p);
    }
    public void LogOut() {
        token = null;
        GetDataSuccess = false;
        LoginSuccess = false;
        RegisterSuccess = false;
        UpdateSuccess = false;
    }
    public void GetUserData(){
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        String json = String.format("{\"funcion\":\"obten\", \"codigo\":\"%s\"} ", token);
        p.setParam("Info", json);
        currentFunc = funcs.OBTEN_DATOS;
        execute(p);
    }
    public void Update(String name, String lastName, String lastName2, String telefono, String sangre){

        if (!ApiConnector.getInstance().getActiveUser().get_nombre().equals(name))
            ApiConnector.getInstance().getActiveUser().setNombre(name);
        if (!ApiConnector.getInstance().getActiveUser().get_apellidopat().equals(lastName))
            ApiConnector.getInstance().getActiveUser().setApellidopat(lastName);
        if (!ApiConnector.getInstance().getActiveUser().get_apellidomat().equals(lastName2))
            ApiConnector.getInstance().getActiveUser().setApellidomat(lastName2);
        if (!ApiConnector.getInstance().getActiveUser().get_telefono().equals(telefono))
            ApiConnector.getInstance().getActiveUser().setTelefono(telefono);

        String json = String.format("{\"funcion\":\"actualiza\", \"codigo\":\"%s\", data:[", token);
        int chagdCount = 0;

        if (ActiveUser.changedNombre)
        {
            chagdCount++;
            json +=
                    "{\"campo\":\"nombre\",\"valor\":\"" + name + "\"},";
        }
        if (ActiveUser.changedTelefono)
        {
            chagdCount++;
            json +=
                    "{\"campo\":\"telefono\",\"valor\":\"" + telefono + "\"},";
        }
        if (ActiveUser.changedTipo_sangre)
        {
            chagdCount++;
            json +=
                    "{\"campo\":\"tipo_sangre\",\"valor\":\"" + sangre + "\"},";
        }
        if (ActiveUser.changedApellidopat)
        {
            chagdCount++;
            json +=
                    "{\"campo\":\"apellidopat\",\"valor\":\"" + lastName + "\"},";
        }
        if (ActiveUser.changedApellidomat)
        {
            chagdCount++;
            json +=
                    "{\"campo\":\"apellidomat\",\"valor\":\"" + lastName2 + "\"},";
        }
        if (chagdCount == 0)
        {
            return;
        }
        json = json.substring(0, json.length() - 1);


        json += "]}";

        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        //p.setUri("http://requestb.in/1gugpgy1");
        p.setParam("Info", json);
        currentFunc = funcs.ACTUALIZA;
        execute(p);
    }
    public void MakeAppointment(int year,int month,int day){
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        String json = String.format("{\"funcion\":\"asigna_cita\", \"codigo\":\"%s\" , \"fecha\":\"%s\"} ", token, Integer.toString(year) + ((month < 10 ? "0" : "") + Integer.toString(month)) + ((day < 10 ? "0" : "") + Integer.toString(day)));
        p.setParam("Info", json);
        currentFunc = funcs.ASIGNA_CITA;
        execute(p);
    }
    public void ViewAppointment(){
        String json = String.format("{\"funcion\":\"ver_cita\", \"codigo\":\"%s\" } ", token);
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        p.setParam("Info", json);
        currentFunc = funcs.VER_CITA;
        execute(p);
    }
    public void CancelAppointment(){
        String json = String.format("{\"funcion\":\"cancelar_cita\", \"codigo\":\"%s\" } ", token);
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        p.setParam("Info", json);
        currentFunc = funcs.CANCELAR;
        execute(p);
    }
    public void ResetPasword(String email){
        String json = String.format("{\"funcion\":\"recupera_contrasena\", \"correo\":\"%s\" } ", email);
        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        p.setParam("Info", json);
        currentFunc = funcs.RESETEA_CONTRASENA;
        execute(p);
    }








//////NESTED CLASS.
    public static class UserData
    {
        public UserData(){
        }
        public String correo;
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
        public String get_usuario() {
            return _usuario;
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



