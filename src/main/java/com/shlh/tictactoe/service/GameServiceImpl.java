package com.shlh.tictactoe.service;

import com.shlh.tictactoe.enums.GameStatus;
import com.shlh.tictactoe.enums.Player;
import com.shlh.tictactoe.model.BoardModel;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{
    private BoardModel gameBoardModel;
    private Player currentPlayer;

    GameServiceImpl() { // TODO: remove example template
        this.gameBoardModel = new BoardModel(3);
    }

    @Override
    public BoardModel getBoard() {
        return gameBoardModel;
    }

    @Override
    public boolean isPlayable() {
        // TODO: implement
        return false;
    }

    @Override
    public BoardModel initGame(int size) {
        this.gameBoardModel = new BoardModel(size);
        this.currentPlayer = Player.X;
        return gameBoardModel;
    }

    @Override
    public GameStatus play(int row, int col) {
        boolean isMoveValid = gameBoardModel.play(row, col, currentPlayer == Player.X);
        if (!isMoveValid) {
            return GameStatus.Invalid;
        }

        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
        return getCurrentStatus();
    }

    @Override
    public GameStatus getCurrentStatus() {
        char winner = gameBoardModel.checkWinner();
        switch (winner) {
            case BoardModel.X -> {
                return GameStatus.PlayerXWin;
            }
            case BoardModel.O -> {
                return GameStatus.PlayerOWin;
            }
            default -> {
                if (gameBoardModel.checkDraw()) {
                    return GameStatus.Draw;
                } else {
                    return currentPlayer == Player.X ? GameStatus.PlayerXTurn : GameStatus.PlayerOTurn;
                }
            }
        }
    }
}
