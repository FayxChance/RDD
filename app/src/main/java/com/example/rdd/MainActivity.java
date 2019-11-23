package com.example.rdd;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import Adapter.CharacAdapter;
import Model.Monster;
import Storage.JSONFileStorageCharacter;
import Storage.JsonFileStorageParticipant;


public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private JSONFileStorageCharacter fichier ;
    private JsonFileStorageParticipant fichierParticipant;
    private Button monsterButton;
    private Button encounterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fichier = JSONFileStorageCharacter.get(getApplicationContext());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intentCreateCharac = new Intent(MainActivity.this, Create_Character.class);
                startActivity(intentCreateCharac);
            }
        });

        /* **************** NAVIGATION BUTTON **************/
        encounterButton=findViewById(R.id.encounterButton);
        monsterButton=findViewById(R.id.monsterButton);

        encounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEncounter = new Intent(MainActivity.this, EncounterActivity.class);
                startActivity(intentEncounter);
                onPause();
            }
        });

        monsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMonster = new Intent(MainActivity.this, MonsterActivity.class);
                startActivity(intentMonster);
            }
        });

        /* **************** NAVIGATION BUTTON **************/


        list=findViewById(R.id.listRecycler);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(new CharacAdapter(fichier,this));


    }

    @Override
    protected void onResume(){
        super.onResume();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fichier = JSONFileStorageCharacter.get(getApplicationContext());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intentCreateCharac = new Intent(MainActivity.this, Create_Character.class);
                startActivity(intentCreateCharac);
            }
        });

        list=findViewById(R.id.listRecycler);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(new CharacAdapter(fichier,this));

        /* **************** NAVIGATION BUTTON **************/
        encounterButton=findViewById(R.id.encounterButton);
        monsterButton=findViewById(R.id.monsterButton);

        encounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEncounter = new Intent(MainActivity.this, EncounterActivity.class);
                startActivity(intentEncounter);
                onPause();
            }
        });

        monsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMonster = new Intent(MainActivity.this, MonsterActivity.class);
                startActivity(intentMonster);
            }
        });

        /* **************** NAVIGATION BUTTON **************/


/*
        fichierParticipant=JsonFileStorageParticipant.get(getApplicationContext());
        int nbCharacter = fichier.nextId();

        List<Integer> listeParticipant=fichierParticipant.findAll();

        for (int j  = 0 ; j<nbCharacter; j++){
            View child = list.getChildAt(j);
            System.out.println("NULL ? "+(child==null ? "yes":"no") +""+j);

            if (child!=null){
                System.out.println("nbCharacter"+j);
                CheckBox check = child.findViewById(R.id.participation);
                int idChamp = Integer.parseInt(((TextView)child.findViewById(R.id.idPerso)).getText().toString());
                for (int i = 0 ; i < listeParticipant.size(); i++){
                    if(listeParticipant.get(i)==idChamp)
                        check.setChecked(true);
                }

            }

        }*/
    }

    public void resume(){
        this.onResume();
    }

    @Override
    protected void onPause(){

        super.onPause();

        fichier = JSONFileStorageCharacter.get(getApplicationContext());
        fichierParticipant=JsonFileStorageParticipant.get(getApplicationContext());

        View v;


        List<Integer> liste= fichierParticipant.findAll();

        if(liste.size()!=0)
            fichierParticipant.deleteAll();


        int nbCharacter = fichier.nextId();

        for (int i = 0; i<nbCharacter; i++){
            v =list.getChildAt(i);
            if (v!=null){
                CheckBox maCheck=v.findViewById(R.id.participation);
                if(maCheck.isChecked()){
                    String idPerso = ((TextView)v.findViewById(R.id.idPerso)).getText().toString();
                    System.out.println("**************    "+idPerso+"    +++++++++++++++++++++");

                    fichierParticipant.insert(Integer.parseInt(idPerso));
                }
            }
        }
        liste= fichierParticipant.findAll();
        for(int i = 0; i<liste.size();i++){
            System.out.println("-----------------    "+liste.get(i)+"    +++++++++++++++++++++");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
