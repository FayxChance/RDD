package com.example.rdd;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import Adapter.CharacAdapter;
import Adapter.MonsterAdapter;
import Model.Monster;
import Storage.JSONFileStorageCharacter;
import Storage.JSONFileStorageMonster;
import Storage.JsonFileStorageParticipant;

public class MonsterActivity extends AppCompatActivity {


    private RecyclerView list;
    private JSONFileStorageMonster fichier ;
    private Button persoButton;
    private Button encounterButton;
    private Button monsterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fichier = JSONFileStorageMonster.get(getApplicationContext());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCreateCharac = new Intent(MonsterActivity.this, Create_Monster.class);
                startActivity(intentCreateCharac);
            }
        });

        /* **************** NAVIGATION BUTTON **************/
        encounterButton=findViewById(R.id.encounterButton);
        persoButton=findViewById(R.id.persoButton);
        monsterButton=findViewById(R.id.monsterButton);
        encounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intentEncounter = new Intent(MonsterActivity.this, EncounterActivity.class);
                startActivity(intentEncounter);
            }
        });

        persoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        monsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPause();
                onResume();
            }
        });

        /* **************** NAVIGATION BUTTON **************/

        list=findViewById(R.id.listRecycler);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(new MonsterAdapter(fichier,this));
    }

    protected void onResume(){
        super.onResume();
        setContentView(R.layout.activity_monster);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fichier = JSONFileStorageMonster.get(getApplicationContext());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCreateCharac = new Intent(MonsterActivity.this, Create_Monster.class);
                startActivity(intentCreateCharac);
            }
        });

        /* **************** NAVIGATION BUTTON **************/
        encounterButton=findViewById(R.id.encounterButton);
        persoButton=findViewById(R.id.persoButton);
        monsterButton=findViewById(R.id.monsterButton);


        encounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intentEncounter = new Intent(MonsterActivity.this, EncounterActivity.class);
                startActivity(intentEncounter);
            }
        });

        persoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        monsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPause();
                onResume();
            }
        });
        /* **************** NAVIGATION BUTTON **************/

        list=findViewById(R.id.listRecycler);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(new MonsterAdapter(fichier,this));
    }

    public void resume(){
        this.onResume();
    }
}
