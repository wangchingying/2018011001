package com.cyw.a2018011001;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        tv=(TextView)findViewById(R.id.textView);
        MyThread t=new MyThread();
        t.start();
    }

    //起一個執行緒,只要true就停一秒, 然後交給主執行緒+1
    //主執行緒一直在run, 不會受MyThread 影響, 所以seekbar可以一直移動不會lag
    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(String.valueOf(Integer.valueOf(tv.getText().toString())+1));
                    }
                });
            }
        }
    }
}
