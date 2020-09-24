package com.ylee.a021thread;

// 2020 0910
// 교재 488쪽
// AdobeXD
// 실습: sb2의메세지도 같이 보여줄것
// 1. 진행율A:61%, 진행율B: 35%
// 2. prgressBar에 sb1의 내용을 반영

// MessageHandler

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb1;
    Button btnInc, btnDec;
    Button threadStart;
    TextView tvSeek;
    SeekBar sb1, sb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.pBar1);
        btnInc = findViewById(R.id.btnInc);
        btnDec = findViewById(R.id.btnDec);
        tvSeek = findViewById(R.id.tvSeek);
        sb1 = findViewById(R.id.sBar1);
        sb2 = findViewById(R.id.sBar2);
        threadStart = findViewById(R.id.btnStart);
        final BackgroundThread thread1 = new BackgroundThread(sb1, 2);
        final BackgroundThread thread2 = new BackgroundThread(sb2, 1);

        threadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                for(int i=0; i<100; ++i){
//                    sb1.setProgress(sb1.getProgress()+2);
//                    sb2.setProgress(sb2.getProgress()+1);
//                    SystemClock.sleep(100);
//                }


//                new Thread(){
//                    public void run(){
//                        for(int i=sb1.getProgress(); i<100; i=i+2){
//                            sb1.setProgress(sb1.getProgress()+2);
//                            SystemClock.sleep(100);
//                        }
//                    }
//                }.start();
//
//                new Thread(){
//                    public void run(){
//                        for(int i=sb2.getProgress(); i<100; i=i+1){
//                            sb2.setProgress(sb2.getProgress()+1);
//                            SystemClock.sleep(100);
//                        }
//                    }
//                }.start();
                thread1.start();
                thread2.start();
            }
        });
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeek.setText("진행율:" + progress + "%");
                pb1.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb1.incrementProgressBy(10);
                // pb1.setProgress(pb1.getProgress()+10);
            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb1.incrementProgressBy(-10);
            }
        });

    }

    class BackgroundThread extends Thread{
        SeekBar seekBar;
        int diff;

        public BackgroundThread(SeekBar sbar, int diff){
            seekBar = sbar;
            this.diff = diff;

        }
        public void run(){
            for(int i=0; i<100; i++){
                    seekBar.setProgress(seekBar.getProgress()+diff);
                    SystemClock.sleep(100);
            }
        }
    }

}
