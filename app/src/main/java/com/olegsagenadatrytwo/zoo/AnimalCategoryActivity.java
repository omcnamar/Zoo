package com.olegsagenadatrytwo.zoo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class AnimalCategoryActivity extends AppCompatActivity {

    private static final String TAG = "AnimalCatogoryActivity";
    private SwipeMenuListView lvAnimalCategory;
    private ArrayList<String> categories;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_category);

        //bind the view
        lvAnimalCategory = (SwipeMenuListView) findViewById(R.id.lvAnimalCategory);

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

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.BLACK);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        lvAnimalCategory.setMenuCreator(creator);

        lvAnimalCategory.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Intent intent = new Intent(getApplicationContext(), ListAnimalsActivity.class);
                        intent.putExtra(AnimalsCategoryListSchema.CATEGORY, categories.get(position));
                        startActivity(intent);
                        break;
                    case 1:
                        // delete
                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                        databaseHelper.deleteCategory(categories.get(position));
                        onStart();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_newCategory:
                Intent intent = new Intent(this, AddCategoryActivity.class);
                startActivity(intent);
                break;
        }
        return true;
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
