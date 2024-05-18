package com.example.hexgame;

import javafx.scene.paint.Color;

import java.util.List;

public class Player {
    String name;
    Color color;
    List visitedList;

    public Player(String name,Color color,List visited){
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

    List getVisitedListofPlayer(){
        return this.visitedList;
    }

}
