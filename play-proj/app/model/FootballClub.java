package model;

import java.awt.*;
import java.io.Serializable;

public class FootballClub extends SportsClub implements Serializable, Comparable<FootballClub>{
	
    private String footballClubId;
    private String nameOfCaptain;
    private Color teamColor;
    private int numberOfWins = 0;
    private int numberOfLoss = 0;
    private int numberOfMatchDraw = 0;
    private int numberOfGoalsScored = 0;
    private int numberOfGoalsReceived = 0;
    private int numberOfMatchesPlayed = 0;
    private int currentNumberOfPoints = 0;

    public FootballClub(String nameOfSportsClub, String locationOfSportsClub, Color teamColor, String nameOfManger, String footballClubId, String nameOfCaptain) {
        super(nameOfSportsClub, locationOfSportsClub, nameOfManger);
        this.footballClubId = footballClubId;
        this.nameOfCaptain = nameOfCaptain;
        this.teamColor = teamColor;
    }

    public FootballClub(String nameOfSportsClub, int numberOfWins, int numberOfLoss, int numberOfMatchDraw, int numberOfGoalsScored, int numberOfGoalsReceived, int numberOfMatchesPlayed, int currentNumberOfPoints) {
        super(nameOfSportsClub);
        this.numberOfWins = numberOfWins;
        this.numberOfLoss = numberOfLoss;
        this.numberOfMatchDraw = numberOfMatchDraw;
        this.numberOfGoalsScored = numberOfGoalsScored;
        this.numberOfGoalsReceived = numberOfGoalsReceived;
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
        this.currentNumberOfPoints = currentNumberOfPoints;
    }

    public FootballClub() {
    }

    public String getNameOfCaptain() {
        return nameOfCaptain;
    }

    public void setNameOfCaptain(String nameOfCaptain) {
        this.nameOfCaptain = nameOfCaptain;
    }

    public int getFootballClubId() {
        return Integer.parseInt(footballClubId);
    }

    public void setFootballClubId(String footballClubId) {
        this.footballClubId = footballClubId;
    }

    public String getTeamColor() {
        return (String.valueOf(teamColor));
    }

    public void setTeamColor(Color teamColor) {
        this.teamColor = teamColor;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = getNumberOfWins() + numberOfWins;
    }

    public int getNumberOfLoss() {
        return numberOfLoss;
    }

    public void setNumberOfLoss(int numberOfLoss) {
        this.numberOfLoss = getNumberOfLoss() + numberOfLoss;
    }

    public int getNumberOfMatchDraw() {
        return numberOfMatchDraw;
    }

    public void setNumberOfMatchDraw(int numberOfMatchDraw) {
        this.numberOfMatchDraw = getNumberOfMatchDraw() + numberOfMatchDraw;
    }

    public int getNumberOfGoalsScored() {
        return numberOfGoalsScored;
    }

    public void setNumberOfGoalsScored(int numberOfGoalsScored) {
        this.numberOfGoalsScored = getNumberOfGoalsScored() + numberOfGoalsScored;
    }

    public int getNumberOfGoalsReceived() {
        return numberOfGoalsReceived;
    }

    public void setNumberOfGoalsReceived(int numberOfGoalsReceived) {
        this.numberOfGoalsReceived = getNumberOfGoalsReceived() + numberOfGoalsReceived;
    }

    public int getNumberOfMatchesPlayed() {
        return numberOfMatchesPlayed;
    }

    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.numberOfMatchesPlayed = getNumberOfMatchesPlayed() + numberOfMatchesPlayed;
    }

    public int getCurrentNumberOfPoints() {
        return currentNumberOfPoints;
    }

    public void setCurrentNumberOfPoints(int currentNumberOfPoints) {
        this.currentNumberOfPoints = getCurrentNumberOfPoints() + currentNumberOfPoints;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public int compareTo(FootballClub footballClub) {
        //already available obj - created obj --> descending order
        if ((footballClub.getCurrentNumberOfPoints()) > this.getCurrentNumberOfPoints()){
            return 1;   //true
        }else if ((footballClub.getCurrentNumberOfPoints()) < this.getCurrentNumberOfPoints()){
            return -1;  //false
        }else {
            int checkGetGoalDifference = footballClub.getNumberOfGoalsScored() - footballClub.getNumberOfGoalsReceived();
            int checkGoalDifference = this.numberOfGoalsScored - this.numberOfGoalsReceived;

            if (checkGetGoalDifference>checkGoalDifference){
                return  1;
            }else if (checkGetGoalDifference<checkGoalDifference){
                return -1;
            }else return 0;
        }
    }
}
