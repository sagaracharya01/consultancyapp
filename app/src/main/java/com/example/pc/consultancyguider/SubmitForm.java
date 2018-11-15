package com.example.pc.consultancyguider;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class SubmitForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText edtname,edtaddress,edtemail,edtphone,edtgender,edteducation,edtconsult;
    CardView submit;
    AlertDialog.Builder builder;
    String name,address,email,phone,gender,education,consultancy;
    String form_url="http://192.168.137.1/form.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);

        edtname=findViewById(R.id.edtname);
        edtaddress=findViewById(R.id.edtaddress);
        edtemail=findViewById(R.id.edtemail);
        edtphone=findViewById(R.id.edtphone);
        edtgender=findViewById(R.id.edtgender);
        edteducation=findViewById(R.id.edteducation);
        edtconsult=findViewById(R.id.edtconsult);
        submit=(CardView)findViewById(R.id.submit);
        builder=new AlertDialog.Builder(SubmitForm.this);


/*
        final Spinner spinner=findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(SubmitForm.this,R.array.consultname,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=edtname.getText().toString();
                address=edtaddress.getText().toString();
                email=edtemail.getText().toString();
                phone=edtphone.getText().toString();
                gender=edtgender.getText().toString();
                education=edteducation.getText().toString();
                consultancy=edtconsult.getText().toString();

                if(name.equals("")||address.equals("")||email.equals("")||phone.equals("")||gender.equals("")||education.equals(""))
                {

                    builder.setTitle("DATA INCOMPLETE");
                    builder.setMessage("Please fill the fields");
                    displayAlert("input_error");

                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST,form_url,
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
                                        Toast.makeText(SubmitForm.this, "Exception", Toast.LENGTH_SHORT).show();

                                        e.printStackTrace();

                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SubmitForm.this, "INTERNET PROBLEM", Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("name",name);
                            params.put("address",address);
                            params.put("email",email);
                            params.put("phone",phone);
                            params.put("gender",gender);
                            params.put("education",education);
                            params.put("consultancy",consultancy);


                            return params;
                        }
                    };
                    MySingleton.getInstance(SubmitForm.this).addToRequestQueue(stringRequest);


                }



            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")){
                    Toast.makeText(SubmitForm.this, "Fill all the data", Toast.LENGTH_SHORT).show();
                }
                else if (code.equals("Upload success"))
                {

                    Intent intent=new Intent(SubmitForm.this,MainActivity.class);

                    startActivity(intent);
                    finish();

                }

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


}
