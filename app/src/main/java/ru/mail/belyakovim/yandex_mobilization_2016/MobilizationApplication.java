package ru.mail.belyakovim.yandex_mobilization_2016;

import android.app.Application;

/**
 * Created by Ilya Belyakov on 4/20/16.
 */
public class MobilizationApplication extends Application {

    private MusiciansList musiciansList = new MusiciansList();

    public static String getServerDataPath() {
        return "http://download.cdn.yandex.net/mobilization-2016/artists.json";
    }

    public MusiciansList getMusiciansList() {
        return musiciansList;
    }

    public void setMusiciansList(MusiciansList musiciansList) {
        this.musiciansList = musiciansList;
    }
}
