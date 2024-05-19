package com.example.hexgame;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    Color color;
    ArrayList visitedList;

    public Player(String name,Color color,ArrayList visited){
        this.name = name;
        this.color = color;
        this.visitedList = visited;
    }
    String getPlayerName(){
        return this.name;
    }
    Color getColor(){
        return this.color;
    }

    ArrayList getVisitedListofPlayer(){
        return this.visitedList;
    }

    setVisitedList(){

    }

}
