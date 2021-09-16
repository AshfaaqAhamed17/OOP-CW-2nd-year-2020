package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatchUpdaterTest {

    @Test
    public void getDateOfMatchPlayed() {
        FootballClub footballClub1 = new FootballClub();
        FootballClub footballClub2 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        footballClub2.setNameOfSportsClub("Club B");
        MatchUpdater matchUpdater = new MatchUpdater("2020-12-10", footballClub1, footballClub2, "6", "5", footballClub1.getNameOfSportsClub());

        String dateOfMatchPlayedTest = "2020-12-10";
        assertEquals(dateOfMatchPlayedTest, matchUpdater.getDateOfMatchPlayed());
    }

    @Test
    public void setDateOfMatchPlayed() {
        MatchUpdater matchUpdater = new MatchUpdater();
        matchUpdater.setDateOfMatchPlayed("2020-12-10");
        String dateOfMatchPlayedTest = "2020-12-10";
        assertEquals(dateOfMatchPlayedTest, matchUpdater.getDateOfMatchPlayed());
    }

    @Test
    public void getTeamOne() {
        FootballClub footballClub1 = new FootballClub();
        FootballClub footballClub2 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        footballClub2.setNameOfSportsClub("Club B");
        MatchUpdater matchUpdater = new MatchUpdater("2020-12-10", footballClub1, footballClub2, "6", "5", footballClub1.getNameOfSportsClub());
        assertEquals(footballClub1, matchUpdater.getMatchTeamOne());
    }

    @Test
    public void setTeamOne() {
        MatchUpdater matchUpdater = new MatchUpdater();
        FootballClub footballClub1 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        matchUpdater.setMatchTeamOne(footballClub1);
        assertEquals(footballClub1, matchUpdater.getMatchTeamOne());
    }

    @Test
    public void getTeamTwo() {
        FootballClub footballClub1 = new FootballClub();
        FootballClub footballClub2 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        footballClub2.setNameOfSportsClub("Club B");
        MatchUpdater matchUpdater = new MatchUpdater("2020-12-10", footballClub1, footballClub2, "6", "5", footballClub1.getNameOfSportsClub());
        assertEquals(footballClub2, matchUpdater.getMatchTeamTwo());
    }

    @Test
    public void setTeamTwo() {
        MatchUpdater matchUpdater = new MatchUpdater();
        FootballClub footballClub2 = new FootballClub();
        footballClub2.setNameOfSportsClub("Club B");
        matchUpdater.setMatchTeamTwo(footballClub2);
        assertEquals(footballClub2, matchUpdater.getMatchTeamTwo());
    }

    @Test
    public void getScoreOfTeamOne() {
        FootballClub footballClub1 = new FootballClub();
        FootballClub footballClub2 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        footballClub2.setNameOfSportsClub("Club B");
        MatchUpdater matchUpdater = new MatchUpdater("2020-12-10", footballClub1, footballClub2, "6", "5", footballClub1.getNameOfSportsClub());
        String scoreOfTeamOneTest = "6";
        assertEquals(scoreOfTeamOneTest, matchUpdater.getScoreOfTeamOne());
    }

    @Test
    public void setScoreOfTeamOne() {
        MatchUpdater matchUpdater = new MatchUpdater();
        matchUpdater.setScoreOfTeamOne("6");
        String scoreOfTeamOneTest = "6";
        assertEquals(scoreOfTeamOneTest, matchUpdater.getScoreOfTeamOne());
    }

    @Test
    public void getScoreOfTeamTwo() {
        FootballClub footballClub1 = new FootballClub();
        FootballClub footballClub2 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        footballClub2.setNameOfSportsClub("Club B");
        MatchUpdater matchUpdater = new MatchUpdater("2020-12-10", footballClub1, footballClub2, "6", "5", footballClub1.getNameOfSportsClub());

        String scoreOfTeamTwoTest = "5";
        assertEquals(scoreOfTeamTwoTest, matchUpdater.getScoreOfTeamTwo());
    }

    @Test
    public void setScoreOfTeamTwo() {
        MatchUpdater matchUpdater = new MatchUpdater();
        matchUpdater.setScoreOfTeamTwo("5");
        String scoreOfTeamTwoTest = "5";
        assertEquals(scoreOfTeamTwoTest, matchUpdater.getScoreOfTeamTwo());
    }

    @Test
    public void getVictoryTeam() {
        FootballClub footballClub1 = new FootballClub();
        FootballClub footballClub2 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        footballClub2.setNameOfSportsClub("Club B");
        MatchUpdater matchUpdater = new MatchUpdater("2020-12-10", footballClub1, footballClub2, "6", "5", footballClub1.getNameOfSportsClub());
        String victoryTeamTest = footballClub1.getNameOfSportsClub();
        assertEquals(victoryTeamTest, matchUpdater.getVictoryTeam());
    }

    @Test
    public void setVictoryTeam() {
        MatchUpdater matchUpdater = new MatchUpdater();
        FootballClub footballClub1 = new FootballClub();
        footballClub1.setNameOfSportsClub("Club A");
        matchUpdater.setVictoryTeam(footballClub1.getNameOfSportsClub());
        assertEquals(footballClub1.getNameOfSportsClub(), matchUpdater.getVictoryTeam());
    }
}
