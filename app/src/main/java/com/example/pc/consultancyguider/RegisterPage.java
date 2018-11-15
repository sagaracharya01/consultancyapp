package com.example.pc.consultancyguider;

import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {
    CardView cardView;
    ImageView imageView;
    EditText Name,Username,Password,Email;
    AlertDialog.Builder builder;
    String name,username,password,email;
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;
    String reg_url="http://192.168.137.1/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register_page);
        super.onCreate(savedInstanceState);
        imageView=findViewById(R.id.regphotoid);
        Name=findViewById(R.id.regname);
        Username=findViewById(R.id.reguser);
        Password=findViewById(R.id.regpass);
        Email=findViewById(R.id.regemail);
        builder=new AlertDialog.Builder(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterPage.this, "imagetest", Toast.LENGTH_SHORT).show();
                imageupload();

            }
        });


        cardView=findViewById(R.id.registerbtn);

        //.............CARDVIEW OPERATION AS BUTTON................

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=Name.getText().toString();
                username=Username.getText().toString();
                password=Password.getText().toString();
                email=Email.getText().toString();
                if(name.equals("")||username.equals("")||password.equals("")||email.equals(""))
                {

                    builder.setTitle("DATA INCOMPLETE");
                    builder.setMessage("Please fill all the fields");
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

                                    //new code

                                    jsonArray = new JSONArray(response);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    String code=jsonObject.getString("code");
                                    String message=jsonObject.getString("message");
                                    builder.setTitle("Server response");
                                    builder.setMessage(message);
                                    displayAlert(code);
                                } catch (JSONException e) {
                                    Toast.makeText(RegisterPage.this, "Exception", Toast.LENGTH_SHORT).show();

                                    e.printStackTrace();

                            }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterPage.this, "INTERNET PROBLEM", Toast.LENGTH_SHORT).show();

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String>params=new HashMap<String,String>();
                                params.put("name",name);
                                params.put("username",username);
                                params.put("password",password);
                                params.put("email",email);

                                //new code
                                params.put("image",imagetostring(bitmap));
                                return params;
                            }
                        };
                        MySingleton.getInstance(RegisterPage.this).addToRequestQueue(stringRequest);


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
                    Name.setText("");
                    Username.setText("");
                    Password.setText("");
                    Email.setText("");
                }
                else if (code.equals("reg_success"))
                {

                    Intent intent=new Intent(RegisterPage.this,Welcome.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                    finish();

                }
                else if (code.equals("reg_fail")){
                    Name.setText("");
                    Username.setText("");
                    Password.setText("");
                    Email.setText("");
                }
            }
        });
        AlertDialog alertDialog=builder.create();
       alertDialog.show();
    }
    private void imageupload()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMG_REQUEST&&resultCode==RESULT_OK&&data!=null){
            Uri path=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private String imagetostring(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
       byte[] imgBytes= byteArrayOutputStream.toByteArray();
       return Base64.encodeToString(imgBytes,Base64.DEFAULT);

    }
}
