package com.example.school.samplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends View {

    List<Circle> circles = new ArrayList();
    Paint paint = new Paint();
    Random random = new Random();
    int backgroundColor = Color.YELLOW;

    OnCirclesChangedListener listener;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            Circle circle = new Circle(x, y,
                    random.nextInt(), 30 + random.nextFloat() * 70);
            circles.add(circle);

            if (listener != null) {
                listener.onCirclesChanged();
            }

            int rx = (int) (x / getWidth() * 255);
            int ry = (int) (y / getHeight() * 255);
            backgroundColor = Color.rgb(rx, ry, 0);
        }
        return true;
    }

    void setOnCirclesChangedListener(OnCirclesChangedListener listener) {
        this.listener = listener;
    }

    //   |
    //abcefgh
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(backgroundColor);

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
}
