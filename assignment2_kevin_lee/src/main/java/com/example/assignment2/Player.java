package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kevin on 11/4/2015.
 *
 * Player Model
 */
public class Player implements Parcelable {

    // enum makes more sense than constants for this.
    // Plus they are cool and don't teach how to make them in any programing classes.
    public enum Position {
        GOALIE{
            @Override
            public String getFriendlyName() {
                return "Goalie";
            }
        },
        FORWARD{
            @Override
            public String getFriendlyName() {
                return "Forward";
            }
        },
        DEFENSE{
            @Override
            public String getFriendlyName() {
                return "Defense";
            }
        };
        public abstract String getFriendlyName();
    }

    private String playerName;
    private Position position;
    private int numGoals;

    public Player(){}

    public Player(String playerName, Position position, int numGoals) {
        this.playerName = playerName;
        this.position = position;
        this.numGoals = numGoals;
    }

    public Player(Parcel parcel){
        this.playerName = parcel.readString();
        this.position = Position.valueOf(parcel.readString());
        this.numGoals = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.playerName);
        dest.writeString(position.name());
        dest.writeInt(numGoals);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>(){

        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getNumGoals() {
        return numGoals;
    }

    public void setNumGoals(int numGoals) {
        this.numGoals = numGoals;
    }
}
