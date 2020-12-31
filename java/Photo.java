package com.example.congratulationseverything;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Photo extends AppCompatActivity {
    private int letter_num;
    ImageView photo;
    String select_photo;
    private final int GET_GALLERY_IMAGE = 200;
    Uri selectedImageUri;
    int count = 0;  //사진 개수
    String name;    //공유프레퍼런스에 저장할 이름
    /*
    ----------------------------사진(만들기)--------------------------------------------------
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        Intent receivedIntent = getIntent();
        letter_num = receivedIntent.getIntExtra("LetterNumber", 0);
        photo = (ImageView) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {   //배경 누르면 갤려리에 접근
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }
    /*
    ---------------------------갤러리에서 선택한 사진 띄우기------------------------------------------
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            photo.setImageURI(selectedImageUri);    //이미지뷰에 선택한 사진 띄우기
            select_photo = selectedImageUri.toString(); //선택된 사진의 경로 저장
        }
    }
    /*
    ------------------------사진 공유프레퍼런스에 저장---------------------------------------------
     */
    public void savePreferences() {
        SharedPreferences sf = getSharedPreferences("DATA", 0);
        SharedPreferences.Editor editor = sf.edit();
        name = "photo"+count;
        editor.putString(name, select_photo);
        editor.commit();
    }
    protected void onStop() {
        super.onStop();
        savePreferences();
    }
/*
-------------------------------------옵션 메뉴--------------------------------------------------------
 */
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "사진 추가");
        menu.add(0, 2, 0, "페이지 삭제");
        menu.add(0, 3, 0, "만들기");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        savePreferences();
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "사진 추가", Toast.LENGTH_SHORT).show();
                count+=1;
                select_photo= null; //선택한 사진 비움
                photo.setImageResource(0);  //배경 비움
                return true;
            case 2:
                Toast.makeText(this, "현재 페이지를 삭제했습니다.", Toast.LENGTH_SHORT).show();
                SharedPreferences sf = getSharedPreferences("DATA", 0);
                SharedPreferences.Editor editor = sf.edit();
                editor.remove(name);    //저장한 현재 사진 정보 삭제
                if (count == 0){
                    Intent intent = new Intent(getApplicationContext(), Letter.class);
                    intent.putExtra("LetterNumber", letter_num);   //편지지 개수
                    startActivity(intent);
                    editor.commit();
                    return true;
                }
                else{
                    count-=1;
                    String pre_name = "photo"+count;    //전 사진 이름(공유프레퍼런스에 저장한)
                    String pre_photo = sf.getString(pre_name,"");
                    Uri back = Uri.parse(pre_photo);  //갤러리에서 선택된 이미지는 URI
                    photo.setImageURI(back);
                    select_photo = pre_photo;   //전 사진으로 저장할 사진 교체
                    editor.commit();
                    return true;
                }
            case 3:
                Toast.makeText(this, "----제작중----", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Result.class);
                intent.putExtra("LetterNumber", letter_num);   //편지지 개수
                intent.putExtra("PhotoNumber",count);      //사진 개수
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}