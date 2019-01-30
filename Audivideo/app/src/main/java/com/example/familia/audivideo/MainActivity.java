package com.example.familia.audivideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button botonrecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        botonrecord = (Button)findViewById(R.id.button3record);

        //Verificar si tienes permisos, sacar una ventana para preguntar al usuario
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        { ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000); }

    }

    //Método para el ActionBar, debe ser este nombre forzosamente onCreateOptionsMenu
    public boolean onCreateOptionsMenu(Menu menu){
       getMenuInflater().inflate(R.menu.overflow, menu);
       return true;
    }

    //Ahora el metodo apra las opciones del ActionBar y debe llamarse onOptionsItemSelected
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item3){
            //Toast.makeText(this, "Opcion1", Toast.LENGTH_SHORT).show();
            Intent navegar = new Intent(this, MainActivity.class);
            startActivity(navegar);
            return true;
        }else if(id == R.id.item2){
            //Toast.makeText(this, "Opcion2", Toast.LENGTH_SHORT).show();
            Intent navegar2 = new Intent(this, Main2ActivityPics.class);
            startActivity(navegar2);
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


    //Método para grabar audio
    public void Recorder(View view){
        //si es null es que no se está grabando nada
        if(grabacion == null){

            //Esta linea es para definir el lugar donde se va a guardar el archivo
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            //aqui definimos un objeto de la clase media recorder y el formato de salida en este caso 3gpp
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //Esto es para decodificar el audio grabado previamente
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
           //Definir el archiv de salida
            grabacion.setOutputFile(archivoSalida);
            //usamos un try catch por si hay algun error
            try{
                //Comenzar a grabar
                grabacion.prepare();
                grabacion.start();
                botonrecord.setBackgroundResource(R.drawable.rec);
            }catch(IOException e){
            }
            Toast.makeText(this,"Grabando", Toast.LENGTH_SHORT).show();
        }else if(grabacion != null){
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            botonrecord.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(getApplicationContext(), "Grabación finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    public void Reroducir(View view){
        MediaPlayer media = new MediaPlayer();
        try{
            media.setDataSource(archivoSalida);
            media.prepare();
        }catch(IOException e){
        }
        media.start();
        Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
    }


    public void goPics(View view){
        Intent navegarpics = new Intent(this, Main2ActivityPics.class);
        startActivity(navegarpics);
    }

    public void goVideos(View view){
        Intent navegarvids = new Intent(this, Main3ActivityVideo.class);
        startActivity(navegarvids);
    }



}
