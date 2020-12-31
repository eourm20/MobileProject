package com.example.congratulationseverything;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {
    int frameID;
    int imageID;
    /*
    --------------------표지 화면(만들기)-------------------------------------------------------------
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Intent receivedIntent = getIntent();
        frameID = (Integer)receivedIntent.getExtras().get("frame ID");
        imageID = (Integer)receivedIntent.getExtras().get("image ID");
        ImageView imageView = (ImageView)findViewById(R.id.main_photo);
        imageView.setImageResource(imageID);
    }
/*
-------------------------표지, 프레임 공유프레퍼런스에 저장---------------------------------------------
 */
    public void savePreferences() {
        SharedPreferences sf = getSharedPreferences("DATA", 0);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("frame ID", frameID);
        editor.putInt("image ID", imageID);
        editor.commit();
    }

    protected void onStop() {
        super.onStop();
        savePreferences();
    }

/*
--------------------------옵션 메뉴-----------------------------------------------------------------
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "편지지 추가");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        savePreferences();
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "편지지 추가", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), Letter.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
