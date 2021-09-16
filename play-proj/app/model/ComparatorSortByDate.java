package model;

import java.util.Comparator;

public class ComparatorSortByDate implements Comparator<MatchUpdater> {

    @Override
    public int compare(MatchUpdater dateOne1, MatchUpdater dateTwo2) {
        return dateTwo2.getDateOfMatchPlayed().compareTo(dateOne1.getDateOfMatchPlayed());
    }
}
