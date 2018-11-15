package com.example.pc.consultancyguider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormList extends AppCompatActivity {
    RecyclerView recyclerView;
    FormAdapter adapter;
    List<FormModel> formList;
    String consultname;

    String form_url="http://192.168.137.1/formdisplay.php";
    String formgrace_url="http://192.168.137.1/formgrace.php";
    String formglobal_url="http://192.168.137.1/formglobal.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_list);
        formList=new ArrayList<>();
        Intent i=getIntent();
        consultname=i.getStringExtra("extra");

        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FormList.this));
        adapter=new FormAdapter(this,formList);
        Toast.makeText(this, consultname, Toast.LENGTH_SHORT).show();
        loaddata();



    }
    private void loaddata()
    {
        if (consultname.equals("Kings")) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, form_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray products = new JSONArray(response);

                        for (int i = products.length() - 1; i >= 0; i--) {
                            JSONObject jsonObject = products.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String address = jsonObject.getString("address");
                            String email = jsonObject.getString("email");
                            String phone = jsonObject.getString("phone");
                            String gender = jsonObject.getString("gender");
                            String education = jsonObject.getString("education");
                            String consultancy = jsonObject.getString("consultancy");

                            FormModel formModel = new FormModel(name, address, email, phone, gender, education, consultancy);
                            formList.add(formModel);

                        }
                        adapter = new FormAdapter(FormList.this, formList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(FormList.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            MySingleton.getInstance(FormList.this).addToRequestQueue(stringRequest);
        }
        else if (consultname.equals("Grace"))
        {
            StringRequest stringRequest=new StringRequest(Request.Method.GET, formgrace_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray products=new JSONArray(response);

                        for(int i=products.length()-1;i>=0;i--)
                        {
                            JSONObject jsonObject=products.getJSONObject(i);
                            String name=jsonObject.getString("name");
                            String address=jsonObject.getString("address");
                            String email=jsonObject.getString("email");
                            String phone=jsonObject.getString("phone");
                            String gender=jsonObject.getString("gender");
                            String education=jsonObject.getString("education");
                            String consultancy=jsonObject.getString("consultancy");

                            FormModel formModel=new FormModel(name,address,email,phone,gender,education,consultancy);
                            formList.add(formModel);

                        }
                        adapter=new FormAdapter(FormList.this,formList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(FormList.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            MySingleton.getInstance(FormList.this).addToRequestQueue(stringRequest);
        }
        else if (consultname.equals("Global"))
        {
            StringRequest stringRequest=new StringRequest(Request.Method.GET, formglobal_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray products=new JSONArray(response);

                        for(int i=products.length()-1;i>=0;i--)
                        {
                            JSONObject jsonObject=products.getJSONObject(i);
                            String name=jsonObject.getString("name");
                            String address=jsonObject.getString("address");
                            String email=jsonObject.getString("email");
                            String phone=jsonObject.getString("phone");
                            String gender=jsonObject.getString("gender");
                            String education=jsonObject.getString("education");
                            String consultancy=jsonObject.getString("consultancy");

                            FormModel formModel=new FormModel(name,address,email,phone,gender,education,consultancy);
                            formList.add(formModel);

                        }
                        adapter=new FormAdapter(FormList.this,formList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(FormList.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            MySingleton.getInstance(FormList.this).addToRequestQueue(stringRequest);
        }


        // MySingleton.getInstance(FormList.this).addToRequestQueue(stringRequest);
        //Volley.newRequestQueue(this).add(stringRequest);


    }
}
