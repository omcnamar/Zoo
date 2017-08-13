package com.olegsagenadatrytwo.zoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText etCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        etCategory = (EditText) findViewById(R.id.etCategoryName);
    }

    public void submitCategory(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        long result = databaseHelper.saveNewCategory(etCategory.getText().toString());
        if(result != -1){
            Toast.makeText(this, "Successfully added Category", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }


}
