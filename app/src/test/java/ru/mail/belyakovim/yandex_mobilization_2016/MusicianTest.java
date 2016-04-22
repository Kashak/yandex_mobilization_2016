package ru.mail.belyakovim.yandex_mobilization_2016;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by kashak on 4/22/16.
 */
public class MusicianTest extends TestCase {

    Musician musician;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        musician = new Musician(0, null, null, 0, 0, null, null, null, null);
    }

    @Override
    protected void tearDown() throws Exception {
        musician = null;
        super.tearDown();
    }

    public void testConstructor() throws Exception {
        ArrayList<String> genres = new ArrayList<String>();
        genres.add("rock");
        genres.add("jazz");
        Musician m = new Musician(
                17683,
                "Moby",
                genres,
                300200,
                100500,
                "http://example.com",
                "Bla-bla-bla",
                "http://example.com/small-cover.png",
                "http://example.com/big-cover.png");
        assertEquals(17683, m.getId());
        assertEquals("Moby", m.getName());
        assertEquals(genres.get(0), m.getGenres().get(0));
        assertEquals(genres.get(1), m.getGenres().get(1));
        assertEquals(300200, m.getTracks());
        assertEquals(100500, m.getAlbums());
        assertEquals("http://example.com", m.getLink());
        assertEquals("Bla-bla-bla", m.getDescription());
        assertEquals("http://example.com/small-cover.png", m.getSmallCoverURL());
        assertEquals("http://example.com/big-cover.png", m.getBigCoverURL());
    }

    public void testGetId() throws Exception {
        musician.setId(17683);
        assertEquals(17683, musician.getId());
    }

    public void testGetName() throws Exception {
        musician.setName("Moby");
        assertEquals("Moby", musician.getName());
    }

    public void testGetGenres() throws Exception {
        ArrayList<String> genres = new ArrayList<String>();
        genres.add("rock");
        genres.add("jazz");
        musician.setGenres(genres);
        assertEquals(genres, musician.getGenres());
    }

    public void testGetTracks() throws Exception {
        musician.setTracks(100500);
        assertEquals(100500, musician.getTracks());
    }

    public void testGetAlbums() throws Exception {
        musician.setAlbums(100500);
        assertEquals(100500, musician.getAlbums());

    }

    public void testGetLink() throws Exception {
        musician.setLink("http://example.com");
        assertEquals("http://example.com", musician.getLink());
    }

    public void testGetDescription() throws Exception {
        musician.setDescription("Bla-bla-bla");
        assertEquals("Bla-bla-bla", musician.getDescription());
    }

    public void testGetSmallCoverURL() throws Exception {
        musician.setSmallCoverURL("http://example.com/small-cover.png");
        assertEquals("http://example.com/small-cover.png", musician.getSmallCoverURL());
    }

    public void testGetBigCoverURL() throws Exception {
        musician.setSmallCoverURL("http://example.com/big-cover.png");
        assertEquals("http://example.com/big-cover.png", musician.getSmallCoverURL());
    }
}