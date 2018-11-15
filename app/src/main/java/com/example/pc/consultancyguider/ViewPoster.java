package com.example.pc.consultancyguider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
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

public class ViewPoster extends AppCompatActivity {
    ImageView imageView;
    Button btnview;
    EditText Viewer,Subject,Desc;
    AlertDialog.Builder builder;
    String viewer,subject,desc;
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;
    String view_url="http://192.168.137.1/viewer.php";
    String datataken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poster);
        imageView=findViewById(R.id.viewimage);
        Viewer=findViewById(R.id.viewerid);
        Subject=findViewById(R.id.editsub);
        btnview=findViewById(R.id.btnview);
        Desc=findViewById(R.id.editdescp);
        builder=new AlertDialog.Builder(this);

        Intent i=getIntent();
        datataken=i.getStringExtra("realname");

        Viewer.setText(datataken);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewPoster.this, "imagetest", Toast.LENGTH_SHORT).show();
                imageupload();

            }
        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewer=Viewer.getText().toString();
                subject=Subject.getText().toString();
                desc=Desc.getText().toString();
                if(subject.equals("")||desc.equals(""))
                {

                    builder.setTitle("DATA INCOMPLETE");
                    builder.setMessage("Please fill the fields");
                    displayAlert("input_error");

                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST,view_url,
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
                                        Toast.makeText(ViewPoster.this, "Exception", Toast.LENGTH_SHORT).show();

                                        e.printStackTrace();

                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ViewPoster.this, "INTERNET PROBLEM", Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("viewer",viewer);
                            params.put("subject",subject);
                            params.put("desc",desc);

                            //new code
                            params.put("image",imagetostring(bitmap));
                            return params;
                        }
                    };
                    MySingleton.getInstance(ViewPoster.this).addToRequestQueue(stringRequest);


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
                    Viewer.setText("");
                    Subject.setText("");
                    Desc.setText("");

                }
                else if (code.equals("reg_success"))
                {

                    Intent intent=new Intent(ViewPoster.this,StatusRecycle.class);
                    //intent.putExtra("name",name);
                    startActivity(intent);
                    finish();

                }
                else if (code.equals("reg_fail")){
                    Viewer.setText("");
                    Subject.setText("");
                    Desc.setText("");
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
