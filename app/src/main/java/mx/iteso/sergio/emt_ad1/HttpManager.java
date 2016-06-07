package mx.iteso.sergio.emt_ad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Sergio on 19/04/2016.
 */
public class HttpManager {

    public static String getData(RequestPackage p) {

        BufferedReader reader = null;
        String uri = p.getUri();
        if (p.getMethod().equals("GET")) {
            uri += "?" + p.getEncodedParams();
        }

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(p.getMethod());

            //String b = "Info=" + p.getParams().get("Info");


            String c ="";

            //yo estaba escribiendo codigo a lo poendejo jaja.
            Map<String, String> map = p.getParams();
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                c += entry.getKey()+"="+entry.getValue() + "&";
                //System.out.println(entry.getKey() + "/" + entry.getValue());
            }c = c.substring(0,c.length()-1);

            //json = String.format("{\"funcion\":\"registro\", \"correo\":\"%s\",\"usuario\":\"%s\",\"palabrasecreta\":\"%s\",\"nombre\":\"%s\",\"APELLIDOPAT\":\"%s\",\"APELLIDOMAT\":\"%s\"}", email, userName, pass, name, firstName, lastName);
            //String b = json.get("junk").toString();
            String params = c;

            if (p.getMethod().equals("POST")) {
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(params);
                writer.flush();
            }

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

}
