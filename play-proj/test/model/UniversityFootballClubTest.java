package model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class UniversityFootballClubTest {

    @Test
    public void getNameOfUniversity() {
        UniversityFootballClub universityFootballClub = new UniversityFootballClub("Zodec", "Colombo", Color.RED, "John", "10002", "Micheal", "IIT", "W1762020", "20");
        String nameOfUniversityTest = "IIT";
        assertEquals(nameOfUniversityTest, universityFootballClub.getNameOfUniversity());
    }

    @Test
    public void setNameOfUniversity() {
        UniversityFootballClub universityFootballClub = new UniversityFootballClub();
        String nameOfUniversityTest = "IIT";
        universityFootballClub.setNameOfUniversity("IIT");
        assertEquals(nameOfUniversityTest, universityFootballClub.getNameOfUniversity());
    }

    @Test
    public void getUniversityCode() {
        UniversityFootballClub universityFootballClub = new UniversityFootballClub("Zodec", "Colombo", Color.RED, "John", "10002", "Micheal", "IIT", "W1762020", "20");
        String universityCodeTest = "W1762020";
        assertEquals(universityCodeTest, universityFootballClub.getUniversityCode());
    }

    @Test
    public void setUniversityCode() {
        UniversityFootballClub universityFootballClub = new UniversityFootballClub();
        String universityCodeTest = "W1762020";
        universityFootballClub.setUniversityCode("W1762020");
        assertEquals(universityCodeTest, universityFootballClub.getUniversityCode());
    }

    @Test
    public void getNumberOfLeaguesWon() {
        UniversityFootballClub universityFootballClub = new UniversityFootballClub("Zodec", "Colombo", Color.RED, "John", "10002", "Micheal", "IIT", "W1762020", "20");
        String numberOfLeagueTest = "20";
        assertEquals(numberOfLeagueTest, universityFootballClub.getNumberOfLeaguesWon());
    }

    @Test
    public void setNumberOfLeaguesWon() {
        UniversityFootballClub universityFootballClub = new UniversityFootballClub();
        String numberOfLeagueTest = "20";
        universityFootballClub.setNumberOfLeaguesWon("20");
        assertEquals(numberOfLeagueTest, universityFootballClub.getNumberOfLeaguesWon());
    }
}
