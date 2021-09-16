package model;

import java.io.Serializable;

public class MatchUpdater implements Serializable {
    private String dateOfMatchPlayed;
    private FootballClub matchTeamOne;
    private FootballClub matchTeamTwo;
    private String scoreOfTeamOne;
    private String scoreOfTeamTwo;
    private String victoryTeam;

    public MatchUpdater(String dateOfMatchPlayed, FootballClub matchTeamOne, FootballClub matchTeamTwo, String scoreOfTeamOne, String scoreOfTeamTwo, String victoryTeam) {
        this.dateOfMatchPlayed = dateOfMatchPlayed;
        this.matchTeamOne = matchTeamOne;
        this.matchTeamTwo = matchTeamTwo;
        this.scoreOfTeamOne = scoreOfTeamOne;
        this.scoreOfTeamTwo = scoreOfTeamTwo;
        this.victoryTeam = victoryTeam;
    }

    public MatchUpdater() { }

    public String getDateOfMatchPlayed() {
        return dateOfMatchPlayed;
    }

    public void setDateOfMatchPlayed(String dateOfMatchPlayed) {
        this.dateOfMatchPlayed = dateOfMatchPlayed;
    }

    public FootballClub getMatchTeamOne() {
        return matchTeamOne;
    }

    public void setMatchTeamOne(FootballClub matchTeamOne) {
        this.matchTeamOne = matchTeamOne;
    }

    public FootballClub getMatchTeamTwo() {
        return matchTeamTwo;
    }

    public void setMatchTeamTwo(FootballClub matchTeamTwo) {
        this.matchTeamTwo = matchTeamTwo;
    }

    public String getScoreOfTeamOne() {
        return scoreOfTeamOne;
    }

    public void setScoreOfTeamOne(String scoreOfTeamOne) {
        this.scoreOfTeamOne = scoreOfTeamOne;
    }

    public String getScoreOfTeamTwo() {
        return scoreOfTeamTwo;
    }

    public void setScoreOfTeamTwo(String scoreOfTeamTwo) {
        this.scoreOfTeamTwo = scoreOfTeamTwo;
    }

    public String getVictoryTeam() {
        return victoryTeam;
    }

    public void setVictoryTeam(String victoryTeam) {
        this.victoryTeam = victoryTeam;
    }

    @Override
    public String toString() {return
            " * Date of Match Played\t\t=\t"    + dateOfMatchPlayed + " \n" +
                    " * Team One\t\t\t\t=\t"            + matchTeamOne + " \n" +
                    " * Score of Team One\t\t=\t"       + scoreOfTeamOne    + " \n" +
                    " * Team Two\t\t\t\t=\t"            + matchTeamTwo + " \n" +
                    " * Score of Team Two\t\t=\t"       + scoreOfTeamTwo    + " \n" +
                    " * Victory Team\t\t\t\t=\t"        + "~ " + victoryTeam + " ~"     + " \n" ;
    }
}
