package com.example.stores;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void get_request(View view)
        {
            final TextView textView = (TextView) findViewById(R.id.text);
            final EditText input = (EditText) findViewById(R.id.input);
            final EditText ip = (EditText) findViewById(R.id.ip);
            final EditText port_num = (EditText) findViewById(R.id.port);
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            String req = input.getText().toString();
            String ipadd = ip.getText().toString();
            String port_val = port_num.getText().toString();

            String url = "http://" + ipadd + ":" + port_val + req;

            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET, url, null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText(response.toString());
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("Error occur!! \n" + error.networkResponse.statusCode);
                        }
                    }
            );

            requestQueue.add(objectRequest);
        }

    public void post_request(View view)
    {
        final TextView textView = (TextView) findViewById(R.id.text);
        final EditText input = (EditText) findViewById(R.id.input);
        final EditText ip = (EditText) findViewById(R.id.ip);
        final EditText port_num = (EditText) findViewById(R.id.port);
        final EditText prm = (EditText) findViewById(R.id.json);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String req = input.getText().toString();
        String ipadd = ip.getText().toString();
        String port_val = port_num.getText().toString();

        String url = "http://" + ipadd + ":" + port_val + req;

        String json_string = prm.getText().toString();
        try {
            JSONObject param = new JSONObject(json_string);

            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, param,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText(response.toString());
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("Error occur!! \n" + error.networkResponse.statusCode);
                        }
                    }
            );
            requestQueue.add(objectRequest);
        }
        catch (JSONException e)
        {
            textView.setText("Error while converting json string to json object!!");
        }

    }
}









        /*
        final TextView textView = (TextView) findViewById(R.id.text);
        final Button button = (Button) findViewById(R.id.button);
        final EditText input = (EditText) findViewById(R.id.input);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String req = input.getText().toString();
                String url = "http://192.168.43.238:5000" + req;
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                textView.setText(response.toString());
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                textView.setText("Error occur");
                            }
                        }
                );

                RequestQueue requestQueue = Volley.newRequestQueue(View );
                requestQueue.add(objectRequest);
            }
        });


        String url = "http://192.168.43.238:5000/stores";

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText(response.toString());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error occur");
                    }
                }
        );

        requestQueue.add(objectRequest);*/
