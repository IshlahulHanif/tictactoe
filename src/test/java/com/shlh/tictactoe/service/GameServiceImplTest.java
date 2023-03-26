package com.shlh.tictactoe.service;

import com.shlh.tictactoe.enums.GameStatus;
import com.shlh.tictactoe.model.BoardModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameServiceImpl();
    }

    @Test
    void testInitGame() {
        BoardModel board = gameService.initGame(4);
        assertNotNull(board);
        assertEquals(4, board.getSize());
    }

    @Test
    void testPlay() {
        gameService.initGame(3);
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());

        // valid move
        assertEquals(GameStatus.PlayerOTurn, gameService.play(0, 0));
        assertEquals(GameStatus.PlayerOTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerXTurn, gameService.play(1, 1));
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());

        // invalid move (already played)
        assertEquals(GameStatus.Invalid, gameService.play(0, 0));
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());
    }

    @Test
    void testPlayWinX() {
        gameService.initGame(3);
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());

        // valid move
        assertEquals(GameStatus.PlayerOTurn, gameService.play(0, 0));
        assertEquals(GameStatus.PlayerOTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerXTurn, gameService.play(1, 1));
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerOTurn, gameService.play(0, 1));
        assertEquals(GameStatus.PlayerOTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerXTurn, gameService.play(1, 2));
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerXWin, gameService.play(0, 2));
        assertEquals(GameStatus.PlayerXWin, gameService.getAndCalculateCurrentStatus());

        // test no more move can be done
        assertEquals(GameStatus.Invalid, gameService.play(2, 2));
    }

    @Test
    void testPlayWinO() {
        gameService.initGame(3);
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());

        // valid move
        assertEquals(GameStatus.PlayerOTurn, gameService.play(0, 0));
        assertEquals(GameStatus.PlayerOTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerXTurn, gameService.play(2, 0));
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerOTurn, gameService.play(1, 1));
        assertEquals(GameStatus.PlayerOTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerXTurn, gameService.play(2, 1));
        assertEquals(GameStatus.PlayerXTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerOTurn, gameService.play(0, 2));
        assertEquals(GameStatus.PlayerOTurn, gameService.getAndCalculateCurrentStatus());
        assertEquals(GameStatus.PlayerOWin, gameService.play(2, 2));
        assertEquals(GameStatus.PlayerOWin, gameService.getAndCalculateCurrentStatus());

        // test no more move can be done
        assertEquals(GameStatus.Invalid, gameService.play(0, 2));
    }

    @Test
    void testDraw() {
        gameService.initGame(3);
        assertEquals(GameStatus.PlayerXTurn, gameService.getCurrentStatus());

        // simulate game play
        gameService.play(0, 0); // X
        gameService.play(0, 1); // O
        gameService.play(1, 1); // X
        gameService.play(2, 2); // O
        gameService.play(2, 1); // X
        gameService.play(2, 0); // O
        gameService.play(1, 0); // X
        gameService.play(2, 1); // O
        gameService.play(1, 2); // X
        gameService.play(0, 2); // O

        // game should end in a draw
        assertEquals(GameStatus.Draw, gameService.getCurrentStatus());
    }
}