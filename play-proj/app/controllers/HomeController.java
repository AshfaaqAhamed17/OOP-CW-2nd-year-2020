package controllers;

import model.*;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeController extends Controller {

    static File footballClubBinaryFile;
    static File matchUpdateBinaryFile;

    PremierLeagueManager premierLeagueManager = new PremierLeagueManager();

    public Result displayDataTable() throws IOException {

        try{
            matchUpdateBinaryFile = new File("MatchUpdateBinaryFile.json");
            matchUpdateBinaryFile.createNewFile();

        }catch (IOException e) {
            System.out.print("Creating file");
        }finally {
            try{
                FileInputStream matchUpdateFileInputStream = new FileInputStream(matchUpdateBinaryFile);
                ObjectInputStream matchUpdateObjectInputStream = new ObjectInputStream(matchUpdateFileInputStream);
                PremierLeagueManager.playedMatchUpdaterArrayList = (ArrayList<MatchUpdater>) matchUpdateObjectInputStream.readObject();

                PremierLeagueManager.playedMatchUpdaterArrayList.sort(new ComparatorSortByDate());

                matchUpdateFileInputStream.close();
                matchUpdateObjectInputStream.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (EOFException e){
                System.out.print("System is successfully updated");
            }
        }

        JsonNode jsonNodeConvert = Json.toJson(PremierLeagueManager.playedMatchUpdaterArrayList);
        return ok(jsonNodeConvert).as("application/json");
    }

//=====================================================================================================================>

    public Result displayClubsDataTable() throws IOException {
//        premierLeagueManager.readFromFile();
        ArrayList<FootballClub> clubArrayList2 = new ArrayList<>();
        try{
            footballClubBinaryFile = new File("FootballClubRegistrationBinaryFile.json");
            footballClubBinaryFile.createNewFile();

        }catch (IOException e) {
            System.out.print("Creating file");
        }finally {
            try{
                FileInputStream footballFileInputStream = new FileInputStream(footballClubBinaryFile);
                ObjectInputStream footballObjectInputStream = new ObjectInputStream(footballFileInputStream);
                PremierLeagueManager.footballClubRegistrationArrayList = (ArrayList<FootballClub>) footballObjectInputStream.readObject();

                PremierLeagueManager.footballClubRegistrationArrayList.sort(FootballClub::compareTo);                                                    //Sorting done accordingly. 1st by total points. if points are same,
                                                                                                                                     // then check the goals difference

                footballFileInputStream.close();
                footballObjectInputStream.close();

            } catch (ClassNotFoundException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (EOFException e){
                System.out.print("System is successfully updated");
            }

            for (int exloop = 0; exloop < PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
                if (!((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)||(PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                    clubArrayList2.add(PremierLeagueManager.footballClubRegistrationArrayList.get(exloop));
                }
            }
        }
        JsonNode jsonNodeConvert = Json.toJson(clubArrayList2);
        return ok(jsonNodeConvert).as("application/json");
    }

    //=====================================================================================================================>

    public static FootballClub getRandomClub(List<FootballClub> clubArrayList){
        Random randomSelector = new Random();
        return clubArrayList.get(randomSelector.nextInt(clubArrayList.size()));
    }

    //=====================================================================================================================>

    public Result generateRandomMatch() throws IOException {
        ArrayList<FootballClub> clubArrayList2 = new ArrayList<>();

        premierLeagueManager.readFromFile();

        String victoryTeam;
        Random randomSelector = new Random();

        int maxDayPossible = 29;
        int maxMonthPossible = 13;
        int maxYearPossible = 2021;
        int minYearPossible = 2000;
        int minPossible = 1;
        FootballClub team1;
        FootballClub team2;
        String footballClub1;
        String footballClub2;

        int inputDay = randomSelector.nextInt(maxDayPossible-minPossible) + minPossible;
        int inputMonth = randomSelector.nextInt(maxMonthPossible-minPossible) + minPossible;
        int inputYear = randomSelector.nextInt(maxYearPossible-minYearPossible) + minYearPossible;

        String inputDate = inputYear+"-"+inputMonth+"-"+inputDay;

        int maxGoalsPossible = 11;
        int scoreTeamOne = randomSelector.nextInt(maxGoalsPossible);
        int scoreTeamTwo = randomSelector.nextInt(maxGoalsPossible);
        String scoreTeam1 = String.valueOf(scoreTeamOne);
        String scoreTeam2 = String.valueOf(scoreTeamTwo);

        for (int exloop = 0; exloop < PremierLeagueManager.footballClubRegistrationArrayList.size(); exloop++) {
            if (!((PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof SchoolFootballClub)||(PremierLeagueManager.footballClubRegistrationArrayList.get(exloop) instanceof UniversityFootballClub))) {
                clubArrayList2.add(PremierLeagueManager.footballClubRegistrationArrayList.get(exloop));
            }
        }

        while (true) {

            team1 = getRandomClub(clubArrayList2);
            team2 = getRandomClub(clubArrayList2);

            if (team1 != team2) {
                if ((!((team1 instanceof SchoolFootballClub) || team1 instanceof UniversityFootballClub)) && (!((team2 instanceof SchoolFootballClub) || team2 instanceof UniversityFootballClub))) {
                    footballClub1 = (String.valueOf(team1));
                    footballClub2 = (String.valueOf(team2));
                    if (scoreTeamOne > scoreTeamTwo) {
                        victoryTeam = footballClub1;
                    } else {
                        victoryTeam = footballClub2;
                    }

                    premierLeagueManager.updatePlayedMatch(inputDate, team1, team2, scoreTeam1, scoreTeam2, victoryTeam);
                    premierLeagueManager.writeToFile();

                    break;
                }
            }
        }

        JsonNode jsonNodeConvert = Json.toJson(PremierLeagueManager.playedMatchUpdaterArrayList.get(PremierLeagueManager.playedMatchUpdaterArrayList.size()-1));
        return ok(jsonNodeConvert).as("application/json");
    }
}
