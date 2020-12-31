package com.example.congratulationseverything;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
/*
---------------------------표지 어댑터--------------------------------------------------
 */
public class ImageAdapter extends BaseAdapter {
    Context context;
    int[] imageIDs; //이미지 파일의 리소스ID

    public ImageAdapter(Context context, int[] imageIDs) {
        this.context = context;
        this.imageIDs = imageIDs;
    }

    public int getCount() {
        return (null != imageIDs) ? imageIDs.length : 0;
    }

    public Object getItem(int position) {
        return imageIDs[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;

        if (null != convertView)
            imageView = (ImageView)convertView;
        else {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(530, 1000));    //그리드 뷰 이미지 크기
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(imageIDs[position]); //position에 맞는 이미지를 imageIDs배열에서 추출
            ImageClickListener imageViewClickListener   //image선택 시 ImageView에 보이게하는 ImageClickListener객체 생성
                    = new ImageClickListener(context, imageIDs[position]);
            imageView.setOnClickListener(imageViewClickListener);
        }
        return imageView;
    }
}
