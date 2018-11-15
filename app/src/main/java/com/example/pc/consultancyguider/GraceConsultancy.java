package com.example.pc.consultancyguider;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class GraceConsultancy extends AppCompatActivity {
    RecyclerView recyclerView;
    KingsAdapter adapter;
    List<KingsModel> kingsList;



    public String description,name;
    AlertDialog.Builder builder;
    EditText edtdesc;
    Button button;
    String comment_url="http://192.168.137.1/commentone.php";
    String comupload_url="http://192.168.137.1/comuploadone.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grace_consultancy);
        button=findViewById(R.id.commentbtn);
        edtdesc=findViewById(R.id.commenthere);
        builder=new AlertDialog.Builder(GraceConsultancy.this);
        Intent i=getIntent();
        name=i.getStringExtra("name");
        kingsList=new ArrayList<>();

        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(GraceConsultancy.this));
        adapter=new KingsAdapter(this,kingsList);
        //loaddata();
        /*
             kingsList.add(
                new KingsModel(
                        "queen",
                        "admission lala open"

        ));
        kingsList.add(
                new KingsModel(
                        "KINGS",
                        "admission lala open"

                ));




       adapter=new KingsAdapter(KingsConsultancy.this,kingsList);
        recyclerView.setAdapter(adapter);
        */
        {
            StringRequest stringRequest=new StringRequest(Request.Method.GET, comupload_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray products=new JSONArray(response);

                        for(int i=0;i<products.length();i++)
                        {
                            JSONObject jsonObject=products.getJSONObject(i);
                            String name=jsonObject.getString("name");

                            String desc=jsonObject.getString("description");

                            KingsModel kingsModel=new KingsModel(name,desc);
                            kingsList.add(kingsModel);


                        }
                        adapter=new KingsAdapter(GraceConsultancy.this,kingsList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(GraceConsultancy.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            Volley.newRequestQueue(this).add(stringRequest);


        }




        //button click add........................

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                description=edtdesc.getText().toString();

                if(description.equals(""))
                {
                    Toast.makeText(GraceConsultancy.this, name, Toast.LENGTH_SHORT).show();

                    builder.setTitle("DATA INCOMPLETE");
                    builder.setMessage("Write something");
                    displayAlert("input_error");

                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST,comment_url,
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
                                        Toast.makeText(GraceConsultancy.this, "Exception", Toast.LENGTH_SHORT).show();

                                        e.printStackTrace();

                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(GraceConsultancy.this, "INTERNET PROBLEM", Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("name",name);
                            params.put("desc",description);

                            return params;
                        }
                    };
                    MySingleton.getInstance(GraceConsultancy.this).addToRequestQueue(stringRequest);


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
                  //  Toast.makeText(GraceConsultancy.this, "Fill all the data", Toast.LENGTH_SHORT).show();
                }
                else if (code.equals("Upload success"))
                {
                   // Toast.makeText(GraceConsultancy.this, "Successful", Toast.LENGTH_SHORT).show();



                }

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
