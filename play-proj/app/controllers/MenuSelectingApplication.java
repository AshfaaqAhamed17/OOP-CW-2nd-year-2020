package controllers;

import model.FootballClub;
import model.SchoolFootballClub;
import model.UniversityFootballClub;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

public class MenuSelectingApplication extends Application {
    static LeagueManager premierLeagueManager = new PremierLeagueManager();
    public static Scanner premierLeagueInputScanner = new Scanner(System.in);

    static FootballClub footballTeamOneMatch = null;
    static FootballClub footballTeamTwoMatch = null;

    public static void main(String[] args) throws IOException {
        premierLeagueManager.readFromFile();
        launch(args);
    }
    //========================================================================================================================================================================>
    public void start(Stage mainStage) throws IOException, IllegalAccessException {
        optionMenu:
        while (true) {

            System.out.println("\n\n\n\t|******************************************************************************************************************************************************************|");
            System.out.println("\t|------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.println("\t|------------------------------------ F O O T B A L L   P R E M I E R    L E A G U E     M A N A G E M E N T    S Y S T E M ---------------------------------------|");
            System.out.println("\t|------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.println("\t|******************************************************************************************************************************************************************|\n");

            System.out.println(" \t\tEnter '1' to add new club to the league");
            System.out.println(" \t\tEnter '2' to delete a club from the league");
            System.out.println(" \t\tEnter '3' to print statistics of a particular club in the league");
            System.out.println(" \t\tEnter '4' to display statistics and points table of the premier league");
            System.out.println(" \t\tEnter '5' to update a played match");
            System.out.println(" \t\tEnter '6' to write/Save to a file");
            System.out.println(" \t\tEnter '7' to open GUI");
            System.out.println(" \t\tEnter '8' to open Angular Project");
            System.out.println(" \t\tEnter 'X' to end the program\n");
            System.out.print("-->> Select an option : ");
            String selectMenu = premierLeagueInputScanner.nextLine();           //Menu option to select which method to start with

            switch (selectMenu){
                case "1":
                    addNewClubToLeague();                                       //add new club to the league
                    break;

                case "2":
                    deleteClubFromLeague();                                     //delete a club from the league
                    break;

                case "3":
                    printStatisticsOfClub();                                    //print statistics of a particular club
                    break;

                case "4":
                    premierLeagueManager.printPointsTableOfTheLeague();         //display statistics and points table
                    break;

                case "5":
                    updatePlayedMatch();                                        //update a played match
                    break;

                case "6":
                    premierLeagueManager.writeToFile();                         //write/Save details to a file
                    break;

                case "7":
                    premierLeagueManager.openGUIJavaFX(mainStage);              //Open GUI with JAVAFX
                    break;

                case "8":                                                       //Open GUI with Angular
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome http://localhost:4200/teams"});
                    break;

                case "x":                                                       //exit from program
                case "X":
                    System.out.println("==========================================================================================================");
                    System.out.println("=                                           !!!Thank you!!!                                              =");
                    System.out.println("==========================================================================================================");
                    break optionMenu;

                default:
                    System.out.println("\n------------------------------Invalid Option------------------------------\n");
                    break;
            }
        }
    }

//========================================================================================================================================================================>

    public static void printStatisticsOfClub() throws IOException {                                    //Method to print stats of a particular club

        System.out.print("\nEnter ID or NAME of club to be printed : ");              //Input ID or Name to print their stats
        String searchClub = premierLeagueInputScanner.nextLine().toUpperCase();

        premierLeagueManager.printStatisticsOfClub(searchClub);                     //Passing the ID to Premiere League Manager to search club
    }

//========================================================================================================================================================================>

    public static void matchUpdateQuestions(String teamOne){                        //Method to get inputs when updating a match
        DateFormat dateOfMatchPlayed;
        String inputDate;
        String teamTwo;
        String scoreOfTeamOne;
        String scoreOfTeamTwo;
        String victoryTeam = null;
        while (true) {
            try {
                System.out.print("Enter number of goals of team 1 : ");             //Input number of goals scored by team 1
                scoreOfTeamOne = premierLeagueInputScanner.nextLine();

                while (true) {
                    if (!scoreOfTeamOne.matches("[0-9]+")) {                  //Validations for number of goals scored
                        System.out.println("------------------------------Please enter valid entry------------------------------\n");
                        System.out.print("\nEnter number of goals of team 1 : ");
                        scoreOfTeamOne = premierLeagueInputScanner.nextLine();
                    } else {
                        break;
                    }
                }

                System.out.print("Enter team \"2\" : ");                            //Input name of team 2
                teamTwo = premierLeagueInputScanner.nextLine().toUpperCase();

                boolean found2 = false;
                while (true) {
                    try {
                        for (int exloop = 0; exloop <= PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                            if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(teamTwo))) {
                                footballTeamTwoMatch =PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                                found2 = true;
                                break;
                            }
                        }
                        if (!found2){                                               //Check if team is available in the league
                            System.out.println("------------------------------Invalid team in league------------------------------");
                            System.out.print("\nEnter team \"2\" : ");
                            teamTwo = premierLeagueInputScanner.nextLine().toUpperCase();
                        }else if (teamTwo.equals(teamOne)){                         //Check if team 2 is already selected as team 1
                            System.out.println("---------------Team already selected in this same match as 'Team 1'---------------");
                            System.out.print("\nEnter team \"2\" : ");
                            teamTwo = premierLeagueInputScanner.nextLine().toUpperCase();
                        }else break;
                    }catch (Exception e){
                        System.out.println("------------------------------Invalid team in league------------------------------");
                        System.out.print("\nEnter team \"2\" : ");
                        teamTwo = premierLeagueInputScanner.nextLine().toUpperCase();
                    }
                }

                System.out.print("Enter number of goals of team 2 : ");             //Input number of goals scored by team 2
                scoreOfTeamTwo = premierLeagueInputScanner.nextLine();
                while (true) {
                    if (!scoreOfTeamTwo.matches("[0-9]+")) {                  //Validations for number of goals scored
                        System.out.println("------------------------------Please enter valid entry------------------------------\n");
                        System.out.print("\nEnter number of goals of team 2 : ");
                        scoreOfTeamTwo = premierLeagueInputScanner.nextLine();
                    } else {
                        break;
                    }
                }

                while (true) {                                                      //Input date of match played
                    System.out.print("Enter date of match played (YYYY-MM-DD) : ");
                    inputDate = premierLeagueInputScanner.nextLine();
                    try {
                        dateOfMatchPlayed = new SimpleDateFormat("yyyy-MM-dd");     //Check if its a valid date
                        dateOfMatchPlayed.setLenient(false);
                        dateOfMatchPlayed.parse(inputDate);                                //Converting the input date to Date format
                        break;
                    } catch (ParseException e) {
                        System.out.println("------------------------------Invalid format of date------------------------------\n");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("------------------------------Invalid inputs------------------------------");
            }
        }
        premierLeagueManager.updatePlayedMatch(inputDate, footballTeamOneMatch, footballTeamTwoMatch, scoreOfTeamOne, scoreOfTeamTwo, victoryTeam);

    }

//========================================================================================================================================================================>

    public static void updatePlayedMatch() {                                            //Check if possible to add a match
        String checkClubCategory;
        String teamOne = null;
        UniversityFootballClub universityFootballClub;
        SchoolFootballClub schoolFootballClub;
        int footballClubCounter = 0;
        int schoolFootballClubCounter = 0;
        int universityFootballClubCounter = 0;

        if (PremierLeagueManager.footballClubRegistrationArrayList.size() == 0) {                           //Check if there are teams in the league to start a match
            System.out.print("\n------------------------------No teams registered yet------------------------------\n");
        } else{
            for (int exloop = 0; exloop < PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {  //Counter to check number of clubs in each category
                if (!((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) || (PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                    footballClubCounter++;
                } else if (PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) {
                    schoolFootballClub = (SchoolFootballClub) PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                    schoolFootballClubCounter++;
                } else if (PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub) {
                    universityFootballClub = (UniversityFootballClub) PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                    universityFootballClubCounter++;
                }
            }

            while (true) {
                try {
                    System.out.println("\nPlease select the type of club");
                    System.out.println(" \"A\" - Football Club");
                    System.out.println(" \"B\" - School Level Football Club");
                    System.out.println(" \"C\" - University Level Football Club");
                    System.out.print("\n-->> Select an option : ");
                    checkClubCategory = premierLeagueInputScanner.nextLine().toUpperCase();

                    while (true) {
                        if (!checkClubCategory.matches("[a-cA-C]")) {
                            System.out.println("------------------------------Please enter a valid selection------------------------------\n");
                            System.out.println("Please select the type of club");
                            System.out.println(" \"A\" - Football Club");
                            System.out.println(" \"B\" - School Level Football Club");
                            System.out.println(" \"C\" - University Level Football Club");
                            System.out.print("\n-->> Select an option : ");
                            checkClubCategory = premierLeagueInputScanner.nextLine().toUpperCase();
                        } else {
                            break;
                        }
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("------------------------------Invalid inputs------------------------------\n");
                }
            }


            switch (checkClubCategory) {
                case "A":
                    if (footballClubCounter < 2) {                                  //Check if there are enough teams in Football club category
                        System.out.print("\n------------------------------Not enough teams to start a match------------------------------\n");
                    } else {
                        System.out.print("Enter team \"1\" : ");
                        teamOne = premierLeagueInputScanner.nextLine().toUpperCase();

                        boolean found = false;
                        while (true) {
                            try {
                                for (int exloop = 0; exloop <= PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {   //Check if entered team is from Football Club category
                                    if (!((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) || (PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                                        break;
                                    }
                                }
                                for (int exloop = 0; exloop <= PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                                    if (!((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) || (PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                                        if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(teamOne))) {
                                            footballTeamOneMatch = PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (!found) {
                                    System.out.println("------------------------------Invalid team in league------------------------------");
                                    System.out.println("\nEnter team \"1\" : ");
                                    teamOne = premierLeagueInputScanner.nextLine().toUpperCase();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("------------------------------TEAM NOT FOUND IN FOOTBALL CLUB------------------------------");
                                System.out.println("\nEnter team \"1\" : ");
                                teamOne = premierLeagueInputScanner.nextLine().toUpperCase();
                            }
                        }
                        matchUpdateQuestions(teamOne);
                        break;
                    }
                    break;

                case "B":
                    if (schoolFootballClubCounter < 2) {                            //Check if there are enough teams in School Football club category
                        System.out.print("\n------------------------------Not enough teams to start a match------------------------------\n");
                    } else {
                        System.out.print("Enter team \"1\" : ");
                        teamOne = premierLeagueInputScanner.nextLine().toUpperCase();

                        boolean found = false;
                        while (true) {
                            try {
                                for (int exloop = 0; exloop <= PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {   //Check if entered team is from School Football Club category
                                    if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)) {
                                        break;
                                    }
                                }
                                for (int exloop = 0; exloop <= PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                                    if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)) {

                                        if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(teamOne))) {
                                            footballTeamOneMatch = PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (!found) {
                                    System.out.println("------------------------------Invalid team in league------------------------------");
                                    System.out.print("\nEnter team \"1\" : ");
                                    teamOne = premierLeagueInputScanner.nextLine().toUpperCase();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("------------------------------TEAM NOT FOUND IN SCHOOL FOOTBALL CLUB------------------------------");
                                System.out.print("\nEnter team \"1\" : ");
                                teamOne = premierLeagueInputScanner.nextLine().toUpperCase();
                            }
                        }
                        matchUpdateQuestions(teamOne);
                        break;
                    }
                    break;
                case "C":
                    if (universityFootballClubCounter < 2) {                            //Check if there are enough teams in University Football club category
                        System.out.print("\n------------------------------Not enough teams to start a match------------------------------\n");
                    } else {
                        System.out.print("Enter team \"1\" : ");
                        teamOne = premierLeagueInputScanner.nextLine().toUpperCase();

                        boolean found = false;
                        while (true) {
                            try {
                                for (int exloop = 0; exloop <= PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {   //Check if entered team is from University Football Club category
                                    if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub)) {
                                        break;
                                    }
                                }
                                for (int exloop = 0; exloop <= PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                                    if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub)) {
                                        if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(teamOne))) {
                                            footballTeamOneMatch = PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (!found) {
                                    System.out.println("------------------------------Invalid team in league------------------------------");
                                    System.out.print("\nEnter team \"1\" : ");
                                    teamOne = premierLeagueInputScanner.nextLine().toUpperCase();
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("------------------------------TEAM NOT FOUND IN UNIVERSITY FOOTBALL CLUB------------------------------");
                                System.out.print("\nEnter team \"1\" : ");
                                teamOne = premierLeagueInputScanner.nextLine().toUpperCase();
                            }
                        }

                        matchUpdateQuestions(teamOne);
                        break;
                    }
                    break;
                default:
                    System.out.println("\n------------------------------Invalid Option------------------------------\n");
                    break;
            }
        }
    }
    //========================================================================================================================================================================>
    public static void deleteClubFromLeague() {                                            //Method to delete a club from the league

        System.out.print("\nEnter ID to be deleted OR press \"-\" to cancel: ");
        String searchDelete = premierLeagueInputScanner.nextLine().toUpperCase();

        premierLeagueManager.deleteClubFromLeague(searchDelete);                            //Passing the club detail to Premiere League Manager delete the club from the league
    }
    //========================================================================================================================================================================>
    public static void addNewClubToLeague() throws IllegalAccessException{                  //Add a new club to the League
        String clubType;
        String nameOfClub;
        String clubId;
        String clubLocation;
        String teamColor;
        String clubManager;
        String clubCaptain;
        String nameOfSchool;
        String schoolCode;
        String nameOfCoach;
        String yearOfEstablishment;
        String nameOfUniversity;
        String universityCode;
        String numberOfLeaguesWon;
        FootballClub footballClub = null;
        SchoolFootballClub schoolFootballClub = null;
        UniversityFootballClub universityFootballClub = null;

        while (true) {
            try {
                System.out.println("\nPlease select the type of club");                   //Select club category to Add a club
                System.out.println(" \"A\" - Football Club");
                System.out.println(" \"B\" - School Level Football Club");
                System.out.println(" \"C\" - University Level Football Club");
                System.out.print("\n-->> Select an option : ");
                clubType = premierLeagueInputScanner.nextLine().toUpperCase();

                while (true){
                    if (!clubType.matches("[a-cA-C]")) {
                        System.out.println("------------------------------Please enter a valid selection------------------------------\n");
                        System.out.println("Please select the type of club");
                        System.out.println(" \"A\" - Football Club");
                        System.out.println(" \"B\" - School Level Football Club");
                        System.out.println(" \"C\" - University Level Football Club");
                        System.out.print("-->> Select an option : ");
                        clubType = premierLeagueInputScanner.nextLine().toUpperCase();
                    }else {
                        break;
                    }
                }

                System.out.print("Enter name of the club : ");                          //Enter the name of club
                nameOfClub = premierLeagueInputScanner.nextLine().toUpperCase();
                boolean checkTakenClubName = true;
                while (checkTakenClubName){
                    if (!nameOfClub.matches("[a-zA-Z0-9\\s]+")) {                 //Validations done for club name
                        System.out.println("------------------------------Please enter valid name------------------------------\n");
                        System.out.print("\nEnter name of the club : ");
                        nameOfClub = premierLeagueInputScanner.nextLine().toUpperCase();
                        checkTakenClubName = true;
                    } else {
                        checkTakenClubName = false;
                    }

                    for (int exloop = 0; exloop < PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {    //Check if club with same name already exists in the League
                        try {
                            if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(nameOfClub))) {
                                System.out.println("------------------------------Club with this name is already registered------------------------------\n");
                                System.out.print("\nEnter name of the club : ");
                                nameOfClub = premierLeagueInputScanner.nextLine().toUpperCase();
                                checkTakenClubName = true;
                                break;
                            } else {
                                checkTakenClubName = false;
                            }
                        }catch (Exception e){
                            System.out.println("------------------------------Please enter valid name------------------------------\n");
                            System.out.print("\nEnter name of the club : ");
                            nameOfClub = premierLeagueInputScanner.nextLine().toUpperCase();
                            checkTakenClubName = true;
                        }
                    }
                }

                System.out.print("Enter club ID : ");                                   //Enter Club ID
                clubId = premierLeagueInputScanner.nextLine();

                boolean checkTakenClubID = true;
                while (checkTakenClubID) {
                    if (!clubId.matches("[0-9A-Z]+")) {                           //Validations for club ID
                        System.out.println("------------------------------Please enter valid ID number------------------------------\n");
                        System.out.print("\nEnter club ID : ");
                        clubId = premierLeagueInputScanner.nextLine();
                        checkTakenClubID = true;
                    } else {
                        checkTakenClubID = false;
                    }

                    for (int exloop = 0; exloop < PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                        try {
                            if ((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop).getFootballClubId() == parseInt(clubId))) {     //Check if Club with same club ID already exists
                                System.out.println("------------------------------ID is already taken------------------------------\n");
                                System.out.print("\nEnter club ID : ");
                                clubId = premierLeagueInputScanner.nextLine();
                                checkTakenClubID = true;
                                break;
                            } else {
                                checkTakenClubID = false;
                            }
                        }catch (Exception e){
                            System.out.println("------------------------------Please enter valid ID number------------------------------\n");
                            System.out.print("\nEnter club ID : ");
                            clubId = premierLeagueInputScanner.nextLine();
                            checkTakenClubID = true;
                        }
                    }
                }

                System.out.print("Enter location of the club (District name)  : ");     //Enter location of club
                clubLocation = premierLeagueInputScanner.nextLine();

                while (true) {
                    if (!clubLocation.matches("[a-zA-Z\\s]+")) {                  //Validations to for club location
                        System.out.println("------------------------------Please enter valid name------------------------------\n");
                        System.out.print("\nEnter location of the club (District name)  : ");
                        clubLocation = premierLeagueInputScanner.nextLine().toUpperCase();
                    } else {
                        break;
                    }
                }

                System.out.print("Enter team color : ");                                //Enter team color
                teamColor = premierLeagueInputScanner.nextLine().toUpperCase();
                Color color;
                Field field = null;

                while (true) {
                    if (teamColor.matches("[a-zA-Z-_]+")) {
                        try {
                            field = Class.forName("java.awt.Color").getField(teamColor.toLowerCase());  //Converting string color to Color
                        } catch (ClassNotFoundException | NoSuchFieldException e) {
                            System.out.println("------------------------------Invalid color selected------------------------------");
                            System.out.print("\nEnter team color : ");
                            teamColor = premierLeagueInputScanner.nextLine().toUpperCase();
                        }
                        if (field != null) {
                            color = (Color) field.get(null);
                            break;
                        }
                    }else {
                        System.out.println("------------------------------Invalid color selected------------------------------");
                        System.out.print("\nEnter team color : ");
                        teamColor = premierLeagueInputScanner.nextLine().toUpperCase();
                    }
                }

                System.out.print("Enter name of the club manager : ");                //Enter club managers name
                clubManager = premierLeagueInputScanner.nextLine().toUpperCase();
                while (true) {
                    if (!clubManager.matches("[a-zA-Z\\s]+")) {                 //Validations for entering club managers name
                        System.out.println("------------------------------Please enter valid name------------------------------\n");
                        System.out.print("\nEnter name of the club manager : ");
                        clubManager = premierLeagueInputScanner.nextLine().toUpperCase();
                    } else {
                        break;
                    }
                }

                System.out.print("Enter name of club captain : ");                    //Entering club captains name
                clubCaptain = premierLeagueInputScanner.nextLine().toUpperCase();
                while (true) {
                    if (!clubCaptain.matches("[a-zA-Z\\s]+")) {                 //Validations for entering club captains name
                        System.out.println("------------------------------Please enter valid name------------------------------\n");
                        System.out.print("\nEnter name of the club captain : ");
                        clubCaptain = premierLeagueInputScanner.nextLine().toUpperCase();
                    } else {
                        break;
                    }
                }

                switch (clubType) {
                    case "A":
                        //If Football CLub category is selected, pass the values to Premiere League Manager
                        footballClub = new FootballClub(nameOfClub, clubLocation, color, clubManager, clubId, clubCaptain);
                        break;
                    //========================================================================================================================================================================>
                    case "B":

                        System.out.print("Enter name of the school associated : ");     //Enter name of school associated by the club
                        nameOfSchool = premierLeagueInputScanner.nextLine().toUpperCase();
                        while (true) {
                            if (!nameOfSchool.matches("[a-zA-Z\\s]+")) {          //Validations for entering name of school
                                System.out.println("------------------------------Please enter valid entry------------------------------\n");
                                System.out.print("\nEnter name of the school associated : ");
                                nameOfSchool = premierLeagueInputScanner.nextLine().toUpperCase();
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter school code : ");                         //Enter school code
                        schoolCode = premierLeagueInputScanner.nextLine().toUpperCase();

                        boolean checkTakenSchoolCode = true;
                        while (checkTakenSchoolCode) {
                            if (!schoolCode.matches("[0-9A-Z]+")) {                 //Validations for entering school code
                                System.out.println("------------------------------Please enter valid school code------------------------------\n");
                                System.out.print("\nEnter school code : ");
                                schoolCode = premierLeagueInputScanner.nextLine().toUpperCase();
                                checkTakenSchoolCode = true;

                            } else {
                                checkTakenSchoolCode = false;
                            }

                            for (int exloop = 0; exloop < PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                                if (PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub){
                                    schoolFootballClub = (SchoolFootballClub) PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                                    try {
                                        if (schoolFootballClub.getSchoolCode().equals(schoolCode)) {    //Check if school code is already mentioned in the Premiere League
                                            System.out.println("---------------School code has already been mentioned---------------\n");
                                            System.out.print("\nEnter school code : ");
                                            schoolCode = premierLeagueInputScanner.nextLine().toUpperCase();
                                            checkTakenSchoolCode = true;
                                            break;
                                        } else {
                                            checkTakenSchoolCode = false;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        System.out.println("-------------------------Please enter valid school code------------------------------\n");
                                        System.out.print("\nEnter school code : ");
                                        schoolCode = premierLeagueInputScanner.nextLine().toUpperCase();
                                        checkTakenSchoolCode = true;
                                    }
                                }
                            }
                        }


                        System.out.print("Enter name of the coach : ");                 //Enter name of the coach of the club
                        nameOfCoach = premierLeagueInputScanner.nextLine().toUpperCase();
                        while (true) {
                            if (!nameOfCoach.matches("[a-zA-Z\\s]+")) {             //Validations for entering name of coach
                                System.out.println("------------------------------Please enter valid entry------------------------------\n");
                                System.out.print("\nEnter name of the coach : ");
                                nameOfCoach = premierLeagueInputScanner.nextLine().toUpperCase();
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter the year in which the school was established : ");      //Enter year in which the school was established
                        yearOfEstablishment = premierLeagueInputScanner.nextLine();
                        while (true) {
                            if (!yearOfEstablishment.matches("[0-9]+")){                          //Validations for entering the year of establishment
                                System.out.println("------------------------------Please enter valid entry------------------------------\n");
                                System.out.print("\nEnter the year in which the school was established : ");
                                yearOfEstablishment = premierLeagueInputScanner.nextLine();
                            }                                                                           //Check if the year entered is eligible
                            else if (((parseInt(yearOfEstablishment) <= 1800)||(parseInt(yearOfEstablishment) >= 2020))||(!yearOfEstablishment.matches("[0-9]+"))) {
                                System.out.println("------------------------------Please enter valid entry------------------------------\n");
                                System.out.print("\nEnter the year in which the school was established : ");
                                yearOfEstablishment = premierLeagueInputScanner.nextLine();
                            } else {
                                break;
                            }
                        }
                        //If School Football CLub category is selected, pass the values to Premiere League Manager
                        footballClub = new SchoolFootballClub(nameOfClub, clubLocation, color, clubManager, clubId, clubCaptain,nameOfSchool,schoolCode,nameOfCoach,yearOfEstablishment);
                        break;
                    //========================================================================================================================================================================>
                    case "C":

                        System.out.print("Enter name of the university associated : ");                 //Entering name of university associated by the club
                        nameOfUniversity = premierLeagueInputScanner.nextLine().toUpperCase();
                        while (true) {
                            if (!nameOfUniversity.matches("[a-zA-Z\\s]+")) {                        //Validations for entering the university name
                                System.out.println("------------------------------Please enter valid entry------------------------------\n");
                                System.out.print("\nEnter name of the university associated : ");
                                nameOfUniversity = premierLeagueInputScanner.nextLine().toUpperCase();
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter university code : ");                                   //Entering the university code
                        universityCode = premierLeagueInputScanner.nextLine().toUpperCase();
                        boolean checkTakenUniversityCode = true;
                        while (checkTakenUniversityCode) {
                            if (!universityCode.matches("[0-9A-Z]+")) {                             //Validations for entering the university code
                                System.out.println("------------------------------Please enter valid university code------------------------------\n");
                                System.out.print("\nEnter university code : ");
                                universityCode = premierLeagueInputScanner.nextLine().toUpperCase();
                                checkTakenUniversityCode = true;
                            } else {
                                checkTakenUniversityCode = false;
                            }

                            for (int exloop = 0; exloop < PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                                if (PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub){
                                    universityFootballClub = (UniversityFootballClub) PremierLeagueManager.footballClubRegistrationArrayList.get(exloop);
                                    try {
                                        if ((universityFootballClub.getUniversityCode()).equals(universityCode)){ //Check if university code is already mentioned in the League
                                            System.out.println("------------------------------University code has already been mentioned------------------------------\n");
                                            System.out.print("\nEnter university code : ");
                                            universityCode = premierLeagueInputScanner.nextLine().toUpperCase();
                                            checkTakenUniversityCode = true;
                                            break;
                                        } else {
                                            checkTakenUniversityCode = false;
                                        }
                                    }catch (Exception e) {
                                        System.out.println("------------------------------Please enter valid university code------------------------------\n");
                                        System.out.print("\nEnter university code : ");
                                        universityCode = premierLeagueInputScanner.nextLine().toUpperCase();
                                        checkTakenUniversityCode = true;
                                    }
                                }
                            }
                        }

                        System.out.print("Enter number of leagues won previously : ");                          //Enter number of leagues won previously
                        numberOfLeaguesWon = premierLeagueInputScanner.nextLine();
                        while (true) {
                            if (!numberOfLeaguesWon.matches("[0-9]+")) {                                    //Validations to check entered number of leagues won previously
                                System.out.print("------------------------------Please enter valid entry------------------------------\n");
                                System.out.print("\nEnter number of leagues won previously : ");
                                numberOfLeaguesWon = premierLeagueInputScanner.nextLine();
                            } else {
                                break;
                            }
                        }
                        //If University Football CLub category is selected, pass the values to Premiere League Manager
                        footballClub = new UniversityFootballClub(nameOfClub, clubLocation, color, clubManager, clubId, clubCaptain,nameOfUniversity,universityCode,numberOfLeaguesWon);
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("------------------------------Invalid inputs------------------------------\n");
            }
        }
        //Passing the club details to Premier League Manager to add the club to the League
        premierLeagueManager.addNewClubToLeague(footballClub);
    }
}

//========================================================================================================================================================================>
