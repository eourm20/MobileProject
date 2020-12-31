package com.example.congratulationseverything;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
/*
--------------------------배경과 편지 내용 보여주기------------------------------------
 */
public class ViewHolderPage extends RecyclerView.ViewHolder {
    private TextView letter;
    private ImageView image_back;

    DataPage data;

    ViewHolderPage(View itemView) {
        super(itemView);
        letter = itemView.findViewById(R.id.letter);
        image_back = itemView.findViewById(R.id.image_back);
    }
    public void onBind(DataPage data){
        this.data = data;
        letter.setText(data.getLetter());
        if (46 < data.getBack().charAt(0) && data.getBack().charAt(0) <=58) {    //표지와 편지지 프레임의 리소스 ID는 숫자 0~9
            int back1 = Integer.valueOf(data.getBack());
            image_back.setBackgroundResource(back1);
        }
        else {
            Uri back2 = Uri.parse(data.getBack());  //갤러리에서 선택된 이미지는 URI
            image_back.setImageURI(back2);
        }
    }
}