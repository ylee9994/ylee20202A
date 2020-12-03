package com.ylee.a12spinnerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
// 교재 444쪽
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("스피너테스트 자바 이용희");

        String[] movie = {"쿵푸팬더", "짱구는못말려", "아저씨",
        "아바타", "대부", "국가대표",
        "토이스토리3", "마당을 나온 암탉", "죽은시인의 사회", "서유기"};
        final Integer[] posterId = {R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov23,R.drawable.mov24,
                R.drawable.mov25,R.drawable.mov26,
                R.drawable.mov27,R.drawable.mov28,
                R.drawable.mov29,R.drawable.mov30};

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,  movie);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView ivpicture = findViewById(R.id.ivPoster);
                ivpicture.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ivpicture.setPadding(5,5,5,5);
                ivpicture.setImageResource(posterId[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
