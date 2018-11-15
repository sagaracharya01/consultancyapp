package com.example.pc.consultancyguider;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
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

public class RatingList extends AppCompatActivity {
    RatingBar kingrate,gracerate,alpharate,amazingrate,globalrate;
    Button submit;
    AlertDialog.Builder builder;
    String rate_url="http://192.168.137.1/rating.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        kingrate=findViewById(R.id.kingratebar);
        gracerate=findViewById(R.id.graceratebar);
        alpharate=findViewById(R.id.alpharatebar);
        amazingrate=findViewById(R.id.amazingratebar);
        globalrate=findViewById(R.id.globalratebar);
        submit=findViewById(R.id.ratesubmit);
        builder=new AlertDialog.Builder(RatingList.this);

        kingrate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
               int rate=(int)rating;

                Toast.makeText(RatingList.this, "rating"+rate, Toast.LENGTH_SHORT).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getIntent();
                final String uname=i.getStringExtra("name");
                 int kingvalue=(int)kingrate.getRating();
                 int gracevalue=(int)gracerate.getRating();
                int alphavalue=(int)alpharate.getRating();
                int amazingvalue=(int)amazingrate.getRating();
                int globalvalue=(int)globalrate.getRating();

                // .....integer to string
                final String kingrated=Integer.toString(kingvalue);
                final String gracerated=Integer.toString(gracevalue);
                final String alpharated=Integer.toString(alphavalue);
                final String amazingrated=Integer.toString(amazingvalue);
               final  String globalrated=Integer.toString(globalvalue);

                StringRequest stringRequest=new StringRequest(Request.Method.POST,
                        rate_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                JSONArray jsonArray;

                                try {
                                    jsonArray = new JSONArray(response);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    String code=jsonObject.getString("code");
                                    String message=jsonObject.getString("message");
                                   // Toast.makeText(RatingList.this, message, Toast.LENGTH_SHORT).show();
                                    builder.setTitle("Server response");
                                    builder.setMessage(message);
                                    displayAlert(code);
                                } catch (JSONException e) {
                                    Toast.makeText(RatingList.this, "Exception", Toast.LENGTH_SHORT).show();

                                    e.printStackTrace();

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RatingList.this, "INTERNET PROBLEM", Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("name",uname);
                        params.put("kings",kingrated);
                        params.put("grace",gracerated);
                        params.put("alpha",alpharated);
                        params.put("amazing",amazingrated);
                        params.put("global",globalrated);
                        return params;
                    }
                };
                MySingleton.getInstance(RatingList.this).addToRequestQueue(stringRequest);

            }
        });


    }
    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("Upload_success"))
                {

                    Intent intent=new Intent(RatingList.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else if (code.equals("reg_fail")){
                    Toast.makeText(RatingList.this, "input error lalala", Toast.LENGTH_SHORT).show();
                }


            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
