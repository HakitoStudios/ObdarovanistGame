package com.example.school.samplegame;

import java.util.List;

public class Level {

    List<Block> blocks;
    Player player;

    public Level(List<Block> blocks, Player player) {
        this.blocks = blocks;
        this.player = player;
    }
}
