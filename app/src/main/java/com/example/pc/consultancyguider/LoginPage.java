package com.example.pc.consultancyguider;

import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
    CardView cardView;
    TextView txtregister;
    EditText Username,Password;
    String username,password;
    String login_url="http://192.168.137.1/login.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Username=findViewById(R.id.usernameid);
        Password=findViewById(R.id.passid);
        builder=new AlertDialog.Builder(this);
        cardView=findViewById(R.id.loginbtnid);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=Username.getText().toString();
                password=Password.getText().toString();

                if(username.equals("")||password.equals(""))
                {

                    builder.setTitle("Input error");
                    displayAlert("Enter a valid username and password");

                }

                else{
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code=jsonObject.getString("code");


                                         if (code.equals("login_fail")){
                                            builder.setTitle("Login error");
                                            displayAlert(jsonObject.getString("message"));

                                        }
                                        else {
                                            User user=new User(LoginPage.this);
                                            user.setName(username);
                                            Intent intent=new Intent(LoginPage.this,MainActivity.class);
                                            intent.putExtra("Vname",username);
                                          //  Bundle bundle=new Bundle();
                                          //  bundle.putString("name",jsonObject.getString("name"));
                                           // bundle.putString("password",jsonObject.getString("password"));
                                         //   intent.putExtras(bundle);

                                            startActivity(intent);
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginPage.this, "INTERNET PROBLEM", Toast.LENGTH_SHORT).show();


                        }

                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("username",username);
                            params.put("password",password);
                            return params;
                        }
                    };
                    MySingleton.getInstance(LoginPage.this).addToRequestQueue(stringRequest);

                }

            }
        });
        txtregister=findViewById(R.id.registerid);
        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginPage.this,RegisterPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void displayAlert(final String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
           public void onClick(DialogInterface dialog, int which) {

           }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
