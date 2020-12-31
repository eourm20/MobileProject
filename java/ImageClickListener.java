package com.example.congratulationseverything;

import android.content.Context;
import android.content.Intent;
import android.view.View;
/*
  ---------------------------표지 추출하는 클래스--------------------------------------------------
 */
public class ImageClickListener implements View.OnClickListener {
    Context context;
    int imageID;    //그리드뷰에서 선택된 이미지 리소스 ID

    public ImageClickListener(Context context, int imageID) {
        this.context = context;
        this.imageID = imageID;
    }
    public void onClick(View v) {
        Intent intent = new Intent(context, Letter_FrameActivity.class);    //클릭 시 표지 선택 액티비티로 전환
        intent.putExtra("image ID", imageID);   //이미지 리소스 ID 전달
        context.startActivity(intent);
    }
}
