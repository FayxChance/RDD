package Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rdd.R;

import java.util.ArrayList;

import Model.Monster;

public class EncounterAdapter extends RecyclerView.Adapter<MonsterAdapter.EncounterHolder> {


    public EncounterAdapter() {

    }

    class EncounterHolder extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView name;
        public TextView idMonster;
        public TextView dd;
        public Button delMonster;

        public EncounterHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photoHolder);
            name = itemView.findViewById(R.id.nameHolder);
            idMonster = itemView.findViewById(R.id.idMonster);
            dd = itemView.findViewById(R.id.ddHolder);
            delMonster = itemView.findViewById(R.id.delMonster);
            delMonster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View w) {
                    fichier.delete(Integer.parseInt(idMonster.getText().toString()));

                    intent.resume();

                }
            });

        }
    }
}
