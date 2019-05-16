package com.example.a40126571.project200;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Strings extends AppCompatActivity {

    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4, sound5, sound6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strings);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        sound1 = soundPool.load(this, R.raw.e_l_string, 1);
        sound2 = soundPool.load(this, R.raw.a_string, 1);
        sound3 = soundPool.load(this, R.raw.d_string, 1);
        sound4 = soundPool.load(this, R.raw.g_string, 1);
        sound5 = soundPool.load(this, R.raw.b_string, 1);
        sound6 = soundPool.load(this, R.raw.e_h_string, 1);
    }

    public void playSound(View v) {
        switch(v.getId()){
            case R.id.btnStringEl:
                soundPool.play(sound1, 1,1,0, 0,1);
                Toast.makeText(Strings.this, "Playing string...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnStringA:
                soundPool.play(sound2, 1,1,0, 0,1);
                Toast.makeText(Strings.this, "Playing string...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnStringD:
                soundPool.play(sound3, 1,1,0, 0,1);
                Toast.makeText(Strings.this, "Playing string...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnStringG:
                soundPool.play(sound4, 1,1,0, 0,1);
                Toast.makeText(Strings.this, "Playing string...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnStringB:
                soundPool.play(sound5, 1,1,0, 0,1);
                Toast.makeText(Strings.this, "Playing string...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnStringEh:
                soundPool.play(sound6, 1,1,0, 0,1);
                Toast.makeText(Strings.this, "Playing string...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
