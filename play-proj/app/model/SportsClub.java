package model;

import java.io.Serializable;

public abstract class SportsClub implements Serializable {
    private String nameOfSportsClub;
    private String locationOfSportsClub;
    private String nameOfManger;

    public SportsClub(String nameOfSportsClub, String locationOfSportsClub, String nameOfManger){
        this.nameOfSportsClub = nameOfSportsClub;
        this.locationOfSportsClub = locationOfSportsClub;
        this.nameOfManger = nameOfManger;
    }

    public SportsClub() {
    }

    public SportsClub(String nameOfSportsClub) {
        this.nameOfSportsClub = nameOfSportsClub;
    }

    public String getNameOfSportsClub() {
        return nameOfSportsClub;
    }

    public void setNameOfSportsClub(String nameOfSportsClub) {
        this.nameOfSportsClub = nameOfSportsClub;
    }

    public String getLocationOfSportsClub() {
        return locationOfSportsClub;
    }

    public void setLocationOfSportsClub(String locationOfSportsClub) {
        this.locationOfSportsClub = locationOfSportsClub;
    }

    public String getNameOfManger() {
        return nameOfManger;
    }

    public void setNameOfManger(String nameOfManger) {
        this.nameOfManger = nameOfManger;
    }

    @Override
    public String toString() {
        return nameOfSportsClub;
    }

}
