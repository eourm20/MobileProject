package com.example.congratulationseverything;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
+-------------------------------뷰페이저 어댑터-----------------------------------------------
 */
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewHolderPage> {
    private ArrayList<DataPage> listData;      //표지, 편지지 프레임, 편지 내용, 갤러리 이미지가 담긴 배열
    ViewPagerAdapter(ArrayList<DataPage> data) {
        this.listData = data;
    }
    @Override
    public ViewHolderPage onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false);
        return new ViewHolderPage(view);
    }
    @Override
    public void onBindViewHolder(ViewHolderPage holder, int position) {
        if(holder instanceof ViewHolderPage){
            ViewHolderPage viewHolder = (ViewHolderPage) holder;
            viewHolder.onBind(listData.get(position));
        }
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
}