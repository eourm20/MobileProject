package com.example.congratulationseverything;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
/*
-----------------표지 선택 화면(결혼)-------------------------------------------
 */
public class W_FrameActivity extends AppCompatActivity {
    private int[] imageIDs = {
            R.drawable.w1,
            R.drawable.w2,
            R.drawable.w3,
            R.drawable.w4,
            R.drawable.w5,
            R.drawable.w6,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wedding_frame);
        //사진들을 보여줄 GridView 뷰의 어댑터 객체를 정의하고 그것을 이 뷰의 어댑터로 설정
        GridView gridViewImages = (GridView)findViewById(R.id.GridView01);
        ImageAdapter imageGridAdapter = new ImageAdapter(this,imageIDs);
        gridViewImages.setAdapter(imageGridAdapter);
    }
}
