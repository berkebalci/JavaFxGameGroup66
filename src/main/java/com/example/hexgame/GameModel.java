package com.example.hexgame;


public class GameModel {
    private final HexBoard board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private int turn;

    public GameModel(int size, Player player1, Player player2) {
        this.board = new HexBoard(size);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.turn = 1; // Initialize turn
    }

    public HexBoard getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getTurn() {
        return turn;
    }

    public boolean makeMove(int row, int col) {
        if (!board.getCell(row, col).isEmpty()) {
            return false;
        }
        board.getCell(row, col).setPlayer(currentPlayer);
        if (checkWin(row, col)) {
            System.out.println(currentPlayer.getName() + " wins!");
        }
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        turn++;
        return true;
    }

    private boolean checkWin(int row, int col) {
        boolean[][] visited = new boolean[board.getSize()][board.getSize()];
        if (currentPlayer == player1) {

            for (int c = 0; c < board.getSize(); c++) {
                if (dfs(0, c, visited, player1)) {
                    return true;
                }
            }
        } else {

            for (int r = 0; r < board.getSize(); r++) {
                if (dfs(r, 0, visited, player2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, boolean[][] visited, Player player) {
        int[][] directions = {
                {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}
        };

        if (!isValidCell(row, col) || visited[row][col] || board.getCell(row, col).getPlayer() != player) {
            return false;
        }

        visited[row][col] = true;

        if (player == player1 && row == board.getSize() - 1) {
            return true;
        } else if (player == player2 && col == board.getSize() - 1) {
            return true;
        }

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (dfs(newRow, newCol, visited, player)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize();
    }

    public boolean isGameOver() {
        boolean[][] visited = new boolean[board.getSize()][board.getSize()];
        if (currentPlayer == player1) {

            for (int c = 0; c < board.getSize(); c++) {
                if (dfs(0, c, visited, player1)) {
                    return true;
                }
            }
        } else {

            for (int r = 0; r < board.getSize(); r++) {
                if (dfs(r, 0, visited, player2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Player getWinner() {
        boolean[][] visited = new boolean[board.getSize()][board.getSize()];
        for (int c = 0; c < board.getSize(); c++) {
            if (dfs(0, c, visited, player1)) {
                return player1;
            }
        }

        visited = new boolean[board.getSize()][board.getSize()];
        for (int r = 0; r < board.getSize(); r++) {
            if (dfs(r, 0, visited, player2)) {
                return player2;
            }
        }

        return null; //kazanan yok
    }
}