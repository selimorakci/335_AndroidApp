package com.example.a335_androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a335_androidapp.helper.WieWarmJsonParser;
import com.example.a335_androidapp.model.Badi;
import com.example.a335_androidapp.model.Becken;

import org.json.JSONException;

public class BadiDetailsActivity extends ContainerActivity {
    private int badiId;
    private String ort;
    private ProgressBar progressBar;
    private static final String WIE_WARM_API_URL = "https://www.wiewarm.ch/api/v1/bad.json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_details);
        // initializes and displays the loader
        progressBar = findViewById(R.id.loading_badi_details_progress);
        progressBar.setVisibility(View.VISIBLE);
        // gets intend and reads the data that was sent
        Intent intent = getIntent();
        badiId = intent.getIntExtra("badiId", 0);
        ort = intent.getStringExtra("badiOrt");
        String name = intent.getStringExtra("badiName");

        setTitle("Badi-App");
        TextView title = (TextView) findViewById(R.id.title_text);
        TextView ortText = (TextView) findViewById(R.id.ort_text);
        title.setText(name);
        ortText.setText(ort);
        addTempIconToClickableList();
        getBadiTemp(WIE_WARM_API_URL + badiId);
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
        dialogBuilder.setMessage("Die Badidetails konnten nicht geladen werden. Versuche es später nochmals.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    // does an api call to get the temperature of the pools by id
    private void getBadiTemp(String url) {
        final ArrayAdapter<Becken> beckenInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // prepares the data from the api call
                    Badi badi = WieWarmJsonParser.createBadiFromJsonString(response);

                    beckenInfosAdapter.addAll(badi.getBecken());
                    ListView badiInfoList = findViewById(R.id.beckenliste);
                    badiInfoList.setAdapter(beckenInfosAdapter);
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

    // makes the temperature icon clickable and defines action on click
    private void addTempIconToClickableList() {
        ImageView temp = findViewById(R.id.temp_icon);
        temp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // opens new activity/intend
                Intent intent = new Intent(getApplicationContext(), BadiTemperatureActivity.class);
                // "attaches" data to the new intent
                intent.putExtra("badiOrt", ort);
                startActivity(intent);
            }
        });
    }
}
