package ru.mail.belyakovim.yandex_mobilization_2016;

import java.util.ArrayList;

/**
 * Created by Ilya Belyakov on 4/20/16.
 */
public class Musician {

    private               int id;
    private            String name;
    private ArrayList<String> genres;
    private               int tracks;
    private               int albums;
    private            String link;
    private            String description;
    private            String smallCoverURL;
    private            String bigCoverURL;


    public Musician(              int id,
                               String name,
                    ArrayList<String> genres,
                                  int tracks,
                                  int albums,
                               String link,
                               String description,
                               String smallCoverURL,
                               String bigCoverURL) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
        this.albums = albums;
        this.link = link;
        this.description = description;
        this.smallCoverURL = smallCoverURL;
        this.bigCoverURL = bigCoverURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallCoverURL() {
        return smallCoverURL;
    }

    public void setSmallCoverURL(String smallCoverURL) {
        this.smallCoverURL = smallCoverURL;
    }

    public String getBigCoverURL() {
        return bigCoverURL;
    }

    public void setBigCoverURL(String bigCoverURL) {
        this.bigCoverURL = bigCoverURL;
    }
}
