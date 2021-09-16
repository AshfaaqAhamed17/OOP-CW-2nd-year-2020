package model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SportsClubTest {

    @Test
    public void getNameOfSportsClub() {
        SportsClub sportsClub = new FootballClub("Club A", "Colombo", Color.RED, "John","10002", "Micheal");
        String nameOfSportsClubTest = "Club A";
        assertEquals(nameOfSportsClubTest, sportsClub.getNameOfSportsClub());
    }

    @Test
    public void setNameOfSportsClub() {
        SportsClub sportsClub = new FootballClub();
        String nameOfSportsClub = "Club B";
        sportsClub.setNameOfSportsClub("Club B");
        assertEquals(nameOfSportsClub, sportsClub.getNameOfSportsClub());
    }

    @Test
    public void getLocationOfSportsClub() {
        SportsClub sportsClub = new FootballClub("Club A", "Colombo", Color.RED, "John","10002", "Micheal");
        String nameOfSportsClubTest = "Colombo";
        assertEquals(nameOfSportsClubTest, sportsClub.getLocationOfSportsClub());
    }

    @Test
    public void setLocationOfSportsClub() {
        SportsClub sportsClub = new FootballClub();
        String locationOfSportsClubTest = "Colombo";
        sportsClub.setLocationOfSportsClub("Colombo");
        assertEquals(locationOfSportsClubTest, sportsClub.getLocationOfSportsClub());
    }

    @Test
    public void getNameOfManger() {
        SportsClub sportsClub = new FootballClub("Club A", "Colombo", Color.RED, "John","10002", "Micheal");
        String nameOfManagerTest = "John";
        assertEquals(nameOfManagerTest, sportsClub.getNameOfManger());
    }

    @Test
    public void setNameOfManger() {
        SportsClub sportsClub = new FootballClub();
        String nameOfManagerTest = "John";
        sportsClub.setNameOfManger("John");
        assertEquals(nameOfManagerTest, sportsClub.getNameOfManger());
    }
}
