package com.example.pc.consultancyguider;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewsPost extends AppCompatActivity {
    EditText edituser,editsubject,editdesc;
    TextView txtconc;
    Button btnpost;
    public String consultname,subject,description;
    AlertDialog.Builder builder;
    String reg_url="http://192.168.137.1/news.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_post);
        edituser=findViewById(R.id.edituser);
        editsubject=findViewById(R.id.editsubject);
        editdesc=findViewById(R.id.editdesc);
        btnpost=findViewById(R.id.btnpostdata);
        txtconc=findViewById(R.id.txtconc);
        builder=new AlertDialog.Builder(NewsPost.this);
        Intent i=getIntent();
        consultname=i.getStringExtra("extra");

        txtconc.setText(consultname+" "+"Consultancy");

        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                subject=editsubject.getText().toString();
                description=editdesc.getText().toString();


                if(consultname.equals("")||subject.equals("")||description.equals(""))
                {

                    builder.setTitle("DATA INCOMPLETE");
                    builder.setMessage("Please fill the fields");
                    displayAlert("input_error");

                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST,reg_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JSONArray jsonArray;
                                    try {
                                        jsonArray = new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code=jsonObject.getString("code");
                                        String message=jsonObject.getString("message");
                                        builder.setTitle("Server response");
                                        builder.setMessage(message);
                                        displayAlert(code);
                                    } catch (JSONException e) {
                                        Toast.makeText(NewsPost.this, "Exception", Toast.LENGTH_SHORT).show();

                                        e.printStackTrace();

                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(NewsPost.this, "INTERNET PROBLEM", Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("name",consultname+" "+"Consultancy");
                            params.put("subject",subject);
                            params.put("desc",description);

                            return params;
                        }
                    };
                    MySingleton.getInstance(NewsPost.this).addToRequestQueue(stringRequest);


                }

            }
        });




    }
    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")){
                    Toast.makeText(NewsPost.this, "Fill all the data", Toast.LENGTH_SHORT).show();
                }
                else if (code.equals("Upload success"))
                {

                    Intent intent=new Intent(NewsPost.this,NewsField.class);

                    startActivity(intent);
                    finish();

                }

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
