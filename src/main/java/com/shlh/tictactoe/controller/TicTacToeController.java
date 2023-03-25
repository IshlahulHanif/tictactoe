package com.shlh.tictactoe.controller;

import com.shlh.tictactoe.enums.GameStatus;
import com.shlh.tictactoe.model.BoardModel;
import com.shlh.tictactoe.service.GameService;
import com.shlh.tictactoe.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicTacToeController {
    private final GameService gameService;

    public TicTacToeController(
            @Autowired GameService gameService
    ) {
        this.gameService = gameService;
    }

    // Get mapping for init web view
    @GetMapping("/")
    public String home () {
        return "play_game";
    }

    @GetMapping("/game")
    public ResponseEntity<BoardModel> getBoard() {
        BoardModel board = gameService.getBoard();
        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(board);
    }

    @PostMapping("/play")
    public ResponseEntity<String> play(@RequestParam int row, @RequestParam int col) { //TODO: make response of message + can game continue ?
        if (row < 0 || col < 0) {
            return ResponseEntity.badRequest().body("Invalid move");
        }

        GameStatus status = gameService.getCurrentStatus();
        if (status == GameStatus.NotStarted) {
            return ResponseEntity.badRequest().body("Cannot play the game yet");
        }

        if (status.isEnd()) {
            return ResponseEntity.badRequest().body("Game has already ended");
        }

        status = gameService.play(row, col);
        return getStringResponseEntity(status);
    }

    @PostMapping("/init")
    public ResponseEntity<BoardModel> initGame(@RequestParam int size, @RequestParam boolean isVsPlayer) {
        BoardModel board = gameService.initGame(size);
        if (board == null) {
            ResponseEntity.internalServerError().body("Something went wrong");
        }
        return ResponseEntity.ok(board);
    }

    @PostMapping("/status")
    public ResponseEntity<String> status() { //TODO: make response of message + can game continue ?
        GameStatus status = gameService.getCurrentStatus();
        return getStringResponseEntity(status);
    }

    private ResponseEntity<String> getStringResponseEntity(GameStatus status) { // TODO: rename?
        return switch (status) {
            case PlayerXWin -> ResponseEntity.ok().body("Player X wins!");
            case PlayerOWin -> ResponseEntity.ok().body("Player O wins!");
            case PlayerXTurn -> ResponseEntity.ok().body("Player X turn");
            case PLayerOTurn -> ResponseEntity.ok().body("Player O turn");
            case Draw -> ResponseEntity.ok().body("Draw!");
            default -> ResponseEntity.internalServerError().body("Something went wrong");
        };
    }
}
