package com.ylee.a11classjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo1, tvInfo2, tvInfo3, tvInfo4, tvInfo5;
    Vehicle v1;
    Car c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo1 = (TextView) findViewById(R.id.tvInfo1);
        tvInfo2 = findViewById(R.id.tvInfo2);
        tvInfo3 = findViewById(R.id.tvInfo3);
        tvInfo4 = findViewById(R.id.tvInfo4);
        tvInfo5 = findViewById(R.id.tvInfo5);

        v1 = new Vehicle("내차", 40, 200,
               40, 4,  false);
        c1 = new Car("내두번째차", 40, 200,
                40, 4,  false,
                "v2");
        v1.setSpeed(-30);
        // v1.speed = -30
        int vspeed = v1.getSpeed();
        // int vspped = v1.speed;
        tvInfo1.setText(v1.toString());
        tvInfo5.setText(v1.sound());

        tvInfo2.setText(c1.toString());
        tvInfo4.setText(c1.sound());
    }
}
