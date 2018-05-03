package com.example.android.trafficlight;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Handler handler;
    public ImageView imageView;
    public boolean state = false;
   // public int colorRed = Color.RED;
    public int colorGreen = Color.GREEN;
    public int colorYellow = Color.YELLOW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        imageView = findViewById(R.id.imageView);


        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new Thread(new Traff()).start();

             if ( state ) {
                    state = false;
                    button.setText("Start");
                } else {
                    state = true;
                    button.setText("Stop");
                }
            }
        });

    }//End of onCreate

    class Traff implements  java.lang.Runnable{


        @Override
        public void run() {
            while(state) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView = findViewById(R.id.imageView);
                        int colorRed = Color.RED;
                        imageView.setColorFilter(colorRed, PorterDuff.Mode.MULTIPLY);
                    }
                });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView = findViewById(R.id.imageView);
                        int colorRed = Color.YELLOW;
                        imageView.setColorFilter(colorRed, PorterDuff.Mode.MULTIPLY);
                    }
                });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView = findViewById(R.id.imageView);
                        int colorRed = Color.GREEN;
                        imageView.setColorFilter(colorRed, PorterDuff.Mode.MULTIPLY);
                    }
                });


            }//End of while true
        }//End of Run
    }//End of class Traff

}//End of Activity
