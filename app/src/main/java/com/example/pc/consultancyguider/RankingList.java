package com.example.pc.consultancyguider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class RankingList extends AppCompatActivity {
    RecyclerView recyclerView;
    RankAdapter adapter;
    List<RankModel> rankList;
    String rank_url="http://192.168.137.1/ranking.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_list);
        rankList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RankingList.this));
        adapter=new RankAdapter(this,rankList);

        loaddata();


      /* rankList.add(
                new RankModel(
                        "queen",
                        "90"


        ));
        rankList.add(
                new RankModel(
                        "kingeen",
                        "70"


                ));



        adapter=new RankAdapter(RankingList.this,rankList);
        recyclerView.setAdapter(adapter);*/
    }
    private void loaddata()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, rank_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray products=new JSONArray(response);

                    for(int i=products.length()-1;i>=0;i--)
                    {
                        JSONObject jsonObject=products.getJSONObject(i);
                        String cname=jsonObject.getString("cname");
                        String point=jsonObject.getString("point");


                        RankModel rankModel=new RankModel(cname,point);
                        rankList.add(rankModel);

                    }
                    adapter=new RankAdapter(RankingList.this,rankList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RankingList.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);


    }
}
