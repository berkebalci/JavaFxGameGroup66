package com.example.hexgame;

import javafx.scene.paint.Color;

public class Player {
    String name;
    Color color;

    public Player(String name,Color color){
        this.name = name;
        this.color = color;
    }
    String getPlayerName(){
        return this.name;
    }
    Color getColor(){
        return this.color;
    }

}
