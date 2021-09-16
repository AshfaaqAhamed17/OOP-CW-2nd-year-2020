package model;

import java.util.Comparator;

public class ComparatorSortGoalsScored implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub footballClub1, FootballClub footballClub2) {
        if (footballClub1.getNumberOfGoalsScored() > footballClub2.getNumberOfGoalsScored()){
            return -1;
        }else if (footballClub1.getNumberOfGoalsScored() < footballClub2.getNumberOfGoalsScored()){
            return 1;
        }
        return 0;
    }
}
