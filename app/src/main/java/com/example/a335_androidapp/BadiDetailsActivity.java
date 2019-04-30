package com.example.a335_androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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

public class BadiDetailsActivity extends AppCompatActivity {
    private int badiId;
    private ProgressBar progressBar;
    private static final String WIE_WARM_API_URL = "https://www.wiewarm.ch/api/v1/bad.json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_details);
        progressBar = findViewById(R.id.loading_badi_details_progress);
        Intent intent = getIntent();
        badiId = intent.getIntExtra("badiId", 0);
        String name = intent.getStringExtra("badiName");
        setTitle(name);
        progressBar.setVisibility(View.VISIBLE);
        getBadiTemp(WIE_WARM_API_URL + badiId);
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

    private void getBadiTemp(String url) {
        final ArrayAdapter<Becken> beckenInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
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
}
