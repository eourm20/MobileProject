package com.example.congratulationseverything;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {
    int letter_num;
    int photo_num;
    private static final String TAG = "MusicServicTest";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Intent receivedIntent = getIntent();
        letter_num = receivedIntent.getIntExtra("LetterNumber",0);
        photo_num = receivedIntent.getIntExtra("PhotoNumber",0);
        /*
        ----------------------커스텀 컨포넌트 연결 후 이벤트 처리---------------------------------------
         */
        final customcomponent view = (customcomponent)findViewById(R.id.circle);    //위젯 연결
        view.setCircleListener(new customcomponent.CircleListener(){
            @Override
            public void onMatch(int x, int y){
                if(225 <x && x<400 && 1180 <y && y< 1330){  //분홍 상자에 넣으면 음악과 함께 재생(서비스)
                    Log.d(TAG, "Music start");
                    startService(new Intent(getApplicationContext(), MusicService.class));
                    Intent intent = new Intent(getApplicationContext(),record.class);
                    intent.putExtra("LetterNumber", letter_num);   //편지지 개수
                    intent.putExtra("PhotoNumber",photo_num);      //사진 개수
                    startActivity(intent);
                }
                else if (750 <x && x<890 && 1180 <y && y< 1335){    //노란 상자에 넣으면 음악없이 재생
                    Intent intent = new Intent(getApplicationContext(),record.class);
                    intent.putExtra("LetterNumber", letter_num);   //편지지 개수
                    intent.putExtra("PhotoNumber",photo_num);      //사진 개수
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "상자 안에 넣어주세요!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}