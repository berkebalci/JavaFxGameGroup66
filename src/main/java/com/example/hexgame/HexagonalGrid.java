package com.example.hexgame;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class HexagonalGrid extends Application {

    final int WIDTH = 1100;
    final int HEIGHT = 800;
    final int SIDE_LENGTH = 18;
    private GameModel gameModel;
    private Pane gridPane;
    private Label statusLabel;

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);


        Button btn5x5 = new Button("5x5");
        Button btn11x11 = new Button("11x11");
        Button btn17x17 = new Button("17x17");
        Button btnStart = new Button("Start");


        statusLabel = new Label("Welcome to Hex Game");
        statusLabel.setPadding(new Insets(5, 5, 5, 5));


        btn5x5.setOnAction(e -> startNewGame(5));
        btn11x11.setOnAction(e -> startNewGame(11));
        btn17x17.setOnAction(e -> startNewGame(17));
        btnStart.setOnAction(e -> startSimulation());


        HBox buttonBox = new HBox(10, btn5x5, btn11x11, btn17x17, btnStart);
        buttonBox.setPadding(new Insets(5, 5, 5, 5));

        root.setBottom(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(0, 0, 20, 0));

        gridPane = new Pane();
        root.setCenter(gridPane);


        BorderPane.setMargin(statusLabel, new Insets(0, 10, 10, 0));
        BorderPane.setAlignment(statusLabel, javafx.geometry.Pos.BOTTOM_RIGHT);
        root.setBottom(new BorderPane(null, null, statusLabel, buttonBox, null));

        primaryStage.setTitle("JavaFX Hex Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startNewGame(int size) {
        Player player1 = new Player("Player 1", Color.BLUE);
        Player player2 = new Player("Player 2", Color.RED);
        gameModel = new GameModel(size, player1, player2);
        drawHexagonalGrid(size);
        updateStatusLabel();
    }

    private void drawHexagonalGrid(int rows) {
        gridPane.getChildren().clear();

        double sceneCenterX = WIDTH / 2;
        double sceneCenterY = HEIGHT / 2;

        double hexagonWidth = SIDE_LENGTH * Math.sqrt(3);
        double hexagonHeight = 2 * SIDE_LENGTH;
        double hexagonDistance = 0.2 * SIDE_LENGTH;

        double startX = sceneCenterX - (rows * hexagonWidth) / 2;
        double startY = sceneCenterY - (rows * hexagonHeight) / 2;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                double x = startX + (rows - col - 1) * hexagonWidth + (row % 2 == 1 ? hexagonWidth / 2 : 0);
                double y = startY + (rows - row - 1) * hexagonHeight * 3 / 4;

                if (row != 0) {
                    x -= (row % 2 == 1 ? hexagonWidth / 2 : 0) + hexagonDistance / 2;
                }

                Polygon hexagon = createHexagon(x, y, SIDE_LENGTH);
                hexagon.setFill(Color.WHITE);
                hexagon.setStroke(Color.BLACK);


                FillTransition fillTransition = new FillTransition(Duration.millis(1200), hexagon, Color.TRANSPARENT, Color.WHITE);
                fillTransition.play();


                int finalRow = row;
                int finalCol = col;
                hexagon.setOnMouseClicked(event -> handleCellClick(finalRow, finalCol, hexagon));

                gridPane.getChildren().add(hexagon);
            }

            startX -= hexagonWidth / 2;
        }
    }

    private Polygon createHexagon(double centerX, double centerY, double sideLength) {
        Polygon hexagon = new Polygon();

        double angleDeg;
        for (int i = 0; i < 6; i++) {
            angleDeg = 60 * i + 30;
            double x = centerX + sideLength * Math.cos(Math.toRadians(angleDeg));
            double y = centerY + sideLength * Math.sin(Math.toRadians(angleDeg));
            hexagon.getPoints().addAll(x, y);
        }

        return hexagon;
    }

    private void handleCellClick(int row, int col, Polygon hexagon) {
        if (gameModel.makeMove(row, col)) {
            Color playerColor = gameModel.getBoard().getCell(row, col).getPlayer().getColor();
            hexagon.setFill(playerColor);

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), hexagon);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);


            fadeTransition.play();

            if (gameModel.isGameOver()) {
                Player winner = gameModel.getWinner();
                if (winner != null) {
                    statusLabel.setText("Game Over: " + winner.getName() + " Wins! Turn: " + gameModel.getTurn());
                } else {
                    statusLabel.setText("Game Over: It's a draw. Turn: " + gameModel.getTurn());
                }
            } else {
                updateStatusLabel();
            }
        } else {
            statusLabel.setText("Cell already occupied! Choose another cell.");
        }
    }

    private void updateStatusLabel() {
        statusLabel.setText("Turn: " + gameModel.getTurn() + " - Current Player: " + gameModel.getCurrentPlayer().getName());
    }

    private void startSimulation() {
        System.out.println("Game started.");
        updateStatusLabel();
    }

}
