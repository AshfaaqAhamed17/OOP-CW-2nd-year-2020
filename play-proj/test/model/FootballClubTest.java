package model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class FootballClubTest {

    @Test
    public void getNameOfCaptain() {
        FootballClub footballClub = new FootballClub("Zodec", "Colombo", Color.RED, "John","10002", "Micheal");
        String nameOfCaptainTest = "Micheal";
        assertEquals(nameOfCaptainTest, footballClub.getNameOfCaptain());
    }

    @Test
    public void setNameOfCaptain() {
        FootballClub footballClub = new FootballClub();
        String nameOfCaptainTest = "Micheal";
        footballClub.setNameOfCaptain("Micheal");
        assertEquals(nameOfCaptainTest, footballClub.getNameOfCaptain());
    }

    @Test
    public void getFootballClubId() {
        FootballClub footballClub = new FootballClub("Zodec", "Colombo", Color.RED, "John","10002", "Micheal");
        int footballClubIDTest = Integer.parseInt("10002");
        assertEquals(footballClubIDTest, footballClub.getFootballClubId());
    }

    @Test
    public void setFootballClubId() {
        FootballClub footballClub = new FootballClub();
        int footballClubIDTest = Integer.parseInt("10002");
        footballClub.setFootballClubId("10002");
        assertEquals(footballClubIDTest, footballClub.getFootballClubId());
    }

    @Test
    public void getTeamColor() {
        FootballClub footballClub = new FootballClub("Zodec", "Colombo", Color.RED, "John","10002", "Micheal");
        String colorTest = String.valueOf(Color.RED);
        assertEquals(colorTest, footballClub.getTeamColor());
    }

    @Test
    public void setTeamColor() {
        FootballClub footballClub = new FootballClub();
        String colorTest = String.valueOf(Color.RED);
        footballClub.setTeamColor(Color.RED);
        assertEquals(colorTest, footballClub.getTeamColor());
    }

    @Test
    public void getNumberOfWins() {
        FootballClub footballClub = new FootballClub("Zodec", 3,2 , 1, 10, 5, 6, 10);
        int numberOfWinsTest = 3;
        assertEquals(numberOfWinsTest, footballClub.getNumberOfWins());
    }

    @Test
    public void setNumberOfWins() {
        FootballClub footballClub = new FootballClub();
        int numberOfWinsTest = 3;
        footballClub.setNumberOfWins(3);
        assertEquals(numberOfWinsTest, footballClub.getNumberOfWins());
    }

    @Test
    public void getNumberOfLoss() {
        FootballClub footballClub = new FootballClub("Zodec", 3,2 , 1, 10, 5, 6, 10);
        int numberOfLossTest = 2;
        assertEquals(numberOfLossTest, footballClub.getNumberOfLoss());
    }

    @Test
    public void setNumberOfLoss() {
        FootballClub footballClub = new FootballClub();
        int numberOfLossTest = 2;
        footballClub.setNumberOfLoss(2);
        assertEquals(numberOfLossTest, footballClub.getNumberOfLoss());
    }

    @Test
    public void getNumberOfMatchDraw() {
        FootballClub footballClub = new FootballClub("Zodec", 3,2 , 1, 10, 5, 6, 10);
        int numberOfDrawTest = 1;
        assertEquals(numberOfDrawTest, footballClub.getNumberOfMatchDraw());
    }

    @Test
    public void setNumberOfMatchDraw() {
        FootballClub footballClub = new FootballClub();
        int numberOfDrawTest = 1;
        footballClub.setNumberOfMatchDraw(1);
        assertEquals(numberOfDrawTest, footballClub.getNumberOfMatchDraw());
    }

    @Test
    public void getNumberOfGoalsScored() {
        FootballClub footballClub = new FootballClub("Zodec", 3,2 , 1, 10, 5, 6, 10);
        int numberOfGoalsScoredTest = 10;
        assertEquals(numberOfGoalsScoredTest, footballClub.getNumberOfGoalsScored());
    }

    @Test
    public void setNumberOfGoalsScored() {
        FootballClub footballClub = new FootballClub();
        int numberOfGoalsScoredTest = 10;
        footballClub.setNumberOfGoalsScored(10);
        assertEquals(numberOfGoalsScoredTest, footballClub.getNumberOfGoalsScored());
    }

    @Test
    public void getNumberOfGoalsReceived() {
        FootballClub footballClub = new FootballClub("Zodec", 3,2 , 1, 10, 5, 6, 10);
        int numberOfGoalsReceivedTest = 5;
        assertEquals(numberOfGoalsReceivedTest, footballClub.getNumberOfGoalsReceived());
    }

    @Test
    public void setNumberOfGoalsReceived() {
        FootballClub footballClub = new FootballClub();
        int numberOfGoalsReceivedTest = 5;
        footballClub.setNumberOfGoalsReceived(5);
        assertEquals(numberOfGoalsReceivedTest, footballClub.getNumberOfGoalsReceived());
    }

    @Test
    public void getNumberOfMatchesPlayed() {
        FootballClub footballClub = new FootballClub("Zodec", 3,2 , 1, 10, 5, 6, 10);
        int numberOfMatchedPlayedTest = 6;
        assertEquals(numberOfMatchedPlayedTest, footballClub.getNumberOfMatchesPlayed());
    }

    @Test
    public void setNumberOfMatchesPlayed() {
        FootballClub footballClub = new FootballClub();
        int numberOfMatchedPlayedTest = 6;
        footballClub.setNumberOfMatchesPlayed(6);
        assertEquals(numberOfMatchedPlayedTest, footballClub.getNumberOfMatchesPlayed());
    }

    @Test
    public void getCurrentNumberOfPoints() {
        FootballClub footballClub = new FootballClub("Zodec", 3,2 , 1, 10, 5, 6, 10);
        int currentNumberOfPointsTest = 10;
        assertEquals(currentNumberOfPointsTest, footballClub.getCurrentNumberOfPoints());
    }

    @Test
    public void setCurrentNumberOfPoints() {
        FootballClub footballClub = new FootballClub();
        int currentNumberOfPointsTest = 10;
        footballClub.setCurrentNumberOfPoints(10);
        assertEquals(currentNumberOfPointsTest, footballClub.getCurrentNumberOfPoints());

    }
}
