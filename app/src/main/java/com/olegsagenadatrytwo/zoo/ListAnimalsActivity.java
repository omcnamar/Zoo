package com.olegsagenadatrytwo.zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ListAnimalsActivity extends AppCompatActivity {

    private static final String TAG = "ListAnimalsActivity";
    private RecyclerView rvListAnimals;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private AdapterListAnimals adapterListAnimals;
    private ArrayList<Animal> animals;
    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_animals);

        //List of Animals for the recycler view
        animals = new ArrayList<>();

        //get intent from the passed Activity
        Intent intent = getIntent();
        //if intent is not null try to get data out
        if(intent != null) {
            category = intent.getStringExtra(AnimalsCategoryListSchema.CATEGORY);
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            animals = databaseHelper.getAnimals(category);
        }

        rvListAnimals = (RecyclerView) findViewById(R.id.rvListAnimals);

        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvListAnimals.setLayoutManager(layoutManager);
        rvListAnimals.setItemAnimator(itemAnimator);

        adapterListAnimals = new AdapterListAnimals(animals);
        rvListAnimals.setAdapter(adapterListAnimals);
        Log.d(TAG, "onCreate: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        adapterListAnimals.setAnimals(databaseHelper.getAnimals(category));
        adapterListAnimals.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar_for_list_animals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_newAnimal:
                Intent intent = new Intent(this, AnimalDetailActivity.class);
                intent.putExtra(AnimalsCategoryListSchema.CATEGORY,category);
                startActivity(intent);
                break;
        }
        return true;
    }
}
