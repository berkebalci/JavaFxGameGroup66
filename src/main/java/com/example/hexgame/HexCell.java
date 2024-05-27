package com.example.hexgame;

public class HexCell {
    private Player player;

    public HexCell() {
        this.player = null;
    }

    public boolean isEmpty() {
        return player == null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}