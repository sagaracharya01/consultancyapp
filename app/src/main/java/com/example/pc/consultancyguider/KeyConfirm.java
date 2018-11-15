package com.example.pc.consultancyguider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KeyConfirm extends AppCompatActivity {
    EditText consult,keyvalue;
    Button confirmbtn;
   String consultstrg,keystring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_confirm);
        consult=(EditText)findViewById(R.id.consultname);
        keyvalue=findViewById(R.id.key);
        confirmbtn=findViewById(R.id.postnews);

       // consultstring=consult.getText().toString();
      //  keystring=keyvalue.getText().toString();
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               consultstrg=consult.getText().toString();
                keystring=keyvalue.getText().toString();

                if (consultstrg.equals("Kings")&&keystring.equals("kings123")){

                    Toast.makeText(KeyConfirm.this, "Now you can post KINGS CONSULTANCY", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(KeyConfirm.this,NewsPost.class);
                    intent.putExtra("extra",consultstrg);
                    startActivity(intent);
                    finish();

                }
                else if(consultstrg.equals("GlobelReach")&&keystring.equals("globel123")){
                    Toast.makeText(KeyConfirm.this, "Now you can post GLOBAL REACH", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(KeyConfirm.this,NewsPost.class);
                    intent.putExtra("extra",consultstrg);
                    startActivity(intent);
                    finish();

                }
                else if(consultstrg.equals("Grace")&&keystring.equals("grace123")){
                    Toast.makeText(KeyConfirm.this, "Now you can post GRACE INTERNATIONAL", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(KeyConfirm.this,NewsPost.class);
                    intent.putExtra("extra",consultstrg);
                    startActivity(intent);
                    finish();

                }
                else if (consultstrg.equals("Amazing")&&keystring.equals("amazing123")){
                    Toast.makeText(KeyConfirm.this, "Now you can post ALPHABETA CONSULTANCY", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(KeyConfirm.this,NewsPost.class);
                    intent.putExtra("extra",consultstrg);
                    startActivity(intent);
                    finish();
                }
                else if (consultstrg.equals("Alphabeta")&&keystring.equals("alpha123")){
                    Toast.makeText(KeyConfirm.this, "Now you can post ALPHABETA CONSULTANCY", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(KeyConfirm.this,NewsPost.class);
                    intent.putExtra("extra",consultstrg);
                    startActivity(intent);
                    finish();
                }

                else{
                    Toast.makeText(KeyConfirm.this, "Invalid  name and password", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
