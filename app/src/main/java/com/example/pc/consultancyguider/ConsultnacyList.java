package com.example.pc.consultancyguider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ConsultnacyList extends AppCompatActivity {

    RelativeLayout firstlayout,secondlayout,thirdlayout,fourthlayout,fifthlayout;
    String Viewname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultnacy_list);
        Intent i=getIntent();
        Viewname=i.getStringExtra("name");
        firstlayout=findViewById(R.id.first);
        firstlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConsultnacyList.this,KingsConsultancy.class);
                intent.putExtra("name",Viewname);
                startActivity(intent);
            }
        });
        secondlayout=findViewById(R.id.second);
        secondlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConsultnacyList.this,GraceConsultancy.class);
                intent.putExtra("name",Viewname);
                startActivity(intent);
            }
        });
        thirdlayout=findViewById(R.id.third);
        thirdlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConsultnacyList.this,GlobalConsultancy.class);
                intent.putExtra("name",Viewname);
                startActivity(intent);
            }
        });
        fourthlayout=findViewById(R.id.fourth);
        fourthlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConsultnacyList.this,AmazingConsultancy.class);
                intent.putExtra("name",Viewname);
                startActivity(intent);
            }
        });
        fifthlayout=findViewById(R.id.fifth);
        fifthlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConsultnacyList.this,AlphabetaConsultancy.class);
                intent.putExtra("name",Viewname);
                startActivity(intent);
            }
        });
    }
}
