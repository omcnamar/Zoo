package com.olegsagenadatrytwo.zoo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final ArrayList<String> categories = new ArrayList<>();
//        categories.add(AnimalsCategoryListSchema.MAMMALS);
//        categories.add(AnimalsCategoryListSchema.BIRDS);
//        categories.add(AnimalsCategoryListSchema.REPTILES);
//        categories.add(AnimalsCategoryListSchema.AMPHIBIANS);
//        categories.add(AnimalsCategoryListSchema.ARTHROPODS);
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(this);
//        for(String cat : categories){
//            databaseHelper.saveNewCategory(cat);
//        }
//        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.zoo);
//        Animal dog = new Animal(AnimalsCategoryListSchema.MAMMALS, AnimalsCategoryListSchema.Mammals.DOG, "Dog food", icon);
//        Animal cat = new Animal(AnimalsCategoryListSchema.MAMMALS, AnimalsCategoryListSchema.Mammals.CAT, "Cat food", icon);
//        Animal eagle = new Animal(AnimalsCategoryListSchema.BIRDS, AnimalsCategoryListSchema.Birds.EAGLE, "Fish", icon);
//        Animal falcon = new Animal(AnimalsCategoryListSchema.BIRDS, AnimalsCategoryListSchema.Birds.FALCON, "Other Birds", icon);
//        Animal snake = new Animal(AnimalsCategoryListSchema.REPTILES, AnimalsCategoryListSchema.Reptile.SNAKE, "Mouse", icon);
//        Animal lizard = new Animal(AnimalsCategoryListSchema.REPTILES, AnimalsCategoryListSchema.Reptile.LIZARD, "Bugs", icon);
//        Animal frog = new Animal(AnimalsCategoryListSchema.AMPHIBIANS, AnimalsCategoryListSchema.Amphibians.FROG, "Bugs", icon);
//        Animal bullFrog = new Animal(AnimalsCategoryListSchema.AMPHIBIANS, AnimalsCategoryListSchema.Amphibians.BULLFROG, "Bugs", icon);
//        Animal merostomata = new Animal(AnimalsCategoryListSchema.ARTHROPODS, AnimalsCategoryListSchema.Arthropods.MEROSTOMATA, "Worms", icon);
//        Animal pycnogonida = new Animal(AnimalsCategoryListSchema.ARTHROPODS, AnimalsCategoryListSchema.Arthropods.PYCNOGONIDA, "Stuff", icon);
//        databaseHelper.saveNewAnimal(dog);
//        databaseHelper.saveNewAnimal(cat);
//        databaseHelper.saveNewAnimal(eagle);
//        databaseHelper.saveNewAnimal(falcon);
//        databaseHelper.saveNewAnimal(snake);
//        databaseHelper.saveNewAnimal(lizard);
//        databaseHelper.saveNewAnimal(frog);
//        databaseHelper.saveNewAnimal(bullFrog);
//        databaseHelper.saveNewAnimal(merostomata);
//        databaseHelper.saveNewAnimal(pycnogonida);
    }

    public void goToCategory(View view) {
        Intent category = new Intent(this, AnimalCategoryActivity.class);
        startActivity(category);
    }
}
