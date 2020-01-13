package com.example.microwave;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class functionsActivity extends AppCompatActivity {

    private TextView textView;
    private SeekBar seekBar;
    private ImageView warming;
    String startToast = "Your countdown timer just started!";

    String pauseToast = "Your countdown timer is paused!";

    //private ProgressBar progressBar;
    private Button countDownButton;
    private TextView time;

    private CountDownTimer timer;
    private long timeLeftInMillis=0;
    private boolean timeRunning;
    private TextView headline;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);

        textView = (TextView) findViewById(R.id.textView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(""+ progress*100+" Watt");
                if (progress!=0){
                    timeLeftInMillis=((10-progress)*30000)+60000;
                } else {
                    timeLeftInMillis=0;
                }

                time=(TextView) findViewById(R.id.time);
                updateTimer();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Intent intent=getIntent();

        String meal=intent.getStringExtra("MEAL");

        //This is where we seperate what is shown based on what meal we selected.

        if (meal.equals("pie")) {
            headline=(TextView) findViewById(R.id.headline);
            headline.setText("You are about to warm some pie!");
            headline.setVisibility(1);
            seekBar.setMax(7);

        } else if(meal.equals("meat")) {
            headline=(TextView) findViewById(R.id.headline);
            headline.setText("You are about to warm some meat!");
            headline.setVisibility(1);
            seekBar.setMax(10);

        } else if(meal.equals("popcorn")) {
            headline=(TextView) findViewById(R.id.headline);
            headline.setText("You are about to make some popcorn!");
            headline.setVisibility(1);
            seekBar.setMax(4);

        } else if(meal.equals("soup")) {
            headline=(TextView) findViewById(R.id.headline);
            headline.setText("You are about to warm some soup!");
            headline.setVisibility(1);
            seekBar.setMax(5);

        }

        //progressBar=(ProgressBar) findViewById(R.id.progressBar);
        time=(TextView) findViewById(R.id.time);
        warming=(ImageView) findViewById(R.id.warming);

        countDownButton=(Button) findViewById(R.id.countdown_button);
        countDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        updateTimer();


    }

    @SuppressLint("WrongConstant")
    public void startStop() {
        if(timeRunning) {
            warming.setVisibility(warming.INVISIBLE);
            Toast.makeText(getApplicationContext(),pauseToast,Toast.LENGTH_LONG).show();
            stopTimeCountdown();
        }else {
            warming.setVisibility(warming.VISIBLE);
            Toast.makeText(getApplicationContext(),startToast,Toast.LENGTH_LONG).show();
            startTimeCountdown();
        }
    }


    public void startTimeCountdown () {
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.sound);
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                sound.start();
                openDialog();

            }
        }.start();
        countDownButton.setText("PAUSE");
        timeRunning = true;
    }

    private void stopTimeCountdown() {
        timer.cancel();
        countDownButton.setText("START");
        timeRunning = false;
    }

    private void updateTimer() {
        int minutes = (int) timeLeftInMillis/60000;
        int seconds = (int) timeLeftInMillis % 60000 /1000;

        String timeLeft;

        timeLeft = "" + minutes;
        timeLeft +=  ":";

         if (seconds<10)
             timeLeft += "0" + seconds;
         else
             timeLeft += seconds;

         time.setText(timeLeft);
    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }


}
