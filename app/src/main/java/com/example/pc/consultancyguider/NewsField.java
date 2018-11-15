package com.example.pc.consultancyguider;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsField extends AppCompatActivity {
    RecyclerView recyclerView;
    ConsultAdapter adapter;
    List<ConsultModel> consultModelList;
    String notify_url="http://192.168.137.1/notify.php";

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_field);
        button=findViewById(R.id.postrecycle);
        consultModelList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
       // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewsField.this));
        adapter=new ConsultAdapter(this,consultModelList);


        loaddata();



       /* consultModelList.add(
                new ConsultModel(
                        "queen",
                        "admission lala open",
                        "Kings has lanch new admission for " +
                                "student for aborad study"

        ));
        consultModelList.add(
                new ConsultModel(
                        "kingeen",
                        "gate open",
                        "Kings has lanch new admission for " +
                                "student for aborad study"

                ));*/



       /* adapter=new ConsultAdapter(NewsField.this,consultModelList);
        recyclerView.setAdapter(adapter);*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewsField.this,KeyConfirm.class);
                startActivity(intent);
            }
        });
    }
    private void loaddata()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, notify_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray products=new JSONArray(response);

                    for(int i=products.length()-1;i>=0;i--)
                    {
                        JSONObject jsonObject=products.getJSONObject(i);
                        String name=jsonObject.getString("name");
                        String subject=jsonObject.getString("subject");
                        String desc=jsonObject.getString("description");

                        ConsultModel consultModel=new ConsultModel(name,subject,desc);
                        consultModelList.add(consultModel);

                    }
                    adapter=new ConsultAdapter(NewsField.this,consultModelList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(NewsField.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);


    }
    }
