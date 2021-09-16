package controllers;

import model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager{

    static List<FootballClub> footballClubRegistrationArrayList = new ArrayList<>();            //Arraylist to store club details
    static List<MatchUpdater> playedMatchUpdaterArrayList = new ArrayList<>();         //Arraylist to store played match details
    static File footballClubRegistrationBinaryFile;                                     //Binary file to store club details
    static File playedMatchUpdateFile;                                            //Text file to store played match details
    static File playedMatchUpdateBinaryFile;                                      //Binary file to store played match details

    FootballClub footballTeamOne;                                           //Initializing Football club Obj (used to to update match)
    FootballClub footballTeamTwo;                                           //Initializing Football club Obj (used to to update match)
    SchoolFootballClub schoolFootballClub;                                  //Initializing School Football club Obj
    UniversityFootballClub universityFootballClub;                          //Initializing University Football club Obj

    int footballClubCounter = 0;                                            //Initializing a counter to count number of football clubs
    int schoolFootballClubCounter = 0;                                      //Initializing a counter to count number of school football clubs
    int universityFootballClubCounter = 0;                                  //Initializing a counter to count number of university football clubs

    @Override
    public void readFromFile() throws IOException {                         //Method to read the details from the file
        try{
            footballClubRegistrationBinaryFile = new File("FootballClubRegistrationBinaryFile.json");   //json file to store club registration details
            footballClubRegistrationBinaryFile.createNewFile();                                                  //create the file if file not available

            playedMatchUpdateBinaryFile = new File("MatchUpdateBinaryFile.json");                 //json file to store club played match details
            playedMatchUpdateBinaryFile.createNewFile();                                                   //create the file if file not available

        }catch (IOException e) {
            System.out.print("Creating file");
        }finally {
            try{
                FileInputStream footballFileInputStream = new FileInputStream(footballClubRegistrationBinaryFile);              //binary file input stream to read from file
                ObjectInputStream footballObjectInputStream = new ObjectInputStream(footballFileInputStream);       //binary file object input stream to read objects
                footballClubRegistrationArrayList = (ArrayList<FootballClub>) footballObjectInputStream.readObject();                   //reading

                FileInputStream matchUpdateFileInputStream = new FileInputStream(playedMatchUpdateBinaryFile);            //binary file input stream to read from file
                ObjectInputStream matchUpdateObjectInputStream = new ObjectInputStream(matchUpdateFileInputStream); //binary file object input stream to read objects
                playedMatchUpdaterArrayList = (ArrayList<MatchUpdater>) matchUpdateObjectInputStream.readObject();             //reading

                footballFileInputStream.close();
                footballObjectInputStream.close();

                matchUpdateFileInputStream.close();
                matchUpdateObjectInputStream.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (EOFException e){                                                                                //prevents error when it reaches the end of file <when reading objects Objects>
                System.out.print("\n======>System is successfully updated<======\n");
            }
        }
    }
//========================================================================================================================================================================>
    @Override
    public void writeToFile() throws IOException {                   //Method to write/save the details to the file
        try {
            footballClubRegistrationBinaryFile = new File("FootballClubRegistrationBinaryFile.json");               //json file to store club registration details
            footballClubRegistrationBinaryFile.createNewFile();                                                              //create the file if file not available

            playedMatchUpdateFile = new File("MatchUpdatingFile.txt");                                        //text file to store club played match details
            playedMatchUpdateFile.createNewFile();                                                                     //create the file if file not available

            playedMatchUpdateBinaryFile = new File("MatchUpdateBinaryFile.json");                             //json file to store club played match details
            playedMatchUpdateBinaryFile.createNewFile();                                                               //create the file if file not available

        }catch (IOException e){
            System.out.println("Creating new file");
        }finally {
            //---writing to text file to store played match details---
            FileWriter matchUpdateFileWriter = new FileWriter(playedMatchUpdateFile);
            PrintWriter matchUpdatePrintWriter = new PrintWriter(matchUpdateFileWriter, true);

            for (int exloop = 0; exloop < playedMatchUpdaterArrayList.size() ; exloop++){
                matchUpdatePrintWriter.println(playedMatchUpdaterArrayList.get(exloop));
            }

            //---writing to binary file to store football club details---
            FileOutputStream footballClubOutputStream = new FileOutputStream(footballClubRegistrationBinaryFile);
            ObjectOutputStream footballClubObjectOutputStream = new ObjectOutputStream(footballClubOutputStream);
            footballClubObjectOutputStream.writeObject(footballClubRegistrationArrayList);

            //---writing to binary file to store played match details---
            FileOutputStream matchUpdateOutputStream = new FileOutputStream(playedMatchUpdateBinaryFile);
            ObjectOutputStream matchUpdateObjectOutputStream = new ObjectOutputStream(matchUpdateOutputStream);
            matchUpdateObjectOutputStream.writeObject(playedMatchUpdaterArrayList);

            matchUpdateFileWriter.close();
            matchUpdatePrintWriter.close();

            footballClubOutputStream.close();
            footballClubObjectOutputStream.close();

            matchUpdateOutputStream.close();
            matchUpdateObjectOutputStream.close();
        }

        System.out.println("\n----------------------------->Backup process is completed successfully<-----------------------------\n");
    }
//========================================================================================================================================================================>
    @Override
    public void updatePlayedMatch(String datePlayed, FootballClub teamOne, FootballClub teamTwo, String scoreOfTeamOne, String scoreOfTeamTwo, String victoryTeam) {
        MatchUpdater matchUpdater = new MatchUpdater(datePlayed, teamOne, teamTwo, scoreOfTeamOne, scoreOfTeamTwo, victoryTeam);

        footballTeamOne = matchUpdater.getMatchTeamOne();            //Setting teamOne to a Football CLub Obj
        footballTeamTwo = matchUpdater.getMatchTeamTwo();            //Setting teamTwo to a Football CLub Obj

        if( footballTeamOne.getClass() == footballTeamTwo.getClass() ) {        //Checking team 1 and team 2 entered are from the same category
            for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {
                //----Adding points for clubs from Football Club category
                if (!((footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) || (footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                    if (footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(footballTeamOne.getNameOfSportsClub())){
                        footballTeamOne.setNumberOfGoalsScored(Integer.parseInt(scoreOfTeamOne));           //setting score scored by team 1
                        footballTeamOne.setNumberOfGoalsReceived(Integer.parseInt(scoreOfTeamTwo));         //setting received scored by team 1
                        footballTeamOne.setNumberOfMatchesPlayed( 1);                                       //setting number of matches played by team 1
                        footballTeamTwo.setNumberOfGoalsScored(Integer.parseInt(scoreOfTeamTwo));           //setting score scored by team 2
                        footballTeamTwo.setNumberOfGoalsReceived(Integer.parseInt(scoreOfTeamOne));         //setting received scored by team 2
                        footballTeamTwo.setNumberOfMatchesPlayed( 1);                                       //setting number of matches played by team 2
                        //---Checking which team scored the most
                        if (Integer.parseInt(scoreOfTeamOne) > Integer.parseInt(scoreOfTeamTwo)) {          //IF TEAM ONE SCORES MORE
                            matchUpdater.setVictoryTeam(String.valueOf(matchUpdater.getMatchTeamOne()));         //Set TEAM ONE as victory team
                            footballTeamOne.setNumberOfWins(1);                                             //TEAM ONE gets +1 for number of wins
                            footballTeamTwo.setNumberOfLoss(1);                                             //TEAM TWO get +1 for number of loss
                            footballTeamOne.setCurrentNumberOfPoints(3);                                    //TEAM ONE gets +3 points for total points
                        }
                        else if (Integer.parseInt(scoreOfTeamOne) < Integer.parseInt(scoreOfTeamTwo)) {     //IF TEAM TWO SCORES MORE
                            matchUpdater.setVictoryTeam(String.valueOf(matchUpdater.getMatchTeamTwo()));         //Set TEAM TWO as victory team
                            footballTeamTwo.setNumberOfWins(1);                                             //TEAM TWO gets +1 for number of wins
                            footballTeamOne.setNumberOfLoss(1);                                             //TEAM ONE gets +1 for number of loss
                            footballTeamTwo.setCurrentNumberOfPoints(3);                                    //TEAM TWO gets +3 points for total points
                        }
                        else if (Integer.parseInt(scoreOfTeamOne) == Integer.parseInt(scoreOfTeamTwo)){     //If both score are equal
                            matchUpdater.setVictoryTeam("Match Draw");                                      //Set TEAM ONE as victory team
                            footballTeamOne.setNumberOfMatchDraw(1);                                        //TEAM ONE gets +1 for number of draws
                            footballTeamTwo.setNumberOfMatchDraw(1);                                        //TEAM TWO gets +1 for number of draws
                            footballTeamOne.setCurrentNumberOfPoints( 1);                                   //TEAM ONE gets +1 points for total points
                            footballTeamTwo.setCurrentNumberOfPoints( 1);                                   //TEAM TWO gets +1 points for total points
                        }
                    }
                }
                //----Adding points for clubs form School Football Club category
                else if (footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) {
                    if (footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(footballTeamOne.getNameOfSportsClub())){
                        footballTeamOne.setNumberOfGoalsScored(Integer.parseInt(scoreOfTeamOne));           //setting score scored by team 1
                        footballTeamOne.setNumberOfGoalsReceived(Integer.parseInt(scoreOfTeamTwo));         //setting received scored by team 1
                        footballTeamOne.setNumberOfMatchesPlayed( 1);                                       //setting number of matches played by team 1
                        footballTeamTwo.setNumberOfGoalsScored(Integer.parseInt(scoreOfTeamTwo));           //setting score scored by team 2
                        footballTeamTwo.setNumberOfGoalsReceived(Integer.parseInt(scoreOfTeamOne));         //setting received scored by team 2
                        footballTeamTwo.setNumberOfMatchesPlayed( 1);                                       //setting number of matches played by team 2
                        //---Checking which team scored the most
                        if (Integer.parseInt(scoreOfTeamOne) > Integer.parseInt(scoreOfTeamTwo)) {          //IF TEAM ONE SCORES MORE
                            matchUpdater.setVictoryTeam(String.valueOf(matchUpdater.getMatchTeamOne()));         //Set TEAM ONE as victory team
                            footballTeamOne.setNumberOfWins(1);                                             //TEAM ONE gets +1 for number of wins
                            footballTeamTwo.setNumberOfLoss(1);                                             //TEAM TWO get +1 for number of loss
                            footballTeamOne.setCurrentNumberOfPoints(3);                                    //TEAM ONE gets +3 points for total points
                        }
                        else if (Integer.parseInt(scoreOfTeamOne) < Integer.parseInt(scoreOfTeamTwo)) {     //IF TEAM TWO SCORES MORE
                            matchUpdater.setVictoryTeam(String.valueOf(matchUpdater.getMatchTeamTwo()));         //Set TEAM TWO as victory team
                            footballTeamTwo.setNumberOfWins(1);                                             //TEAM TWO gets +1 for number of wins
                            footballTeamOne.setNumberOfLoss(1);                                             //TEAM ONE gets +1 for number of loss
                            footballTeamTwo.setCurrentNumberOfPoints(3);                                    //TEAM TWO gets +3 points for total points
                        }
                        else if (Integer.parseInt(scoreOfTeamOne) == Integer.parseInt(scoreOfTeamTwo)){     //If both score are equal
                            matchUpdater.setVictoryTeam("Match Draw");                                      //Set TEAM ONE as victory team
                            footballTeamOne.setNumberOfMatchDraw(1);                                        //TEAM ONE gets +1 for number of draws
                            footballTeamTwo.setNumberOfMatchDraw(1);                                        //TEAM TWO gets +1 for number of draws
                            footballTeamOne.setCurrentNumberOfPoints( 1);                                   //TEAM ONE gets +1 points for total points
                            footballTeamTwo.setCurrentNumberOfPoints( 1);                                   //TEAM TWO gets +1 points for total points
                        }
                    }
                }
                //----Adding points for clubs form University Football Club category
                else if (footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub) {
                    if (footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(footballTeamOne.getNameOfSportsClub())) {
                        footballTeamOne.setNumberOfGoalsScored(Integer.parseInt(scoreOfTeamOne));           //setting score scored by team 1
                        footballTeamOne.setNumberOfGoalsReceived(Integer.parseInt(scoreOfTeamTwo));         //setting received scored by team 1
                        footballTeamOne.setNumberOfMatchesPlayed( 1);                                       //setting number of matches played by team 1
                        footballTeamTwo.setNumberOfGoalsScored(Integer.parseInt(scoreOfTeamTwo));           //setting score scored by team 2
                        footballTeamTwo.setNumberOfGoalsReceived(Integer.parseInt(scoreOfTeamOne));         //setting received scored by team 2
                        footballTeamTwo.setNumberOfMatchesPlayed( 1);                                       //setting number of matches played by team 2
                        //---Checking which team scored the most
                        if (Integer.parseInt(scoreOfTeamOne) > Integer.parseInt(scoreOfTeamTwo)) {          //IF TEAM ONE SCORES MORE
                            matchUpdater.setVictoryTeam(String.valueOf(matchUpdater.getMatchTeamOne()));         //Set TEAM ONE as victory team
                            footballTeamOne.setNumberOfWins(1);                                             //TEAM ONE gets +1 for number of wins
                            footballTeamTwo.setNumberOfLoss(1);                                             //TEAM TWO get +1 for number of loss
                            footballTeamOne.setCurrentNumberOfPoints(3);                                    //TEAM ONE gets +3 points for total points
                        }
                        else if (Integer.parseInt(scoreOfTeamOne) < Integer.parseInt(scoreOfTeamTwo)) {     //IF TEAM TWO SCORES MORE
                            matchUpdater.setVictoryTeam(String.valueOf(matchUpdater.getMatchTeamTwo()));         //Set TEAM TWO as victory team
                            footballTeamTwo.setNumberOfWins(1);                                             //TEAM TWO gets +1 for number of wins
                            footballTeamOne.setNumberOfLoss(1);                                             //TEAM ONE gets +1 for number of loss
                            footballTeamTwo.setCurrentNumberOfPoints(3);                                    //TEAM TWO gets +3 points for total points
                        }
                        else if (Integer.parseInt(scoreOfTeamOne) == Integer.parseInt(scoreOfTeamTwo)){     //If both score are equal
                            matchUpdater.setVictoryTeam("Match Draw");                                      //Set TEAM ONE as victory team
                            footballTeamOne.setNumberOfMatchDraw(1);                                        //TEAM ONE gets +1 for number of draws
                            footballTeamTwo.setNumberOfMatchDraw(1);                                        //TEAM TWO gets +1 for number of draws
                            footballTeamOne.setCurrentNumberOfPoints( 1);                                   //TEAM ONE gets +1 points for total points
                            footballTeamTwo.setCurrentNumberOfPoints( 1);                                   //TEAM TWO gets +1 points for total points
                        }
                    }
                }
            }
            playedMatchUpdaterArrayList.add(matchUpdater);
        }else {                                                                                             //Excpetion when two teams are not from same category
            System.out.println("\n--------------------------Two teams are from different categories--------------------------");
            matchUpdater.setVictoryTeam("Match Denied");
        }

        System.out.println("\nVictory team = "+ matchUpdater.getVictoryTeam());                             //Display the victory team
    }

//========================================================================================================================================================================>
    @Override
    public void printPointsTableOfTheLeague() throws IOException {                                                             //Method to print the points table
        readFromFile();
        if (footballClubRegistrationArrayList.size()==0){                                                                       //Check if there are teams registered already
            System.out.print("\n------------------------------No teams registered yet------------------------------\n");
        }else {
            footballClubRegistrationArrayList.sort(FootballClub::compareTo);                                                    //Sorting done accordingly. 1st by total points. if points are same,
                                                                                                            // then check the goals difference
            //-----Printing Football Club Statistics-----
            System.out.println("\n\n                                                               Premier League Points Table - Football Club\n");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println(String.format("|%-30s|%-14s|%-14s|%-15s|%-15s|%-15s|%-16s|%-20s|%-15s|","          Club Name           ","   Club ID    ","  No. of wins "," No. of losses "," No. of draws  "," Goals scored  "," Goals received ","Total played matches"," Total points  "));
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

            for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {
                if (!((footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)||(footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))){
                    System.out.println(String.format("|  %-28s|  %-12s|  %-12s|  %-13s|  %-13s|  %-13s|  %-14s|  %-18s|  %-13s|\n%s",
                            footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub(), footballClubRegistrationArrayList.get(exloop).getFootballClubId(), footballClubRegistrationArrayList.get(exloop).getNumberOfWins(),
                            footballClubRegistrationArrayList.get(exloop).getNumberOfLoss(), footballClubRegistrationArrayList.get(exloop).getNumberOfMatchDraw(), footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsScored(),
                            footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsReceived(), footballClubRegistrationArrayList.get(exloop).getNumberOfMatchesPlayed(), footballClubRegistrationArrayList.get(exloop).getCurrentNumberOfPoints(),
                            "+------------------------------+--------------+--------------+---------------+---------------+---------------+----------------+--------------------+---------------+"));
                }
            }

            //-----Printing School Football Club Statistics-----
            System.out.println("\n\n                                                         Premier League Points Table - School Football Club\n");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println(String.format("|%-30s|%-14s|%-14s|%-15s|%-15s|%-15s|%-16s|%-20s|%-15s|","          Club Name           ","   Club ID    ","  No. of wins "," No. of losses "," No. of draws  "," Goals scored  "," Goals received ","Total played matches"," Total points  "));
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

            for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {
                if (footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub){
                    schoolFootballClub = (SchoolFootballClub) footballClubRegistrationArrayList.get(exloop);
                    System.out.println(String.format("|  %-28s|  %-12s|  %-12s|  %-13s|  %-13s|  %-13s|  %-14s|  %-18s|  %-13s|\n%s",
                            footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub(), footballClubRegistrationArrayList.get(exloop).getFootballClubId(), footballClubRegistrationArrayList.get(exloop).getNumberOfWins(),
                            footballClubRegistrationArrayList.get(exloop).getNumberOfLoss(), footballClubRegistrationArrayList.get(exloop).getNumberOfMatchDraw(), footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsScored(),
                            footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsReceived(), footballClubRegistrationArrayList.get(exloop).getNumberOfMatchesPlayed(), footballClubRegistrationArrayList.get(exloop).getCurrentNumberOfPoints(),
                            "+------------------------------+--------------+--------------+---------------+---------------+---------------+----------------+--------------------+---------------+"));
                }
            }

            //-----Printing University Football Club Statistics-----
            System.out.println("\n\n                                                          Premier League Points Table - University Football Club\n");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println(String.format("|%-30s|%-14s|%-14s|%-15s|%-15s|%-15s|%-16s|%-20s|%-15s|","          Club Name           ","   Club ID    ","  No. of wins "," No. of losses "," No. of draws  "," Goals scored  "," Goals received ","Total played matches"," Total points  "));
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

            for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {
                if (footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub){
                    universityFootballClub = (UniversityFootballClub) footballClubRegistrationArrayList.get(exloop);
                    System.out.println(String.format("|  %-28s|  %-12s|  %-12s|  %-13s|  %-13s|  %-13s|  %-14s|  %-18s|  %-13s|\n%s",
                            footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub(), footballClubRegistrationArrayList.get(exloop).getFootballClubId(), footballClubRegistrationArrayList.get(exloop).getNumberOfWins(),
                            footballClubRegistrationArrayList.get(exloop).getNumberOfLoss(), footballClubRegistrationArrayList.get(exloop).getNumberOfMatchDraw(), footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsScored(),
                            footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsReceived(), footballClubRegistrationArrayList.get(exloop).getNumberOfMatchesPlayed(), footballClubRegistrationArrayList.get(exloop).getCurrentNumberOfPoints(),
                            "+------------------------------+--------------+--------------+---------------+---------------+---------------+----------------+--------------------+---------------+"));
                }
            }
        }
    }
//========================================================================================================================================================================>
    @Override
    public void printStatisticsOfClub(String searchClub) throws IOException {                                                  //Method to print stats of a selected club
        readFromFile();
        if (footballClubRegistrationArrayList.size()==0){                                                                       //Check if there are any clubs registered already in the League
            System.out.print("\n------------------------------No teams registered yet------------------------------\n");
        }else {
            try {
                for (int exloop = 0; exloop <= footballClubRegistrationArrayList.size(); exloop++) {                            //Check if club mentioned exists in the League
                    if ((footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub().equals(searchClub)) || (String.valueOf(footballClubRegistrationArrayList.get(exloop).getFootballClubId()).equals(searchClub))){
                        System.out.println(String.format(" * Name of Club  %18s\n * Club ID  %23s\n * Number of wins %16s\n * Number of losses %14s\n * Number of draw match %10s\n " +
                                        "* Number goals scored  %11s\n * Number of goals received  %6s\n * Total matches played %11s\n * Total points %19s",
                                "- '"+ footballClubRegistrationArrayList.get(exloop).getNameOfSportsClub()+"'", "- '"+ footballClubRegistrationArrayList.get(exloop).getFootballClubId()+"'", "-  "+ footballClubRegistrationArrayList.get(exloop).getNumberOfWins(),
                                "-  "+ footballClubRegistrationArrayList.get(exloop).getNumberOfLoss(), "-  "+ footballClubRegistrationArrayList.get(exloop).getNumberOfMatchDraw(), "-  "+ footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsScored(),
                                "-  "+ footballClubRegistrationArrayList.get(exloop).getNumberOfGoalsReceived(), "-  "+ footballClubRegistrationArrayList.get(exloop).getNumberOfMatchesPlayed(), "-  "+ footballClubRegistrationArrayList.get(exloop).getCurrentNumberOfPoints()));
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("\n---------------Club with mentioned detail is not available in league---------------");
            }
        }
    }
//========================================================================================================================================================================>
    @Override
    public void deleteClubFromLeague(String searchDelete) {                                                 //Method to delete a club from the League
        if (footballClubRegistrationArrayList.size()==0){                                                                       //Check if there are any clubs registered already in the League
            System.out.print("\n------------------------------No teams registered yet------------------------------\n");
        }else {
            try {
                if (!(searchDelete).equals("-")) {
                    for (int exloop = 0; exloop <= footballClubRegistrationArrayList.size(); exloop++){
                        if (footballClubRegistrationArrayList.get(exloop).getFootballClubId() == Integer.parseInt(searchDelete)) {      //Check if club mentioned is in the league
                            System.out.println("\n---------------Successfully removed team '" + footballClubRegistrationArrayList.get(exloop) + "' from the league---------------\n");
                            footballClubRegistrationArrayList.remove(exloop);
                            break;
                        }
                    }
                }if ((searchDelete).equals("-")) {                                                                  //To exit without deleting a club
                    System.out.println("\n------------------------------No team was removed from the league------------------------------\n");
                }
            } catch (Exception e) {
                System.out.println("\n------------------------------Team entered is not available in league------------------------------\n");
            }
        }
    }
//========================================================================================================================================================================>
    @Override
    public void addNewClubToLeague(FootballClub footballClub) {                                                     //Method to add a club to the League
        System.out.println("\n\n ***** Successfully registered '" + footballClub.getNameOfSportsClub() + "' to the Premiere League *****");
        footballClubRegistrationArrayList.add(footballClub);
    }

//========================================================================================================================================================================>
public static FootballClub getRandomClub(List<FootballClub> clubArrayList){                                         //Method to generate a random club
    Random randomSelector = new Random();
    return clubArrayList.get(randomSelector.nextInt(clubArrayList.size()));
}

    @Override
    public void openGUIJavaFX(Stage mainStage) {                                                                    //Method to show the GUI using JavaFX
        mainStage = new Stage();                                                                                    //main stage created
        // ========================================================================================================================================================================>
        Label mainHeading = new Label("Premier League Manager");                                                //main heading label
        mainHeading.setLayoutX(470);
        mainHeading.setLayoutY(25);
        mainHeading.setStyle("-fx-font-size: 50 ; -fx-text-fill: white ; -fx-font-family: 'Bookman Old Style' ; -fx-font-weight: bold");

        Label footballClubHeading = new Label("Football Club");                                                 //sub heading label
        footballClubHeading.setLayoutX(650);
        footballClubHeading.setLayoutY(100);
        footballClubHeading.setStyle("-fx-font-size: 40 ; -fx-text-fill: white ; -fx-font-family: 'Bookman Old Style' ; -fx-font-weight: bold");
        //========================================================================================================================================================================>

        Button winsSortButton = new Button("Sort By Wins");                                                     //Button created to sort by wins
        winsSortButton.setLayoutX(150);
        winsSortButton.setLayoutY(200);
        winsSortButton.setMinSize(200,35);
        winsSortButton.setStyle("-fx-background-color: #e90052; -fx-text-fill: white ; -fx-font-family: 'Bookman Old Style' ; -fx-font-size: 18;-fx-background-radius: 50px; -fx-padding:10px 65px;");

        Button goalsScoredSortButton = new Button("Sort By Goals Scored");                                      //Button created to sort by Goals Scored
        goalsScoredSortButton.setLayoutX(150);
        goalsScoredSortButton.setLayoutY(270);
        goalsScoredSortButton.setMinSize(200,35);
        goalsScoredSortButton.setStyle("-fx-background-color: #e90052; -fx-text-fill: white; -fx-font-family: 'Bookman Old Style' ; -fx-font-size: 18;-fx-background-radius: 50px; -fx-padding:10px 30px;");

        Button randomMatchButton = new Button("Generate Match");                                                //Button created to Generate a new random match
        randomMatchButton.setLayoutX(1220);
        randomMatchButton.setLayoutY(250);
        randomMatchButton.setMinSize(200,35);
        randomMatchButton.setStyle("-fx-background-color: rgb(0, 146, 73); -fx-text-fill: white; -fx-font-family: 'Bookman Old Style' ; -fx-font-size: 18; -fx-background-radius: 50px; -fx-padding:10px 55px;");

        Button playedMatchButton = new Button("Show Played Matches");                                           //Button created to display previously played matches
        playedMatchButton.setLayoutX(1220);
        playedMatchButton.setLayoutY(180);
        playedMatchButton.setMinSize(200,35);
        playedMatchButton.setStyle("-fx-background-color: #04f5ff; -fx-text-fill: black; -fx-font-family: 'Bookman Old Style' ; -fx-font-size: 18; -fx-background-radius: 50px; -fx-padding:10px 30px");

        TextField searchByDateTField = new TextField();                                                             //Text field to search matches played on the particular date
        searchByDateTField.setLayoutX(660);
        searchByDateTField.setLayoutY(280);
        searchByDateTField.setMinSize(250,40);
        searchByDateTField.setPromptText("Enter date (YYYY-MM-DD)");
        searchByDateTField.setStyle("-fx-alignment: center;");

        Button okSearchButton = new Button("Search");                                                           //Button created to search matches
        okSearchButton.setLayoutX(710);
        okSearchButton.setLayoutY(330);
        okSearchButton.setMinSize(150,35);
        okSearchButton.setStyle("-fx-background-color: #008896;-fx-text-fill: white; -fx-font-family: 'Bookman Old Style' ; -fx-font-size: 18;-fx-background-radius: 50px;-fx-padding: 10px 20px");

        Label showMatchLabel = new Label();                                                                         //Label to display pat matches
        showMatchLabel.setLayoutX(80);
        showMatchLabel.setLayoutY(100);
        showMatchLabel.setStyle("-fx-font-size: 18 ; -fx-font-family: 'Bookman Old Style'; -fx-padding: 0 0 0 100px");

        Label matchPlayedOnDateLabel = new Label();                                                                 //Label to display matches according to date
        matchPlayedOnDateLabel.setLayoutX(50);
        matchPlayedOnDateLabel.setLayoutY(100);
        matchPlayedOnDateLabel.setStyle("-fx-font-size: 18 ; -fx-font-family: 'Bookman Old Style'");

        Label showMatchHeading = new Label();                                                                       //Label to display heading in past matches
        showMatchHeading.setLayoutX(210);
        showMatchHeading.setLayoutY(20);
        showMatchHeading.setStyle("-fx-font-size: 22 ; -fx-font-family: 'Bookman Old Style' ; -fx-font-weight: bold");

        Image bgImage = new Image("file:../../ui/src/assets/images/bg2.jpg");                                    //Background image
        ImageView backgroundImagesJAVAFX = new ImageView(bgImage);
        backgroundImagesJAVAFX.setStyle("-fx-opacity: 0.7");
        backgroundImagesJAVAFX.setFitHeight(850);
        backgroundImagesJAVAFX.setFitWidth(1550);
        //========================================================================================================================================================================>
        TableView premierLeagueManagerTable = new TableView();                                                      //Table view to display clubs statistics
        premierLeagueManagerTable.setLayoutX(60);

        TableColumn<String, FootballClub> columnNameOfClub = new TableColumn<>("Club Name");                    //Column to display club name
        columnNameOfClub.setCellValueFactory(new PropertyValueFactory<>("nameOfSportsClub"));
        columnNameOfClub.setMinWidth(200);

        TableColumn<String, FootballClub> columnClubID = new TableColumn<>("Club ID");                          //Column to display club ID
        columnClubID.setCellValueFactory(new PropertyValueFactory<>("footballClubId"));
        columnClubID.setMinWidth(150);

        TableColumn<String, FootballClub> columnNumberOfWins = new TableColumn<>("No. Of Wins");                //Column to display number of wins
        columnNumberOfWins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
        columnNumberOfWins.setMinWidth(150);

        TableColumn<String, FootballClub> columnNumberOfLosses = new TableColumn<>("No. Of Losses");            //Column to display number of losses
        columnNumberOfLosses.setCellValueFactory(new PropertyValueFactory<>("numberOfLoss"));
        columnNumberOfLosses.setMinWidth(150);

        TableColumn<String, FootballClub> columnNumberOfDraws = new TableColumn<>("No. Of Draws");              //Column to display number of draws
        columnNumberOfDraws.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchDraw"));
        columnNumberOfDraws.setMinWidth(150);

        TableColumn<String, FootballClub> columnGoalsScored = new TableColumn<>("Goals Scored");                //Column to display goals scored
        columnGoalsScored.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
        columnGoalsScored.setMinWidth(150);

        TableColumn<String, FootballClub> columnGoalsReceived = new TableColumn<>("Goals Received");            //Column to display goals received
        columnGoalsReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));
        columnGoalsReceived.setMinWidth(150);

        TableColumn<String, FootballClub> columnTotalPlayedMatches = new TableColumn<>("Matches Played");       //Column to display number of matches played
        columnTotalPlayedMatches.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
        columnTotalPlayedMatches.setMinWidth(180);

        TableColumn<String, FootballClub> columnTotalPoints = new TableColumn<>("Points");                      //Column to display total points
        columnTotalPoints.setCellValueFactory(new PropertyValueFactory<>("currentNumberOfPoints"));
        columnTotalPoints.setMinWidth(150);
        //---Adding columns to the table
        premierLeagueManagerTable.getColumns().addAll(columnNameOfClub, columnClubID, columnNumberOfWins, columnNumberOfLosses, columnNumberOfDraws, columnGoalsScored, columnGoalsReceived, columnTotalPlayedMatches, columnTotalPoints);   //adding the columns to the table
        //---Adding the data to the columns
        footballClubRegistrationArrayList.sort(FootballClub::compareTo);                                                    //Sorting done accordingly. 1st by total points. if points are same,
                                                                                                        // then check the goals difference
        for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {
            if (!((footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)||(footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                FootballClub premierLeagueManagerTableData = footballClubRegistrationArrayList.get(exloop);
                premierLeagueManagerTable.getItems().addAll(premierLeagueManagerTableData);
            }
        }
        //========================================================================================================================================================================>

        winsSortButton.setOnAction(new EventHandler<ActionEvent>() {                                                //method to sort the table by number of wins
            @Override
            public void handle(ActionEvent event) {
                footballClubRegistrationArrayList.sort(new ComparatorSortNumOfWin());
                for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {
                    if (!((footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)||(footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                        FootballClub premierLeagueManagerTableData = footballClubRegistrationArrayList.get(exloop);                     //writing the data to the table
                        premierLeagueManagerTable.getItems().removeAll(premierLeagueManagerTableData);
                        premierLeagueManagerTable.getItems().addAll(premierLeagueManagerTableData);
                    }
                }
            }
        });
        //========================================================================================================================================================================>

        goalsScoredSortButton.setOnAction(new EventHandler<ActionEvent>() {                                         //method to sort the table by number of goals scored
            @Override
            public void handle(ActionEvent event) {
                footballClubRegistrationArrayList.sort(new ComparatorSortGoalsScored());
                for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {
                    if (!((footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)||(footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                        FootballClub premierLeagueManagerTableData = footballClubRegistrationArrayList.get(exloop);                     //writing the data to the table
                        premierLeagueManagerTable.getItems().removeAll(premierLeagueManagerTableData);
                        premierLeagueManagerTable.getItems().addAll(premierLeagueManagerTableData);
                    }
                }
            }
        });

        //========================================================================================================================================================================>
        //--------GENERATES MATCHES ONLY FROM FOOTBALL CLUB CATEGORY
        randomMatchButton.setOnAction(new EventHandler<ActionEvent>() {                                             //method to generate a random match
            @Override
            public void handle(ActionEvent event) {
                Stage showMatchStage = new Stage();
                Pane showMatchMainPane = new Pane();
                String victoryTeam;
                Random randomSelector = new Random();

                showMatchLabel.setText("");
                showMatchHeading.setText("This match was added");

                showMatchMainPane.getChildren().addAll(showMatchLabel,showMatchHeading);
                showMatchStage.setTitle("Football Club - Add a Match");
                showMatchStage.setScene(new Scene(showMatchMainPane,650,300));
                //---initializing variables
                int maxDayPossible = 29;
                int maxMonthPossible = 13;
                int maxYearPossible = 2021;
                int minYearPossible = 2000;
                int minPossible = 1;

                FootballClub team1;
                FootballClub team2;
                String footballClub1 = null;
                String footballClub2 = null;

                //---generating random integers to generate a random date
                int inputDay = randomSelector.nextInt(maxDayPossible-minPossible) + minPossible;
                int inputMonth = randomSelector.nextInt(maxMonthPossible-minPossible) + minPossible;
                int inputYear = randomSelector.nextInt(maxYearPossible-minYearPossible) + minYearPossible;

                String inputDate = inputYear+"-"+inputMonth+"-"+inputDay;                                           //setting up the date

                int maxGoalsPossible = 11;
                int scoreTeamOne = randomSelector.nextInt(maxGoalsPossible);                                        //generating number of goals scored for team 1
                int scoreTeamTwo = randomSelector.nextInt(maxGoalsPossible);                                        //generating number of goals scored for team 2
                String scoreTeam1 = String.valueOf(scoreTeamOne);                                                   //getting string value of the goals scored by team 1
                String scoreTeam2 = String.valueOf(scoreTeamTwo);                                                   //getting string value of the goals scored by team 2

                for (int exloop = 0; exloop < footballClubRegistrationArrayList.size(); exloop++) {                                     //counter to check number of clubs in each category
                    if (!((footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) || (footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                        footballClubCounter++;
                    }else if (footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub) {
                        schoolFootballClub = (SchoolFootballClub) footballClubRegistrationArrayList.get(exloop);
                        schoolFootballClubCounter++;
                    }else if (footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub) {
                        universityFootballClub = (UniversityFootballClub) footballClubRegistrationArrayList.get(exloop);
                        universityFootballClubCounter++;
                    }
                }

                if (footballClubCounter < 2) {                                                                      //check if there are enough teams to start a match
                    showMatchLabel.setText("NOT ENOUGH TEAMS TO GENERATE A MATCH");
                    showMatchStage.show();
                }else {
                    while (true) {
                        team1 = getRandomClub(footballClubRegistrationArrayList);                                                       //generating team 1 randomly
                        team2 = getRandomClub(footballClubRegistrationArrayList);                                                       //generating team 2 randomly

                        if (team1 != team2) {                                                                       //check if team 1 and team 2 are not the same clubs
                            if ((!((team1 instanceof SchoolFootballClub) || team1 instanceof UniversityFootballClub)) && (!((team2 instanceof SchoolFootballClub) || team2 instanceof UniversityFootballClub))) {
                                footballClub1 = (String.valueOf(team1));
                                footballClub2 = (String.valueOf(team2));
                                if (scoreTeamOne > scoreTeamTwo) {                                                  //analyzing scores of each clubs
                                    victoryTeam = footballClub1;
                                } else {
                                    victoryTeam = footballClub2;
                                }
                                showMatchLabel.setText(" \t\t "+inputDate + "\n\n" +"\t\t"+ footballClub1 + "\tVS\t" + footballClub2 + "\n" +"\t\t"+ scoreTeam1 + "\t:\t" + scoreTeam2 + "\n\n" + "\tVictory Team\t=\t" + victoryTeam);
                                updatePlayedMatch(inputDate, team1, team2, scoreTeam1, scoreTeam2, victoryTeam);    //passing the values to update match method to assign scores and points accordingly
                                premierLeagueManagerTable.refresh();                                                //refresh table to updte table in GUI
                                showMatchStage.show();
                                break;
                            }
                        }
                    }
                }
                try {
                    writeToFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //========================================================================================================================================================================>

        playedMatchButton.setOnAction(new EventHandler<ActionEvent>() {                                             //method to show played past match details
            @Override
            public void handle(ActionEvent event) {
                Stage showMatchStage = new Stage();
                Pane showMatchMainPane = new Pane();
                showMatchLabel.setText("");
                showMatchHeading.setText("\t\tFootball Club Matches");
                ScrollPane showMatchScroll = new ScrollPane();                                    //ScrollPane because there are many information in it
                showMatchScroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);  //disable horizontal scroll bar

                playedMatchUpdaterArrayList.sort(new ComparatorSortByDate().reversed());
                for (int exloop = 0; exloop < playedMatchUpdaterArrayList.size() ; exloop++){
                    showMatchLabel.setText(playedMatchUpdaterArrayList.get(exloop) + "\n" +showMatchLabel.getText());
                }

                showMatchMainPane.getChildren().addAll(showMatchLabel,showMatchHeading);
                showMatchScroll.setContent(showMatchMainPane);
                showMatchStage.setTitle("Football Club Played matches");
                showMatchStage.setScene(new Scene(showMatchScroll,850,600));
                showMatchStage.show();
            }
        });

        //========================================================================================================================================================================>

        okSearchButton.setOnAction(new EventHandler<ActionEvent>() {                                                //method to show matches according to the date mentioned
            @Override
            public void handle(ActionEvent event) {
                String getDate = searchByDateTField.getText();
                matchPlayedOnDateLabel.setText(" ");
                if (getDate.equals("")){
                    Alert alerttxt = new Alert(Alert.AlertType.ERROR);
                    alerttxt.setTitle("ERROR!!!");
                    alerttxt.setHeaderText(null);
                    alerttxt.setContentText("Please enter valid Inputs!");
                    alerttxt.showAndWait();
                }
                else{
                    Stage matchPlayedOnDateStage = new Stage();
                    Pane matchPlayedOnDatePane = new Pane();
                    ScrollPane showMatchScroll = new ScrollPane();                                    //ScrollPane because there are many information in it
                    showMatchScroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);  //disable horizontal scroll bar
                    showMatchHeading.setText("\tMatches Played on " + (searchByDateTField.getText()));
                    for (int exloop = 0; exloop < playedMatchUpdaterArrayList.size() ; exloop++){
                        if (playedMatchUpdaterArrayList.get(exloop).getDateOfMatchPlayed().equals(getDate)){
                            matchPlayedOnDateLabel.setText(playedMatchUpdaterArrayList.get(exloop) + "\n" +matchPlayedOnDateLabel.getText());
                        }
                    }

                    matchPlayedOnDatePane.getChildren().addAll(matchPlayedOnDateLabel,showMatchHeading);
                    showMatchScroll.setContent(matchPlayedOnDatePane);
                    matchPlayedOnDateStage.setTitle("Football Club Played matches");
                    matchPlayedOnDateStage.setMaxWidth(1020);
                    matchPlayedOnDateStage.setScene(new Scene(showMatchScroll,850,600));
                    matchPlayedOnDateStage.show();
                }
            }
        });

        //========================================================================================================================================================================>

        Pane mainPaneJAVAFX = new Pane();
        mainPaneJAVAFX.getChildren().addAll(premierLeagueManagerTable);

        Pane upperPaneJAVAFX = new Pane();        //upper pane to display labels, text fields, buttons
        upperPaneJAVAFX.getChildren().addAll(mainHeading,footballClubHeading,winsSortButton,goalsScoredSortButton,randomMatchButton,playedMatchButton,searchByDateTField,okSearchButton);
        upperPaneJAVAFX.setMinHeight(400);

        Pane lowerPaneJAVAFX = new Pane();        //lower pane contains the scroll pane
        lowerPaneJAVAFX.getChildren().addAll(mainPaneJAVAFX);
        lowerPaneJAVAFX.setMinHeight(500);

        VBox premierLeagueManagerVBoxJAVAFX = new VBox(upperPaneJAVAFX,lowerPaneJAVAFX);  //VBox to contain both the panes
        Pane bgPaneJAVAFX = new Pane(backgroundImagesJAVAFX,premierLeagueManagerVBoxJAVAFX);
        Scene premierLeagueManagerSceneJAVAFX = new Scene(bgPaneJAVAFX,1550,850);//string the scene with VBox

        mainStage.setScene(premierLeagueManagerSceneJAVAFX);  //setting the stage with Scene
        mainStage.setTitle("Premiere League Manager");
        premierLeagueManagerVBoxJAVAFX.setId("mainPane");
        premierLeagueManagerSceneJAVAFX.getStylesheets().add(PremierLeagueManager.class.getResource("FootballClubJavaFXStyleSheet.css").toExternalForm());
        mainStage.showAndWait();
    }
}
