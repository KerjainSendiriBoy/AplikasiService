package com.example.aplikasiservice;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;

public class WallpaperChangeService extends Service implements Runnable {
    private int wallpaperId[] = {R.drawable.wallpaper1, R.drawable.wallpaper2};
    private int waktu;
    private int FLAG=0;
    private Thread t;
    private Bitmap gambar;
    private Bitmap gambar1;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public int onStartCommand(Intent i, int flag, int startId)
    {
        super.onStartCommand(i, flag, startId);
        Bundle b = i.getExtras();
        waktu = b.getInt("durasi");
        gambar= BitmapFactory.decodeResource(getResources(), wallpaperId[0]);
        gambar1= BitmapFactory.decodeResource(getResources(), wallpaperId[1]);
        t = new Thread(WallpaperChangeService.this);
        t.start();
        return START_STICKY_COMPATIBILITY;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }
    @Override
    public void run(){
        WallpaperManager myWall;
        myWall = WallpaperManager.getInstance(this);
        try {
            while (true){
                if (FLAG==0){
                    myWall.setBitmap(gambar);
                    FLAG++;
                }else {
                    myWall.setBitmap(gambar1);
                    FLAG--;
                }
                Thread.sleep(100*waktu);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
