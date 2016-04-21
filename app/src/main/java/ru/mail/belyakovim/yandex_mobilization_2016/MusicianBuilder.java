package ru.mail.belyakovim.yandex_mobilization_2016;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kashak on 4/21/16.
 */
public class MusicianBuilder {

    public static Musician buildFromJSON(JSONObject obj) {
        try {
            int    id   = obj.getInt("id");
            String name = obj.getString("name");

            return new Musician(id, name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
