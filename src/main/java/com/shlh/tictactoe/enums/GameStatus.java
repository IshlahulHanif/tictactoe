package com.shlh.tictactoe.enums;

public enum GameStatus {
    NotStarted,
    PlayerXWin,
    PlayerOWin,
    PlayerXTurn,
    PLayerOTurn,
    Draw;

    public boolean isEnd() {
        return switch (this) {
            case Draw, PlayerXWin, PlayerOWin -> true;
            default -> false;
        };
    }
}
