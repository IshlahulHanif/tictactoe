package com.shlh.tictactoe.enums;

public enum GameStatus {
    NotStarted,
    PlayerXWin,
    PlayerOWin,
    PlayerXTurn,
    PlayerOTurn,
    Draw,
    Invalid;

    public boolean isEnd() {
        return switch (this) {
            case Draw, PlayerXWin, PlayerOWin -> true;
            default -> false;
        };
    }

    public boolean isPlayable() {
        return switch (this) {
            case PlayerXTurn, PlayerOTurn -> true;
            default -> false;
        };
    }

    public String getStatusString() {
        return switch (this) {
            case PlayerXWin -> "Player X wins!";
            case PlayerOWin -> "Player O wins!";
            case PlayerXTurn -> "Player X turn";
            case PlayerOTurn -> "Player O turn";
            case Draw -> "Draw!";
            default -> "Something went wrong";
        };
    }
}
