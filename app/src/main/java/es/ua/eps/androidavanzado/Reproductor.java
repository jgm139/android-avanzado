package es.ua.eps.androidavanzado;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Reproductor extends AppCompatActivity {
    private ImageButton button_play_pause;
    private ImageButton button_stop;
    private ImageButton button_back;
    private ImageButton button_forward;

    private MediaPlayer mPlayer;
    private ServiceConnection mServiceConn;
    private boolean mIsBound = false;
    private boolean play = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        button_play_pause = findViewById(R.id.button_play_pause);
        button_stop = findViewById(R.id.button_stop);
        button_back = findViewById(R.id.button_back);
        button_forward = findViewById(R.id.button_forward);

        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mPlayer = ((MediaPlayer.MyBinder) service).getService();
                mIsBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mPlayer = null;
                mIsBound = false;
            }
        };

        Intent intent = new Intent(Reproductor.this, MediaPlayer.class);
        startService(intent);
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);

        button_play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play) {
                    mPlayer.play();
                    play = false;
                } else {
                    mPlayer.pause();
                    play = true;
                }
            }
        });

        button_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.forward();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.go_back();
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
                play = true;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mIsBound) {
            unbindService(mServiceConn);
            mIsBound = false;
        }
    }
}
