package model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SchoolFootballClubTest {

    @Test
    public void getNameOfSchool() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub("Zodec", "Colombo", Color.RED, "John", "10002", "Micheal", "Zahira College", "861", "Andrew", "1892");
        String nameOfSchoolTest = "Zahira College";
        assertEquals(nameOfSchoolTest, schoolFootballClub.getNameOfSchool());
    }

    @Test
    public void setNameOfSchool() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub();
        String nameOfSchoolTest = "Zahira College";
        schoolFootballClub.setNameOfSchool("Zahira College");
        assertEquals(nameOfSchoolTest, schoolFootballClub.getNameOfSchool());
    }

    @Test
    public void getSchoolCode() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub("Zodec", "Colombo", Color.RED, "John", "10002", "Micheal", "Zahira College", "861", "Andrew", "1892");
        String schoolCodeTest = "861";
        assertEquals(schoolCodeTest, schoolFootballClub.getSchoolCode());
    }

    @Test
    public void setSchoolCode() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub();
        String schoolCodeTest = "861";
        schoolFootballClub.setSchoolCode("861");
        assertEquals(schoolCodeTest, schoolFootballClub.getSchoolCode());
    }

    @Test
    public void getNameOfCoach() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub("Zodec", "Colombo", Color.RED, "John", "10002", "Micheal", "Zahira College", "861", "Andrew", "1892");
        String nameOfCoachTest = "Andrew";
        assertEquals(nameOfCoachTest, schoolFootballClub.getNameOfCoach());
    }

    @Test
    public void setNameOfCoach() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub();
        String nameOfCoachTest = "Andrew";
        schoolFootballClub.setNameOfCoach("Andrew");
        assertEquals(nameOfCoachTest, schoolFootballClub.getNameOfCoach());
    }

    @Test
    public void getYearOfEstablishment() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub("Zodec", "Colombo", Color.RED, "John", "10002", "Micheal", "Zahira College", "861", "Andrew", "1892");
        String yearOfEstablishmentTest = "1892";
        assertEquals(yearOfEstablishmentTest, schoolFootballClub.getYearOfEstablishment());
    }

    @Test
    public void setYearOfEstablishment() {
        SchoolFootballClub schoolFootballClub = new SchoolFootballClub();
        String yearOfEstablishmentTest = "1892";
        schoolFootballClub.setYearOfEstablishment("1892");
        assertEquals(yearOfEstablishmentTest, schoolFootballClub.getYearOfEstablishment());
    }
}
