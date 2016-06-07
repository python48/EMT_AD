package mx.iteso.sergio.emt_ad1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 19/04/2016.
 */
public class JSONParser {

    public static List<Donator> parseFeed(String content){

        try {
            JSONArray ar = new JSONArray(content);
            List<Donator> donatorList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                Donator donator = new Donator();
                donator.setName(obj.getString("name"));

                donatorList.add(donator);
            }
            return donatorList;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

}
