package com.ylee.a03http;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    String naverurl="https://m.naver.com";
    String openweatherapi = "https://api.openweathermap.org/data/2.5/weather?&mode=xml&units=metric&appid=3e0c533ec49a146af626992e6bcce23f&q=seoul";


    String htmldata = null;
    String sourceurl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        sourceurl = openweatherapi;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RequestThread rthread = new RequestThread();
//                rthread.start();
                ReqeustTask reqeustTask = new ReqeustTask();
                reqeustTask.execute();
//                SystemClock.sleep(5000);
//                textView.setText(htmldata);
            }
        });
    }

    class ReqeustTask extends AsyncTask<Integer, Integer, Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("AsyncTask running");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textView.setText(htmldata);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            htmldata = getHTTPdata(sourceurl);
            return null;
        }
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            textView.setText((String)msg.obj);
        }
    };
    // thread의 문제
    // 1. 언제 끝날지 모름
    // 2. 직접 UI에 접근하면 죽음
    class RequestThread extends Thread{
        public void run(){
            htmldata = getHTTPdata(sourceurl);
//            textView.setText((String)htmldata);
            // 이렇게 하면 죽음

            Message message = handler.obtainMessage();
            message.obj = htmldata;
            handler.sendMessage(message);
        }
    }

    public String getHTTPdata(String urlStr){
        String rdata = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                int resCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(conn.getInputStream()));
                String line;
                line = reader.readLine();
                rdata = line;
                while (true){
                    line = reader.readLine();
                    if(line == null)
                        break;
                    rdata += line;
                }
                reader.close();
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rdata;
    }
    public String getHTTPdata2(String urlStr){
        String rdata = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                int resCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(conn.getInputStream()));

                String line = reader.readLine();
                rdata = line;
                while (true){
                    line = reader.readLine();
                    if(line == null)
                        break;
                    rdata = rdata + line + '\n';
                }

                reader.close();
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rdata;
    }


}
