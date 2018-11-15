package com.example.pc.consultancyguider;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=3000;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView=findViewById(R.id.namedisplay);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Welcome.this, MainActivity.class);
                Intent intent1 = getIntent();
                Bundle bd = intent1.getExtras();
                if(bd != null)
                {
                    String getName = (String) bd.get("name");
                    textView.setText(getName);
                }
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);

    }
}
