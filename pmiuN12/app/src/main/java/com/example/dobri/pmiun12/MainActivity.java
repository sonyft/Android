package com.example.dobri.pmiun12;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uiHandler = new Handler();
    }

    public void onClick(View view) {
        new Thread(new calcThread()).start();
        Button btn = (Button) findViewById(R.id.button);
        btn.setEnabled(false);
    }

    public void changeColor(View view) {
        new Thread(new trafficLightThread()).start();
    }

    protected class calcThread implements Runnable{
        @Override
        public void run() {
            Random rnd = new Random();
            for(int i = 1; i <=3; i++){
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(rnd.nextInt(10) > 5){
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView txt = findViewById(R.id.textView1);
                            txt.setText("OK");
                            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
                        }
                    });
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Button btn = (Button) findViewById(R.id.button);
                            btn.setEnabled(true);
                        }
                    });
                    return;
                }
            }
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    TextView txt = findViewById(R.id.textView1);
                    txt.setText("Failed");

                    Button btn = (Button) findViewById(R.id.button);
                    btn.setEnabled(true);
                }
            });
        }
    }

    protected class trafficLightThread implements Runnable{
        Integer way = 1;
        Integer counter = 1;
        @Override
        public void run() {
            while(true){
                uiHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        Button btn2 = (Button) findViewById(R.id.button2);
                        btn2.setEnabled(false);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Button btn2 = (Button) findViewById(R.id.button2);
                        btn2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Button btn2 = (Button) findViewById(R.id.button2);
                        btn2.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Button btn2 = (Button) findViewById(R.id.button2);
                        btn2.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                });
            }
        }
    }
}
