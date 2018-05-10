package com.example.school.samplegame;

import android.graphics.RectF;

public class Player {

    static final float SPEED = 2;

    RectF rect;

    int color;
    float speedY;
    private boolean falling;

    public Player(RectF rect, int color) {
        this.rect = rect;
        this.color = color;
    }

    void update() {
        if (falling) {
            speedY += 0.1f;
        }

        rect.offset(0, speedY);
    }

    void move(boolean right) {
        rect.offset(right ? SPEED : -SPEED, 0);
    }

    void setFalling(boolean falling) {
        this.falling = falling;
        if (!falling) {
            speedY = 0;
        }
    }
}
