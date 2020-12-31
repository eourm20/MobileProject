package com.example.congratulationseverything;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
/*
---------------편지지 어댑터----------------------------------
 */
public class L_ImageAdapter extends BaseAdapter {
    Context context;
    int[] FrameIDs; //이미지 파일의 리소스ID
    int imageID;    //표지 이미지의 정보

    public L_ImageAdapter(Context context, int[] FrameIDs, int imageID) {
        this.context = context;
        this.FrameIDs = FrameIDs;
        this.imageID = imageID;
    }
    public int getCount() {
        return (null != FrameIDs) ? FrameIDs.length : 0;
    }

    public Object getItem(int position) {
        return (null != FrameIDs) ? FrameIDs[position] : 0;
    }


    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (null != convertView)
            imageView = (ImageView)convertView;
        else {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(530, 1000));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(FrameIDs[position]); //position에 맞는 이미지를 imageIDs배열에서 추출
            FrameClickListener imageViewClickListener   //image선택 시 ImageView에 보이게하는 FrameClickListener객체 생성
                    = new FrameClickListener(context, FrameIDs[position], imageID); //표지 이미지 정보도 같이 전달
            imageView.setOnClickListener(imageViewClickListener);
        }

        return imageView;
    }
}
