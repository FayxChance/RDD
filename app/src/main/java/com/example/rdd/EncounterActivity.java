package com.example.rdd;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import Model.Monster;
import Storage.JSONFileStorageMonster;

public class EncounterActivity extends AppCompatActivity {

    private RecyclerView list;
    private JSONFileStorageMonster fichier ;
    private Button persoButton;
    private Button encounterButton;
    private Button monsterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        encounterButton=findViewById(R.id.encounterButton);
        persoButton=findViewById(R.id.persoButton);
        monsterButton=findViewById(R.id.monsterButton);
        monsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intentEncounter = new Intent(EncounterActivity.this, MonsterActivity.class);
                startActivity(intentEncounter);
            }
        });

        persoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        encounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPause();
                onResume();
            }
        });
    }



}
