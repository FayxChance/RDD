package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Model.Monster;
import Storage.JSONFileStorageMonster;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rdd.MonsterActivity;
import com.example.rdd.R;

import java.util.ArrayList;
import java.util.List;

public  class MonsterAdapter extends RecyclerView.Adapter<MonsterAdapter.MonsterHolder> {



    public MonsterAdapter() {
        this.monsters=new ArrayList<>();
        monsters.add(new Monster());
        monsters.add(new Monster());
        monsters.add(new Monster());
    }

    class MonsterHolder extends RecyclerView.ViewHolder{
        public ImageView photo;
        public TextView name;
        public TextView idMonster;
        public TextView dd;
        public Button delMonster;

        public  MonsterHolder(View itemView){
            super(itemView);
            photo=itemView.findViewById(R.id.photoHolder);
            name=itemView.findViewById(R.id.nameHolder);
            idMonster=itemView.findViewById(R.id.idMonster);
            dd=itemView.findViewById(R.id.ddHolder);
            delMonster=itemView.findViewById(R.id.delMonster);
            delMonster.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View w){
                    fichier.delete(Integer.parseInt(idMonster.getText().toString()));

                    intent.resume();

                }
            });        }

    }

    private JSONFileStorageMonster fichier;
    private List<Monster> monsters;
    private  MonsterActivity intent;

    public MonsterAdapter(JSONFileStorageMonster fichier, MonsterActivity intent){
        this.intent=intent;
        this.fichier=fichier;
        this.monsters=new ArrayList<>();
        this.monsters=this.fichier.findAll();

    }

    public MonsterAdapter.MonsterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_monster, parent, false);

        return new MonsterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonsterHolder holder, int position) {
        holder.name.setText(monsters.get(position).getName());
        holder.dd.setText(""+monsters.get(position).getDD());
        holder.idMonster.setText(""+monsters.get(position).getId());
        //holder.photo.setImageResource(characters.get(position).getPhoto());
        if (monsters.get(position).getPhoto()!=null){
            String imageEncode = monsters.get(position).getPhoto();
            System.out.println("----------"+imageEncode+"----------");
            byte[] decodedString = Base64.decode(imageEncode, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.photo.setImageBitmap(decodedByte);
        }
    }
    public int getItemCount() {
        return monsters.size();
    }

    //public abstract void onItemClick(View v);


}
