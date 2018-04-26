package com.example.school.samplegame;

import android.graphics.Color;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelLoader {

    static final int BLOCK_SIZE = 100;

    static final char GROUND_BLOCK = '1';

    static final char PLAYER = '2';

    static final String LEVEL =
                    "00000000000000\n" +
                    "00000200000000\n" +
                    "00001111000000\n" +
                    "00001000000000\n" +
                    "11111100111111";

   static Random random = new Random();

    static Level loadLevel(){
        List<Block> blocks= new ArrayList<>();
        Player player = null;

        String[] strings = LEVEL.split("\n");
        for (int y = 0; y < strings.length; y++){
            String str = strings[y];
            for (int x = 0; x < str.length(); x++){
                char currentChar = str.charAt(x);
                if(currentChar == GROUND_BLOCK){
                    blocks.add(createBlock(x, y));
                } else if(currentChar == PLAYER){
                    player = createPlayer(x, y);
                }
            }
        }
        Level level = new Level(blocks, player);
        return level;
    }

    static Player createPlayer(int x, int y){
        return new Player(mapToRectF(x, y), Color.RED);
    }

    static Block createBlock(int x, int y) {
        RectF rect = mapToRectF(x, y);
        int color = random.nextInt();
        Block block = new Block(rect, color);

        return block;
    }

    static RectF mapToRectF(int x, int y){
        return new RectF(BLOCK_SIZE * x, BLOCK_SIZE * y,
                BLOCK_SIZE * (x + 1), BLOCK_SIZE * (y + 1));
    }
}
