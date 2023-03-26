package com.shlh.tictactoe.controller;

import com.shlh.tictactoe.enums.GameStatus;
import com.shlh.tictactoe.model.BoardModel;
import com.shlh.tictactoe.model.GameMode;
import com.shlh.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String home(Model model) {
        GameMode gameMode = new GameMode();
        model.addAttribute("gameMode", gameMode);
        return "index";
    }

    // Get mapping for init web view
    @GetMapping("/play_game")
    public String playGame(Model model) {
        BoardModel board = gameService.getBoard();
        if (board == null) {
            return "index";
        }
        model.addAttribute("board", board);

        GameStatus status = gameService.getCurrentStatus();
        model.addAttribute("message", status);
        return "play_game";
    }

    // Get mapping for init web view
    @GetMapping("/game_end")
    public String endGame(Model model) {
        GameStatus status = gameService.getCurrentStatus();
        model.addAttribute("message", status);
        return "game_end";
    }

    @PostMapping("/play")
    public String play(@RequestParam int row, @RequestParam int col, Model model) {
        BoardModel board = gameService.getBoard();
        if (board == null) {
            return "redirect:/index";
        }
        model.addAttribute("board", board);

        if (row < 0 || col < 0) {
            model.addAttribute("message", "Invalid move");
            return "play_game";
        }

        GameStatus status = gameService.getCurrentStatus();
        if (!status.isPlayable()) {
            model.addAttribute("message", "Cannot play the game yet");
            return "play_game"; //TODO: redirect somewhere else
        }

        status = gameService.play(row, col);
        model.addAttribute("message", status);

        if (status.isEnd()) {
            return "redirect:/game_end";
        }

        return "play_game";
    }

    @PostMapping("/init")
    public String initGame(GameMode gameMode, Model model) {
        BoardModel board = gameService.initGame(gameMode.getSize());
        if (board == null) {
            return "redirect:/";
        }
        return "redirect:/play_game";
    }
}
