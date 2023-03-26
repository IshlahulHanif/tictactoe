package com.shlh.tictactoe.model;

public class BoardModel {
    private final char[][] board;
    private final int size;
    public static final char X = 'X'; // TODO: use enum class?
    public static final char O = 'O';
    public static final char EMPTY = '-';

    public BoardModel(int size) {
        this.board = new char[size][size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = EMPTY;
            }
        }
    }

    public boolean play(int row, int col, boolean isX) {
        if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != '-') {
            return false;
        }
        board[row][col] = isX ? X : O;
        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public char checkWinner() {
        boolean isWin;
        // Check rows
        for (int i = 0; i < size; i++) {
            char first = board[i][0];
            if (first == EMPTY) continue;
            isWin = true;
            for (int j = 1; j < size; j++) {
                if (board[i][j] != first) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) return first;
        }

        // Check columns
        for (int i = 0; i < size; i++) {
            char first = board[0][i];
            if (first == EMPTY) continue;
            isWin = true;
            for (int j = 1; j < size; j++) {
                if (board[j][i] != first) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) return first;
        }

        // Check diagonals
        char first = board[0][0];
        if (first != EMPTY) {
            isWin = true;
            for (int i = 1; i < size; i++) {
                if (board[i][i] != first) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) return first;
        }

        first = board[0][size - 1];
        if (first != ' ') {
            isWin = true;
            for (int i = 1; i < size; i++) {
                if (board[i][size - 1 - i] != first) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) return first;
        }

        return EMPTY;
    }

    public boolean checkDraw() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
