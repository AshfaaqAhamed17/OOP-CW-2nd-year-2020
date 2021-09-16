package model;

import java.util.Comparator;

public class ComparatorSortNumOfWin implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub footballClub1, FootballClub footballClub2) {
        if (footballClub1.getNumberOfWins() > footballClub2.getNumberOfWins()){
            return -1;
        }else if (footballClub1.getNumberOfWins() < footballClub2.getNumberOfWins()){
            return 1;
        }
        return 0;
    }
}
