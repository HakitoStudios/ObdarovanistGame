package com.example.school.samplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends View {

    List<Circle> circles = new ArrayList<>();
    Level level;
    Paint paint = new Paint();
    Random random = new Random();
    int backgroundColor = Color.YELLOW;

    OnCirclesChangedListener listener;

    boolean pressed;
    float x;
    float y;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        level = LevelLoader.loadLevel();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            pressed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            pressed = true;
            if (y < getHeight() * 0.3) {
                level.player.speedY = -5;
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {

            Circle circle = new Circle(x, y,
                    random.nextInt(), 30 + random.nextFloat() * 70);
            //circles.add(circle);

            if (listener != null) {
                listener.onCirclesChanged();
            }

            int rx = (int) (x / getWidth() * 255);
            int ry = (int) (y / getHeight() * 255);
            //backgroundColor = Color.rgb(rx, ry, 0);
        }
        return true;
    }

    void movePlayer(float x) {
        if (pressed) {
            level.player.move(x > getWidth() / 2);
        }
    }

    void setOnCirclesChangedListener(OnCirclesChangedListener listener) {
        this.listener = listener;
    }

    //   |
    //abcefgh
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        checkCollisions();

        movePlayer(x);
        level.player.update();

        canvas.translate(-level.player.rect.centerX() + (getWidth() / 2), 0);

        canvas.drawColor(backgroundColor);

        for (int i = 0; i < level.blocks.size(); i++) {
            Block block = level.blocks.get(i);
            paint.setColor(block.color);
            canvas.drawRect(block.rect, paint);
        }

        paint.setColor(level.player.color);
        canvas.drawRect(level.player.rect, paint);

        for (int i = 0; i < circles.size(); i++) {
            Circle circle = circles.get(i);
            circle.radius--;

            if (circle.radius <= 0) {
                circles.remove(i);
                if (listener != null) {
                    listener.onCirclesChanged();
                }
                i--;
            }

            paint.setColor(circle.color);
            canvas.drawCircle(circle.x, circle.y, circle.radius, paint);
        }

        invalidate();
    }

    private void checkCollisions() {
        boolean collides = false;
        for (Block block : level.blocks) {
            if (RectF.intersects(block.rect, level.player.rect)) {
                collides = true;
                break;
            }
        }
        level.player.setFalling(!collides);
    }
}
