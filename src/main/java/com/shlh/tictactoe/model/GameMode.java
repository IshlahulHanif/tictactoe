package com.shlh.tictactoe.model;

import org.jetbrains.annotations.NotNull;

public class GameMode {

    private Integer size;

    public GameMode() {}

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return this.size;
    }
}
