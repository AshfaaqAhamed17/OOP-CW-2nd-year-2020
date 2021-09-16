package controllers;

import model.FootballClub;
import javafx.stage.Stage;

import java.io.IOException;

public interface LeagueManager {
    void writeToFile() throws IOException;
    void readFromFile() throws IOException;
    void updatePlayedMatch(String datePlayed, FootballClub teamOne, FootballClub teamTwo, String scoreOfTeamOne, String scoreOfTeamTwo, String victoryTeam);
    void printPointsTableOfTheLeague() throws IOException;
    void printStatisticsOfClub(String searchClub) throws IOException;
    void deleteClubFromLeague(String searchDelete);
    void addNewClubToLeague(FootballClub footballClub);
    void openGUIJavaFX(Stage mainStage);

}
