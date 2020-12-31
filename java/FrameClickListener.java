package com.example.congratulationseverything;

import android.content.Context;
import android.content.Intent;
import android.view.View;
/*
----------------------------편지지 프레임 추출하는 클래스---------------------------------------
 */
public class FrameClickListener implements View.OnClickListener {
    Context context;
    int frameID;    //그리드뷰에서 선택된 이미지 리소스 ID(편지지 프레임)
    int imageID;

    public FrameClickListener(Context context, int frameID, int imageID) {
        this.context = context;
        this.frameID = frameID;
        this.imageID = imageID;
    }
    public void onClick(View v) {
        Intent intent = new Intent(context, Start.class);   //편지만드는 시작화면으로 이동
        intent.putExtra("frame ID", frameID);   //편지지 프레임 리소스 ID
        intent.putExtra("image ID", imageID);   //표지 이미지 리소스 ID
        context.startActivity(intent);
    }
}
