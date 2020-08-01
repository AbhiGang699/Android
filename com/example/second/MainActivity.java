package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer s1;
    int a,b,c,d,e,f,g,h;
     public void paus(View v){
        TextView t=(TextView)v;
        String str=t.getText().toString();
        if(str=="Pause"){
            s1.pause();
            t.setText("Resume");
        }
        else{
            t.setText("Pause");
            s1.start();
        }
    }
    public void play(View v){
        int id=v.getId();
        int str=getResources().getIdentifier (v.getResources().getResourceEntryName(id),"raw","com.example.second");
        //System.out.println(R.raw.);
        s1.pause();
        int temp=a;
        try {
            switch (v.getId()) {
                case R.id.b1:
                    temp=a;
                    break;
                case R.id.b2:
                    temp=b;
                    break;
                case R.id.b3:
                    temp=c;
                    break;
                case R.id.b4:
                    temp=d;
                    break;
                case R.id.b5:
                    temp=e;
                    break;
                case R.id.b6:
                    temp=f;
                    break;
                case R.id.b7:
                    temp=g;
                    break;
                case R.id.b8:
                    temp=h;
                    break;
            }
            TextView t=(TextView)findViewById(R.id.button);
            t.setText("Pause");

            s1=MediaPlayer.create(this,temp);
            s1.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=R.raw.s1;b=R.raw.s2;c=R.raw.s3;d=R.raw.s4;e=R.raw.s5;f=R.raw.s6;g=R.raw.s7;h=R.raw.s8;
        s1=MediaPlayer.create(this,a);
        final AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
        final SeekBar sb=(SeekBar)findViewById(R.id.seekBar);
        sb.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sb.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
            }
        },0,100);
        final SeekBar sb2=(SeekBar)findViewById(R.id.seekBar2);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int k=s1.getCurrentPosition();
                sb2.setProgress(k*100/s1.getDuration());

            }
        },0,100);
        sb2.setMax(100);
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                s1.seekTo(progress*s1.getDuration()/100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                s1.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                s1.start();
            }
        });
    }
}
