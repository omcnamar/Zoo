package com.olegsagenadatrytwo.zoo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by omcna on 8/10/2017.
 */

public class AdapterListAnimals extends RecyclerView.Adapter<AdapterListAnimals.ViewHolder> {

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    ArrayList<Animal> animals = new ArrayList<>();

    public AdapterListAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvAnimalName.setText(animals.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animalDetail = new Intent(v.getContext(), AnimalDetailActivity.class);
                animalDetail.putExtra("animal", animals.get(position));
                v.getContext().startActivity(animalDetail);
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
                long result = databaseHelper.deleteAnimal(animals.get(position));
                if(result != -1) {
                    animals.remove(position);
                    Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(v.getContext(), "Failed to Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAnimalImage;
        private TextView tvAnimalName;
        private ImageView ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            ivDelete      = (ImageView) itemView.findViewById(R.id.ivDelete);
            ivAnimalImage = (ImageView) itemView.findViewById(R.id.ivAnimalImageView);
            tvAnimalName  = (TextView)  itemView.findViewById(R.id.tvAnimalName);

        }
    }
}
