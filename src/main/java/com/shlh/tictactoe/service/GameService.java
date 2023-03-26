package com.shlh.tictactoe.service;

import com.shlh.tictactoe.enums.GameStatus;
import com.shlh.tictactoe.model.BoardModel;

public interface GameService {
    BoardModel getBoard();
    BoardModel initGame(int size);
    GameStatus play(int row, int col);
    GameStatus getAndCalculateCurrentStatus();
    GameStatus getCurrentStatus();
}
