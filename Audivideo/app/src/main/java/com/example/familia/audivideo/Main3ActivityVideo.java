package com.example.familia.audivideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

public class Main3ActivityVideo extends AppCompatActivity {

    private VideoView myvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_video);

        myvideo = (VideoView)findViewById(R.id.myvideoView);

        if (ContextCompat.checkSelfPermission(Main3ActivityVideo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Main3ActivityVideo.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        { ActivityCompat.requestPermissions(Main3ActivityVideo.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000); }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    //Ahora el metodo apra las opciones del ActionBar y debe llamarse onOptionsItemSelected
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item3){
            Intent navegar = new Intent(this, MainActivity.class);
            startActivity(navegar);
            //Toast.makeText(this, "Opcion1", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.item2){
            Intent navegar2 = new Intent(this, Main2ActivityPics.class);
            startActivity(navegar2);
            //Toast.makeText(this, "Opcion2", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.buscar){
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.compartir){
            Toast.makeText(this, "Compartir", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id ==  R.id.item1){
            Intent navegar3 = new Intent(this, Main3ActivityVideo.class);
            startActivity(navegar3);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static final int REQUEST_VIDEO_CAPTURE = 1;

    public void CastVideo(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            myvideo.setVideoURI(videoUri);
        }
    }*/


}
