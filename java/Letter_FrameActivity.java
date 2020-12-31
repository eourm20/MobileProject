package com.example.congratulationseverything;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
/*
----------------------------편지지 프레임 화면--------------------------------------------
 */
public class Letter_FrameActivity extends AppCompatActivity {
    int imageID;
    private int[] frameIDs ={
            R.drawable.l1,
            R.drawable.l2,
            R.drawable.l3,
            R.drawable.l4,
            R.drawable.l5,
            R.drawable.l6,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.letter_frame);
        Intent receivedIntent = getIntent();
        imageID = (Integer)receivedIntent.getExtras().get("image ID");  //표지 프레임 받기
        //사진들을 보여줄 GridView 뷰의 어댑터 객체를 정의하고 그것을 이 뷰의 어댑터로 설정
        GridView gridViewImages = (GridView)findViewById(R.id.GridView03);
        L_ImageAdapter imageGridAdapter = new L_ImageAdapter(this, frameIDs, imageID);
        gridViewImages.setAdapter(imageGridAdapter);
    }
}

