package com.example.asus.wook3.ui.satin;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.asus.wook3.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPause, btnPlayUrl, btnStop,btnReplay;
    private SeekBar skbProgress;
    private Player player;
    private SimpleDraweeView sim33;
    private ObjectAnimator discAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        TelephonyManager telephonyManager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);

        skbProgress = (SeekBar) findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());

        String url="http://sc1.111ttt.cn:8282/2018/1/03m/13/396131229550.m4a?tflag=1519095601&pin=6cd414115fdb9a950d827487b16b5f97#.mp3";
        player = new Player(url,skbProgress);

        btnPlayUrl = (Button) findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(this);

        btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);

        btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener( this);

        btnReplay = (Button) findViewById(R.id.btnReplay);
        btnReplay.setOnClickListener( this);
        sim33 = (SimpleDraweeView) findViewById(R.id.sim33);
        sim33.setImageURI("http://image.tianjimedia.com/uploadImages/2013/220/7UWSCYL47P3P.jpg");
        discAnimation = ObjectAnimator.ofFloat(sim33, "rotation", 0, 360*3f);
//        int ss = player.ss();
        discAnimation.setDuration(180000);
        discAnimation.setInterpolator(new LinearInterpolator());
        discAnimation.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlayUrl:
                player.play();
                discAnimation.start();
                break;
            case R.id.btnPause:
                 player.pause();
                 discAnimation.cancel();
                break;
            case R.id.btnStop:
                player.stop();
                discAnimation.end();
                break;
            case R.id.btnReplay:
                player.play();
                discAnimation.start();
                break;

        }
    }

    /**
     * 只有电话来了之后才暂停音乐的播放
     */
    private final class MyPhoneListener extends android.telephony.PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING://电话来了
                    player.callIsComing();
                    break;
                case TelephonyManager.CALL_STATE_IDLE: //通话结束
                    player.callIsDown();
                    break;
            }
        }
    }



    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }
}