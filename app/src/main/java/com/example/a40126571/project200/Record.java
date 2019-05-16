package com.example.a40126571.project200;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Record extends AppCompatActivity {

    // Declare variables
    Button btnStartRecord;
    Button btnStopRecord;
    Button btnPlay;
    Button btnStop;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    final int REQUEST_PERMISSION_CODE = 1000;




    // This method begins when a user begins the record activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // Request Run-time permission
        if(!checkPermissionFromDevice())
            requestPermission();


        //Init view
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnStartRecord = (Button)findViewById(R.id.btnStartRecord);
        btnStopRecord = (Button)findViewById(R.id.btnStopRecord);


            btnStartRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    if(checkPermissionFromDevice()){

                        pathSave = Environment.getExternalStorageDirectory()
                                .getAbsolutePath()+"/"
                                +UUID.randomUUID().toString()+"_audio_record.3gp";

                        setUpMediaRecorder();
                        try{
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        btnPlay.setEnabled(false);
                        btnStop.setEnabled(false);

                        //Including the below to attempt to solve the issue of "stop recording" button not being enabled on second record.
                        btnStopRecord.setEnabled(true);

                        Toast.makeText(Record.this, "Recording...", Toast.LENGTH_SHORT).show();

                    } else {
                        requestPermission();
                    }

                }
            });

            btnStopRecord.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mediaRecorder.stop();
                    btnStopRecord.setEnabled(false);
                    btnPlay.setEnabled(true);
                    btnStartRecord.setEnabled(true);
                    btnStop.setEnabled(false);
                }
            });

            btnPlay.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    btnStop.setEnabled(true);
                    btnStopRecord.setEnabled(false);
                    btnStartRecord.setEnabled(false);

                    mediaPlayer = new MediaPlayer();
                    try{
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();

                    } catch (IOException e){
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(Record.this, "Playing...", Toast.LENGTH_SHORT).show();
                }
            });

            btnStop.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    btnStopRecord.setEnabled(false);
                    btnStartRecord.setEnabled(true);
                    btnStop.setEnabled(false);
                    btnPlay.setEnabled(true);

                    if (mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        setUpMediaRecorder();
                    }
                }

            });

    }

    private void setUpMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{

                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO

        }, REQUEST_PERMISSION_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
                break;


        }
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;

    }
}
