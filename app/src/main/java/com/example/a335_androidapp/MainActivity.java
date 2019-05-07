package com.example.a335_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.a335_androidapp.dal.BadiDao;
import com.example.a335_androidapp.model.Badi;

public class MainActivity extends ContainerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Badi-App");
        addBadisToClickableList();
    }

    private void addBadisToClickableList() {
        ListView badis = findViewById(R.id.badiliste);
        ArrayAdapter<Badi> badiAdapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        badiAdapter.addAll(BadiDao.getAll());
        badis.setAdapter(badiAdapter);

        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BadiDetailsActivity.class);
                Badi selected = (Badi) parent.getItemAtPosition(position);
                intent.putExtra("badiId", selected.getId());
                intent.putExtra("badiName", selected.getName());
                startActivity(intent);
            }
        };
        badis.setOnItemClickListener(mListClickedHandler);
    }
}
