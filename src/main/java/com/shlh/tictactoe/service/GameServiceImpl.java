package com.shlh.tictactoe.service;

import com.shlh.tictactoe.enums.GameStatus;
import com.shlh.tictactoe.enums.Player;
import com.shlh.tictactoe.model.BoardModel;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{
    private BoardModel gameBoardModel;
    private Player currentPlayer;

    private GameStatus status;

    GameServiceImpl() { // TODO: remove example template
        this.gameBoardModel = new BoardModel(3);
        this.status = GameStatus.PlayerXTurn;
    }

    @Override
    public BoardModel getBoard() {
        return gameBoardModel;
    }

    @Override
    public BoardModel initGame(int size) {
        this.gameBoardModel = new BoardModel(size);
        this.currentPlayer = Player.X;
        this.status = GameStatus.PlayerXTurn;
        return gameBoardModel;
    }

    @Override
    public GameStatus play(int row, int col) {
        if (status.isEnd()) {
            return GameStatus.Invalid;
        }

        boolean isMoveValid = gameBoardModel.play(row, col, currentPlayer == Player.X);
        if (!isMoveValid) {
            return GameStatus.Invalid;
        }

        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
        return getAndCalculateCurrentStatus();
    }

    @Override
    public GameStatus getAndCalculateCurrentStatus() {
        updateCurrentStatus();
        return status;
    }

    @Override
    public GameStatus getCurrentStatus() {
        return this.status;
    }

    private void updateCurrentStatus() {
        char winner = gameBoardModel.checkWinner();
        switch (winner) {
            case BoardModel.X -> status = GameStatus.PlayerXWin;
            case BoardModel.O -> status = GameStatus.PlayerOWin;
            default -> {
                if (gameBoardModel.checkDraw()) {
                    status = GameStatus.Draw;
                } else {
                    status = currentPlayer == Player.X ? GameStatus.PlayerXTurn : GameStatus.PlayerOTurn;
                }
            }
        }
    }
}
