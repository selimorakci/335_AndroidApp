package com.example.a335_androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;


public class BadiTemperatureActivity extends ContainerActivity {
    private static final String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String appId = "&APPID=59ee4acb1201b53089d449b38595bc16";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_temperature);
        // initializes and displays the loader
        progressBar = findViewById(R.id.loading_badi_temp_progress);
        progressBar.setVisibility(View.VISIBLE);
        setTitle("Badi-App");
        // gets intend and reads the data that was sent
        Intent intent = getIntent();
        String ort = intent.getStringExtra("badiOrt");

        TextView ortText = (TextView) findViewById(R.id.ort_text);
        ortText.setText(ort);
        getBadiTemp(endpoint + ort + appId);
        // adds the "back" icon
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // adds the function to the back icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void generateAlertDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Closes this activity
                finish();
            }
        });
        dialogBuilder.setMessage("Die Temeratur konnte nicht geladen werden. Versuche es später nochmals.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    // does an api call to get the weather of the city by name
    private void getBadiTemp(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    TextView tempText = (TextView) findViewById(R.id.temp_text);
                    TextView weatherText = (TextView) findViewById(R.id.weather_text);
                    DecimalFormat twoDForm = new DecimalFormat("#.##");

                    // reads the data from the JSON response
                    JSONArray weatherArr = jsonObj.getJSONArray("weather");
                    JSONObject weatherObj = weatherArr.getJSONObject(0);
                    double tempCelsius = (jsonObj.getJSONObject("main").getDouble("temp") - 273.15);
                    String icon = weatherObj.getString("icon");
                    // sets the text of the TextView to the response text
                    weatherText.setText(weatherObj.getString("main"));
                    tempText.setText(twoDForm.format(tempCelsius)+"°");
                    // gets the icon from the api and sets it to the image view
                    Picasso.with(getApplicationContext()).load("https://openweathermap.org/img/w/"+icon+".png").into((ImageView) findViewById(R.id.weather_icon));
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    generateAlertDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);
    }


}
