package model;

import java.awt.*;
import java.io.Serializable;

public class SchoolFootballClub extends FootballClub implements Serializable {
    private String nameOfSchool;
    private String schoolCode;
    private String nameOfCoach;
    private String yearOfEstablishment;

    public SchoolFootballClub(String nameOfSportsClub, String locationOfSportsClub, Color teamColor, String nameOfManger, String footballClubId, String nameOfCaptain, String nameOfSchool, String schoolCode, String nameOfCoach, String yearOfEstablishment) {
        super(nameOfSportsClub, locationOfSportsClub, teamColor, nameOfManger, footballClubId, nameOfCaptain);
        this.nameOfSchool = nameOfSchool;
        this.schoolCode = schoolCode;
        this.nameOfCoach = nameOfCoach;
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public SchoolFootballClub(){ }

    public String getNameOfSchool() {
        return nameOfSchool;
    }

    public void setNameOfSchool(String nameOfSchool) {
        this.nameOfSchool = nameOfSchool;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getNameOfCoach() {
        return nameOfCoach;
    }

    public void setNameOfCoach(String nameOfCoach) {
        this.nameOfCoach = nameOfCoach;
    }

    public String getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(String yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }


    @Override
    public String toString() {
        return super.toString();
    }

}
