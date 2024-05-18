package com.example.hexgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class MAIN extends Application {
    private static final int SIZE = 11; // 11x11 hex board
    private static final double HEX_SIZE = 20; // Size of each hexagon

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Hexagon hex = new Hexagon(row, col, HEX_SIZE);
                hex.setOnMouseClicked(event -> handleHexClick(hex));
                pane.getChildren().add(hex);
            }
        }

        Scene scene = new Scene(pane, 800, 800);
        primaryStage.setTitle("Hex Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleHexClick(Hexagon hex) {
        int row = hex.getRow();
        int col = hex.getCol();
        System.out.println("Hexagon clicked at row: " + row + ", col: " + col);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Hexagon extends javafx.scene.shape.Polygon {
    private final int row;
    private final int col;

    public Hexagon(int row, int col, double size) {
        this.row = row;
        this.col = col;

        double centerX = size * Math.cos(Math.PI / 6);
        double centerY = size * Math.sin(Math.PI / 6);

        getPoints().addAll(
                centerX, 0.0,
                centerX / 2, centerY,
                -centerX / 2, centerY,
                -centerX, 0.0,
                -centerX / 2, -centerY,
                centerX / 2, -centerY
        );

        setTranslateX(col * 1.5 * size);
        setTranslateY(row * 2 * centerY + (col % 2) * centerY);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
