package com.example.pc.consultancyguider;

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

public class StatusRecycle extends AppCompatActivity {
    Button btnview;
    String usertransfer;
    RecyclerView recyclerView;
    ViewerAdapter adapter;
    List<ViewerModel> viewerList;
    String viewer_url="http://192.168.137.1/viewerreturn.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_recycle);
        btnview=findViewById(R.id.btnview);
        viewerList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(StatusRecycle.this));
        adapter=new ViewerAdapter(StatusRecycle.this,viewerList);
        loaddata();

        Intent i=getIntent();
        usertransfer=i.getStringExtra("data");

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StatusRecycle.this,ViewPoster.class);
                intent.putExtra("realname",usertransfer);
                startActivity(intent);
            }
        });
    }
    private void loaddata()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, viewer_url, new Response.Listener<String>() {
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
                        String imgurl="http://192.168.137.1/uploads/"+subject+".jpg";

                        ViewerModel viewerModel=new ViewerModel(name,subject,desc,imgurl);
                        viewerList.add(viewerModel);


                    }
                    adapter=new ViewerAdapter(StatusRecycle.this,viewerList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(StatusRecycle.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);


    }
}
