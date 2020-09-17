package com.ylee.a022async;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
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
    int value;
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
        final BackgroundTask task = new BackgroundTask();
        final BackgroundTask task1 = new BackgroundTask(sb1, 2);
        final BackgroundTask task2 = new BackgroundTask(sb2, 1);

        threadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // task.execute();
                task1.execute();
                task2.execute();
            }
        });
    }



    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        SeekBar pbar;
        int dif;
        public BackgroundTask() {
            super();
        }

        public BackgroundTask(SeekBar ppbar, int pdif){
            pbar = ppbar;
            dif = pdif;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            value = 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbar.setProgress(values[0].intValue());
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            for (int i = 0; i < 100; i = i+dif) {
                value = value + dif;
                publishProgress(value);
                SystemClock.sleep(100);
             }
            return value;
        }

    }
}
