package com.olegsagenadatrytwo.zoo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AnimalDetailActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;
    private ImageView ivAnimalImage;
    private EditText etCategory;
    private EditText etName;
    private EditText etEats;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        ivAnimalImage = (ImageView) findViewById(R.id.ivAnimalImageView);
        etCategory    = (EditText)  findViewById(R.id.etCategory);
        etName    = (EditText)  findViewById(R.id.etName);
        etEats    = (EditText)  findViewById(R.id.etEats);

        Intent intent = getIntent();
        if(intent != null){

            animal = (Animal) intent.getParcelableExtra("animal");
            if(animal == null){
                String category = intent.getStringExtra(AnimalsCategoryListSchema.CATEGORY);
                animal = new Animal("","","",null);
                animal.setCategory(category);
                etCategory.setText(category);
            }else {
                etCategory.setText(animal.getCategory());
                etName.setText(animal.getName());
                etEats.setText(animal.getEats());
                ivAnimalImage.setImageBitmap(animal.getImage());
            }
        }

        ivAnimalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, CAMERA_REQUEST);
            }
        });

    }

    public void submitAnimal(View view) {
        if(animal != null) {
            Intent animalCategoryActivity = null;
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            if(!animal.getCategory().equals(etCategory.getText().toString())){
                databaseHelper.saveNewCategory(etCategory.getText().toString());
                animalCategoryActivity = new Intent(this, AnimalCategoryActivity.class);
            }
            animal.setCategory(etCategory.getText().toString());
            animal.setName(etName.getText().toString());
            animal.setEats(etEats.getText().toString());
            animal.setImage(((BitmapDrawable)ivAnimalImage.getDrawable()).getBitmap());
            long result = -1;
            if(animal.getId() == 0){
                if(animal.getImage() == null){
                    Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.zoo);
                    animal.setImage(icon);
                }
                result = databaseHelper.saveNewAnimal(animal);
            }else{
                result = databaseHelper.updateExistingAnimal(animal);
            }
            if(result != -1){
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Failed to update", Toast.LENGTH_SHORT).show();
            }

            if(animalCategoryActivity != null){
                startActivity(animalCategoryActivity);
            }
        }
    }

    public void deleteAnimal(View view) {
        if(animal != null) {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.deleteAnimal(animal);
            onBackPressed();
        }
    }
    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ivAnimalImage.setImageBitmap(imageBitmap);

        }

    }
}
