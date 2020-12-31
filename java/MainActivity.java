package com.example.congratulationseverything;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
    ---------------------intent를 이용한 화면 전환------------------------------------
     */
    public void moveWedding(View target){
        Intent intent = new Intent(getApplicationContext(),W_FrameActivity.class);
        startActivity(intent);
    }
    public void moveBirthday(View target){
        Intent intent = new Intent(getApplicationContext(),B_FrameActivity.class);
        startActivity(intent);
    }
    public void moveCalender(View target){
        Intent intent = new Intent(getApplicationContext(), CalenderActivity.class);
        startActivity(intent);
    }
}