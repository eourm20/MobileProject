package com.example.congratulationseverything;

import androidx.annotation.NonNull;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/*
---------------------------달력과 일정(추가,삭제)입력하는 엑티비티--------------------------------------
 */
public class CalenderActivity extends AppCompatActivity {
    public String fname=null;
    public String str;
    public String saved_str=null;
    public CalendarView calendarView;
    public Button del_Btn,save_Btn;
    public TextView diaryTextView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        calendarView=findViewById(R.id.calendarView);
        diaryTextView=findViewById(R.id.diaryTextView);
        save_Btn=findViewById(R.id.save_Btn);
        del_Btn=findViewById(R.id.del_Btn);
        textView2=findViewById(R.id.textView2);
/*
----------------------------------캘린더뷰 이용---------------------------------------------------
 */
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));   //선택된 날 보여줌
                checkDay(year,month,dayOfMonth);
            }
        });
        /*
        ----------------------------일정 추가 버튼 클릭--------------------------------------------
         */
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //textView2.setVisibility(View.INVISIBLE);
                //str = null;
/*
          ---------------------------커스텀 다이얼로그 이용-------------------------------------
 */
                //inflate를 이용해 layout을 view 객체로 생성
                LayoutInflater inflater = getLayoutInflater();
                //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언
                final View dialogView= inflater.inflate(R.layout.save_dialog, null);
                /*
                --------------------일정 추가하는 Dialog 생성------------------------------------
                 */
                AlertDialog.Builder buider= new AlertDialog.Builder(CalenderActivity.this); //AlertDialog.Builder 객체 생성
                buider.setTitle("Who's Day??"); //Dialog 제목
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅 (Customize)
                buider.setPositiveButton("Save", new DialogInterface.OnClickListener() {  //추가 버튼 누르면
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edit_name = (EditText)dialogView.findViewById(R.id.dialog_edit);   //이름 입력 객체
                        RadioGroup rg = (RadioGroup)dialogView.findViewById(R.id.dialog_rg); //(결혼 or 생일)선택 객체
                        String name = edit_name.getText().toString();
                        int checkedId = rg.getCheckedRadioButtonId();
                        RadioButton rb = (RadioButton)rg.findViewById(checkedId);    //체크된 라디오 버튼 객체
                        String choice_Day = rb.getText().toString(); //체크된 라이도 버튼의 내용 저장
                        str = name+"-"+choice_Day;
                        textView2.setText(str); //텍스트뷰에 이름과 선택된 라디오 버튼 내용 입력
                        saveDiary(fname);   //fname이름의 파일에 내용 저장
                        str = null; //str 초기화
                        Toast.makeText(CalenderActivity.this, "일정을 추가했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                buider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  //취소 버튼 누르면
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CalenderActivity.this, "추가를 취소합니다", Toast.LENGTH_SHORT).show();
                    }
                });
                //설정한 값으로 AlertDialog 객체 생성
                AlertDialog dialog=buider.create();
                //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.setCanceledOnTouchOutside(false);//없어지지 않도록 설정
                dialog.show();
                textView2.setVisibility(View.VISIBLE);
            }
        });
    }
/*
    -----------------------------날짜 클릭----------------------------------------
 */
    public void  checkDay(int cYear,int cMonth,int cDay){
        fname=""+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";   //저장할 파일 이름설정
        FileInputStream fis=null;   //FileStream fis 변수
        textView2.setVisibility(View.INVISIBLE);
        try{
            fis=openFileInput(fname);
            byte[] fileData=new byte[fis.available()];
            fis.read(fileData); //파일 내용 불러오기
            fis.close();
            saved_str=new String(fileData);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(saved_str);   //저장된 일정 출력
/*
-------------------------일정 삭제 버튼 클릭------------------------------------------------
 */
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setVisibility(View.INVISIBLE);
                    removeDiary(fname); //파일 내용 삭제
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay){    //파일 내용 삭제
        FileOutputStream fos=null;
        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String removed_str="";
            fos.write((removed_str).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay){  //파일 내용 추가
        FileOutputStream fos=null;
        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content = textView2.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}