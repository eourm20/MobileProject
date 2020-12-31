package com.example.congratulationseverything;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

/*
------------------------표지 선택(생일)-------------------------------------
 */
public class B_FrameActivity extends AppCompatActivity {

    private int[] imageIDs ={
            R.drawable.b1,
            R.drawable.b2,
            R.drawable.b3,
            R.drawable.b4,
            R.drawable.b5,
            R.drawable.b6,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday_frame);
        //사진들을 보여줄 GridView 뷰의 어댑터 객체를 정의하고 그것을 이 뷰의 어댑터로 설정
        GridView gridview = (GridView) findViewById(R.id.GridView02);
        ImageAdapter imageGridAdapter = new ImageAdapter(this, imageIDs);
        gridview.setAdapter(imageGridAdapter);
    }
}
