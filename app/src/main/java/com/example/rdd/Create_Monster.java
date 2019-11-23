package com.example.rdd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import Model.Monster;
import Storage.JSONFileStorageMonster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;

import Model.Monster;
import Storage.JSONFileStorageMonster;

public class Create_Monster extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE=1;
    public static final String EXTRA_MONSTER="monster";


    private Monster myMonster;
    private EditText nameET, ddET;
    private String name;
    private String photo;
    private int dd;
    private ImageButton imageBT;
    private Button ddBT, createBt;
    private Bitmap image;
    private JSONFileStorageMonster fichier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__monster);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fichier = JSONFileStorageMonster.get(getApplicationContext());


        nameET = this.findViewById(R.id.nameET);
        ddET = this.findViewById(R.id.ddET);
        ddBT = this.findViewById(R.id.ddBT);
        createBt = this.findViewById(R.id.createBT);
        imageBT=this.findViewById(R.id.imageBT);

        ddBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                ddET.setText((Integer.toString((int) ((Math.random() * 20) + 1))));
            }

        });

        imageBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


        createBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name        =   nameET.getText().toString();
                dd          =   Integer.parseInt(ddET.getText().toString());
                photo       =   imageBT.toString();

                if(image==null){
                    fichier.insert(name,dd,"");

                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    System.out.println(encoded);
                    fichier.insert(name,dd,encoded);

                }

                finish();
            }
        });




    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
            image=(Bitmap)data.getExtras().get("data");
            imageBT.setImageBitmap((Bitmap) data.getExtras().get("data"));
    }
}


