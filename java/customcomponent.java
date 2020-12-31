package com.example.congratulationseverything;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/*
------------------------커스텀 컴포넌트 제작---------------------------------------------
 */
public class customcomponent extends View implements View.OnTouchListener{
    private CircleListener listener;
    int mx =540, my = 450;

    public interface CircleListener {
        public void onMatch(int x, int y);
    }
    public void setCircleListener(CircleListener lis){
        listener = lis;
    }
    public customcomponent(Context context,AttributeSet attr) {
        super(context,attr);
        this.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        mx = (int) event.getX();
        my = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            listener.onMatch(mx,my);    //원을 놓았을때 위치 전달
        }
        invalidate();   //화면 초기화
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(50);
        canvas.drawCircle(mx, my, 80, paint);
        canvas.drawText("공을 상자 안에 넣으면 완성된 편지가 나옵니다~!", 40, 200, paint);
        canvas.drawText("~~노래와 함께 재생~~", 100, 900, paint);
        canvas.drawText("~~노래 없이 재생~~", 600, 900, paint);
    }
}
