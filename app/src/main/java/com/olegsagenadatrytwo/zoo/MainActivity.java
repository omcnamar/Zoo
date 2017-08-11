package com.olegsagenadatrytwo.zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<String> categories = new ArrayList<>();
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
//        Animal dog = new Animal(AnimalsCategoryListSchema.MAMMALS, AnimalsCategoryListSchema.Mammals.DOG, "Dog food", null);
//        Animal cat = new Animal(AnimalsCategoryListSchema.MAMMALS, AnimalsCategoryListSchema.Mammals.CAT, "Cat food", null);
//        Animal eagle = new Animal(AnimalsCategoryListSchema.BIRDS, AnimalsCategoryListSchema.Birds.EAGLE, "Fish", null);
//        Animal falcon = new Animal(AnimalsCategoryListSchema.BIRDS, AnimalsCategoryListSchema.Birds.FALCON, "Other Birds", null);
//        Animal snake = new Animal(AnimalsCategoryListSchema.REPTILES, AnimalsCategoryListSchema.Reptile.SNAKE, "Mouse", null);
//        Animal lizard = new Animal(AnimalsCategoryListSchema.REPTILES, AnimalsCategoryListSchema.Reptile.LIZARD, "Bugs", null);
//        Animal frog = new Animal(AnimalsCategoryListSchema.AMPHIBIANS, AnimalsCategoryListSchema.Amphibians.FROG, "Bugs", null);
//        Animal bullFrog = new Animal(AnimalsCategoryListSchema.AMPHIBIANS, AnimalsCategoryListSchema.Amphibians.BULLFROG, "Bugs", null);
//        Animal merostomata = new Animal(AnimalsCategoryListSchema.ARTHROPODS, AnimalsCategoryListSchema.Arthropods.MEROSTOMATA, "Worms", null);
//        Animal pycnogonida = new Animal(AnimalsCategoryListSchema.ARTHROPODS, AnimalsCategoryListSchema.Arthropods.PYCNOGONIDA, "Stuff", null);
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
