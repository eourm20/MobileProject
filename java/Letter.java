package com.example.congratulationseverything;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Letter extends AppCompatActivity {
    private int frameID;
    private String letter_text;
    private int count = 0;  //편지지 개수
    String name;    //공유프레퍼런스에 저장할 이름
    EditText letter;
    /*
    ---------------------편지지(만들기)----------------------------------------------------------
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.letter);
        Intent receivedIntent = getIntent();
        count = receivedIntent.getIntExtra("LetterNumber", 0);
        SharedPreferences settings = getSharedPreferences("DATA", 0);   //공유 레퍼런스에서 "frame ID" 불러오기
        frameID = settings.getInt("frame ID", 0);
        ImageView imageView = (ImageView)findViewById(R.id.background);
        imageView.setImageResource(frameID);
        letter = (EditText)findViewById(R.id.letter);
        name = "letter"+count;
        String savedText = settings.getString(name,"");
        if (savedText != null)      //내용이 있다면 편집중인 편지지
            letter.setText(savedText);
    }

/*
    ------------------------편지지 내용 공유프레퍼런스에 저장--------------------------------------
 */
    public void savePreferences() {
        SharedPreferences sf = getSharedPreferences("DATA", 0);
        SharedPreferences.Editor editor = sf.edit();
        letter_text = letter.getText().toString();
        name = "letter"+count;
        editor.putString(name, letter_text);
        editor.commit();
    }

    protected void onStop() {
        super.onStop();
        savePreferences();
    }
/*
-------------------------옵션 메뉴-------------------------------------------------------------
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "편지지 추가");
        menu.add(0, 2, 0, "맨 뒤에 사진 추가");
        menu.add(0, 3, 0, "페이지 삭제");
        menu.add(0, 4, 0, "만들기");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        savePreferences();
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "편지지 추가", Toast.LENGTH_SHORT).show();
                count+=1;
                letter.setText("");
                return true;
            case 2:
                Toast.makeText(this, "편지지 추가 종료", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), Photo.class);
                intent2.putExtra("LetterNumber", count);   //편지지 개수
                startActivity(intent2);
                return true;
            case 3:
                Toast.makeText(this, "현재 페이지를 삭제했습니다.", Toast.LENGTH_SHORT).show();
                SharedPreferences sf = getSharedPreferences("DATA", 0);
                SharedPreferences.Editor editor = sf.edit();
                editor.remove(name);    //편지 내용 삭제
                if (count != 0) {
                    count -= 1;
                    String pre_name = "letter"+count;    //전 편지 이름(공유프레퍼런스에 저장한)
                    String pre_letter = sf.getString(pre_name,"");
                    letter_text = pre_letter;   //전 편지로 저장할 편지 교체
                    letter.setText(letter_text); //전 편지 내용 편집화면
                    editor.commit();
                    return true;
                }
                else{
                    letter.setText(""); //편지 내용 비우기
                    editor.commit();
                    return true;
                }
            case 4:
                Toast.makeText(this, "----제작중----", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(getApplicationContext(), Result.class);
                intent3.putExtra("LetterNumber", count);   //편지지 개수
                intent3.putExtra("PhotoNumber",-1);      //사진 개수
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
