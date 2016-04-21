package ru.mail.belyakovim.yandex_mobilization_2016;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kashak on 4/21/16.
 */
public class MusicianBuilder {

    public static Musician buildFromJSON(JSONObject obj) {
        try {
            int             id = obj.getInt("id");
            String        name = obj.getString("name");
            ArrayList<String> genres =
                    convertJSONArrayToArrayListOfStrings(obj.getJSONArray("genres"));
            int         tracks = obj.getInt("tracks");
            int         albums = obj.getInt("albums");
            String        link = obj.getString("link");
            String description = obj.getString("description");

            JSONObject     cover = obj.getJSONObject("cover");
            String smallCoverURL = cover.getString("small");
            String   bigCoverURL = cover.getString("big");

            return new Musician(id, name, genres, tracks, albums, link, description, smallCoverURL, bigCoverURL);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> convertJSONArrayToArrayListOfStrings(JSONArray jsonArray) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i=0; i<jsonArray.length(); i++) {
            try {
                list.add( jsonArray.getString(i) );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
