package com.example.congratulationseverything;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
/*
------------------배경 음악 서비스----------------------------------------------------
 */
public class MusicService extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer player;

    public IBinder onBind(Intent intent){   //연결형에서 재정의
        return null;
    }
    public void onCreate(){
        Log.d(TAG,"onCreate()");
        player = MediaPlayer.create(this,R.raw.bgm);    //재생기
        player.setLooping(false);
    }
    public void onDestroy(){
        Log.d(TAG,"onDestroy()");
        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {  //onCreate() 다음에 실행
        Log.d(TAG,"onStart()");
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }
}


