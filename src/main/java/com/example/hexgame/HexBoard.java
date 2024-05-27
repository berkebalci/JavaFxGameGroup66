package com.example.hexgame;


public class HexBoard {
    private final int size;
    private final HexCell[][] cells;

    public HexBoard(int size) {
        this.size = size;
        cells = new HexCell[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = new HexCell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public HexCell getCell(int row, int col) {
        return cells[row][col];
    }
}