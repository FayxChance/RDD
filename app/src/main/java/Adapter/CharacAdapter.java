package Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Model.Character;
import Storage.JSONFileStorageCharacter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rdd.MainActivity;
import com.example.rdd.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public  class CharacAdapter extends RecyclerView.Adapter<CharacAdapter.CharacHolder> {


    public CharacAdapter() {
        this.characters=new ArrayList<>();
        characters.add(new Character());
        characters.add(new Character());
        characters.add(new Character());
    }

    class CharacHolder extends RecyclerView.ViewHolder{
        public ImageView photo;
        public TextView name;
        public Button button;
        public CheckBox participation;
        public TextView idPerso;

        public  CharacHolder(View itemView){
            super(itemView);
            photo=itemView.findViewById(R.id.photoHolder);
            name=itemView.findViewById(R.id.nameHolder);
            button=itemView.findViewById(R.id.buttonHolder);
            participation=itemView.findViewById(R.id.participation);
            idPerso=itemView.findViewById(R.id.idPerso);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View w){
                    fichier.delete(Integer.parseInt(idPerso.getText().toString()));
                    intent.resume();
                }
            });
        }

    }

    private JSONFileStorageCharacter fichier;
    private List<Character> characters;
    private MainActivity intent;

    public CharacAdapter( JSONFileStorageCharacter fichier, MainActivity mainActivity){
        this.intent=mainActivity;
        this.fichier=fichier;
        this.characters=new ArrayList<>();
        this.characters=this.fichier.findAll();

    }

    public CharacAdapter.CharacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_character, parent, false);

        return new CharacHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacHolder holder, int position) {
        holder.name.setText(characters.get(position).getName());
        holder.idPerso.setText(""+characters.get(position).getId());
        //holder.photo.setImageResource(characters.get(position).getPhoto());
        if (characters.get(position).getPhoto()!=null){
            String imageEncode = characters.get(position).getPhoto();
            System.out.println("----------"+imageEncode+"----------");
            byte[] decodedString = Base64.decode(imageEncode, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.photo.setImageBitmap(decodedByte);
        }

    }
    public int getItemCount() {
        return characters.size();
    }

    //public abstract void onItemClick(View v);


}
