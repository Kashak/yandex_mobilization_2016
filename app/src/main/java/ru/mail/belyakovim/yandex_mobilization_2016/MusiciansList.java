package ru.mail.belyakovim.yandex_mobilization_2016;

import java.util.ArrayList;

/**
 * Created by Ilya Belyakov on 4/20/16.
 */
public class MusiciansList {

    private static final String ILLEGAL_ARGUMENT = "QuestSpaceList: Illegal argument of list";

    private ArrayList<Musician> musicians = new ArrayList<Musician>();

    public final Musician getMusiciansListItem(final int index) {
        if(index < 0 || index > musicians.size()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        } else {
            return musicians.get(index);
        }
    }

    public void add(Musician musician) {
        musicians.add(musician);
    }

    public int size() {
        return musicians.size();
    }

    public boolean isEmpty() {
        return musicians.isEmpty();
    }
}
