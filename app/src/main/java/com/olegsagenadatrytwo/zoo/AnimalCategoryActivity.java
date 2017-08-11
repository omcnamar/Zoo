package com.olegsagenadatrytwo.zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AnimalCategoryActivity extends AppCompatActivity {

    private static final String TAG = "AnimalCatogoryActivity";
    private ListView lvAnimalCategory;
    private ArrayList<String> categories;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_category);

        //bind the view
        lvAnimalCategory = (ListView) findViewById(R.id.lvAnimalCategory);

        //get the categories from database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        categories = databaseHelper.getCategories();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                categories);

        lvAnimalCategory.setAdapter(adapter);

        lvAnimalCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ListAnimalsActivity.class);
                intent.putExtra(AnimalsCategoryListSchema.CATEGORY, categories.get(i));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        categories = databaseHelper.getCategories();
        lvAnimalCategory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                categories);
        lvAnimalCategory.setAdapter(adapter);
        Log.d(TAG, "onStart: ");
    }
}
