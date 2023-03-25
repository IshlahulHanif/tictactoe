package com.shlh.tictactoe.service;

import com.shlh.tictactoe.enums.GameStatus;
import com.shlh.tictactoe.model.BoardModel;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{
    private BoardModel gameBoardModel;

    GameServiceImpl() { // TODO: remove example template
        this.gameBoardModel = new BoardModel(3);
    }


    @Override
    public BoardModel getBoard() {
        // TODO: implement
        return null;
    }

    @Override
    public boolean isPlayable() {
        // TODO: implement
        return false;
    }

    @Override
    public BoardModel initGame(int size) {
        // TODO: implement
        return null;
    }

    @Override
    public GameStatus play(int row, int col) {
        // TODO: implement
        return GameStatus.Draw;
    }

    @Override
    public GameStatus getCurrentStatus() {
        // TODO: implement
        return null;
    }
}
