package com.example.iii_user.loading;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private UIHandler handler;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        handler = new UIHandler();
        timer = new Timer();
        timer.schedule(new task(),10,5000);
    }

    private class task extends TimerTask{
        @Override
        public void run() {
            new Thread() {
                @Override
                public void run() {
                    try {
                        MultipartUtility mu = new MultipartUtility(
                                "http://10.2.12.104/selectload01.php","UTF-8");
                        List<String> ret = mu.finish();
                        for (String line : ret){
                            Message msg = new Message();
                            Bundle b = new Bundle();
                            b.putString("pStr",line);
                            msg.setData(b);
                            handler.sendMessage(msg);
                            Log.i("Jack",line);
                        }
                    }catch (Exception e){
                        Log.i("Jack",e.toString());
                    }
                }
            }.start();
        }
    }
    private class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String line = msg.getData().getString("pStr");
            tv.setText(line);
        }
    }/*
@Override
public void finish() {
if (timer != null){
timer.cancel();
timer.purge();
timer = null;
}
super.finish();
}*/
}