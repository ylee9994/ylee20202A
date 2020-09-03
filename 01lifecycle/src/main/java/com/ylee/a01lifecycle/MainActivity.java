package com.ylee.a01lifecycle;

// 교재5판 416쪽 액티비티생명주기
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.util.Log.i("LifeCycle", "OnCreate 호출");
        Button buttontel = findViewById(R.id.buttontel);
        buttontel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:01012345678");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
    }
    @Override
    // 클래스룸 코드 umu5vml
    protected void onDestroy() {
        super.onDestroy();   Log.i("LifeCycle", "OnDestroy 호출");
    }
    @Override
    protected void onPause() {
        super.onPause();      Log.i("LifeCycle", "OnPause 호출");
    }
    @Override
    protected void onRestart() {
        super.onRestart();  Log.i("LifeCycle", "OnRestart 호출");
    }
    @Override
    protected void onResume() {
        super.onResume();  Log.i("LifeCycle", "OnResume 호출");
    }

    @Override
    protected void onStart() {
        super.onStart(); Log.i("LifeCycle", "OnStart 호출");
    }
    @Override
    protected void onStop() {
        super.onStop();  Log.i("LifeCycle", "OnStop 호출");
    }
}
