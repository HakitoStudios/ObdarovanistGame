package com.example.school.samplegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    TextView circlesCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView);
        circlesCountTextView = findViewById(R.id.circlesCountTextView);

        gameView.setOnCirclesChangedListener(new OnCirclesChangedListener() {
            @Override
            public void onCirclesChanged() {
                String count = "Circles: " + gameView.circles.size();
                circlesCountTextView.setText(count);
            }
        });
    }
}
