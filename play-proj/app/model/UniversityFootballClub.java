package model;

import java.awt.*;
import java.io.Serializable;

public class UniversityFootballClub extends FootballClub implements Serializable {
    private String nameOfUniversity;
    private String universityCode;
    private String numberOfLeaguesWon;

    public UniversityFootballClub(String nameOfSportsClub, String locationOfSportsClub, Color teamColor, String nameOfManger, String footballClubId, String nameOfCaptain, String nameOfUniversity, String universityCode, String numberOfLeaguesWon) {
        super(nameOfSportsClub, locationOfSportsClub, teamColor, nameOfManger, footballClubId, nameOfCaptain);
        this.nameOfUniversity = nameOfUniversity;
        this.universityCode = universityCode;
        this.numberOfLeaguesWon = numberOfLeaguesWon;
    }

    public UniversityFootballClub(){ }

    public String getNameOfUniversity() {
        return nameOfUniversity;
    }

    public void setNameOfUniversity(String nameOfUniversity) {
        this.nameOfUniversity = nameOfUniversity;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getNumberOfLeaguesWon() {
        return numberOfLeaguesWon;
    }

    public void setNumberOfLeaguesWon(String numberOfLeaguesWon) {
        this.numberOfLeaguesWon = numberOfLeaguesWon;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
