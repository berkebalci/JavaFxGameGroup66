package com.example.hexgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class HexagonalGrid extends Application {

    final int WIDTH = 1100;
    final int HEIGHT = 800;
    final int SIDE_LENGTH = 18;
 //   final int rows = 5; // Default value


    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Create buttons to choose grid size
        Button btn5x5 = new Button("5x5");
        Button btn11x11 = new Button("11x11");
        Button btn17x17 = new Button("17x17");
        Button btnStart = new Button("Start");

        // Button actions
        btn5x5.setOnAction(e -> drawHexagonalGrid(root, 5));
        btn11x11.setOnAction(e -> drawHexagonalGrid(root, 11));
        btn17x17.setOnAction(e -> drawHexagonalGrid(root, 17));
        btnStart.setOnAction(e -> startSimulation());

        // Layout buttons
        HBox buttonBox = new HBox(10, btn5x5, btn11x11, btn17x17, btnStart);
        buttonBox.setPadding(new Insets(5, 5, 5, 5));

        root.setBottom(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(0, 0, 20, 0));

        primaryStage.setTitle(" JavaFX Hex Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawHexagonalGrid(BorderPane root, int rows) {
        Pane gridPane = new Pane();
        root.setCenter(gridPane);
        gridPane.getChildren().removeIf(node -> node instanceof Polygon);

        double sceneCenterX = root.getWidth() / 2;
        double sceneCenterY = root.getHeight() / 2;

        double hexagonWidth = SIDE_LENGTH * Math.sqrt(3); // Width of a hexagon (distance between parallel sides)
        double hexagonHeight = 2 * SIDE_LENGTH; // Height of a hexagon (distance between opposite vertices)
        double hexagonDistance = 0.2 * SIDE_LENGTH; // Distance between hexagons

        double startX = sceneCenterX - (rows * hexagonWidth) / 2;
        double startY = sceneCenterY - (rows * hexagonHeight) / 2;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                double x = startX + (rows - col - 1) * hexagonWidth + (row % 2 == 1 ? hexagonWidth / 2 : 0);
                double y = startY + (rows - row - 1) * hexagonHeight * 3 / 4;

                // Add distance between hexagons in the second row and after
                if (row != 0) {
                    x -= (row % 2 == 1 ? hexagonWidth / 2 : 0) + hexagonDistance / 2;
                }

                Polygon hexagon = createHexagon(x, y, SIDE_LENGTH);
                hexagon.setFill(Color.WHITE);
                hexagon.setStroke(Color.BLACK);
                gridPane.getChildren().add(hexagon);
            }

            // Adjust start position for next row to create a staggered appearance
            startX -= hexagonWidth / 2;
        }


    }


    private Polygon createHexagon(double centerX, double centerY, double sideLength) {
        Polygon hexagon = new Polygon();

        double angleDeg;
        for (int i = 0; i < 6; i++) {
            angleDeg = 60 * i + 30; // Rotate the hexagon by 30 degrees to get the second shape
            double x = centerX + sideLength * Math.cos(Math.toRadians(angleDeg));
            double y = centerY + sideLength * Math.sin(Math.toRadians(angleDeg));
            hexagon.getPoints().addAll(x, y);
        }

        return hexagon;
    }


    private void startSimulation() {

        System.out.println("Simulation started.");
    }
}
