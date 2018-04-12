package com.example.school.samplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {

    float radius = 10;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //ARGB 0xffffff00
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        radius += 1;

        canvas.drawColor(Color.YELLOW);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(100, 200, radius, paint);

        invalidate();
    }
}
