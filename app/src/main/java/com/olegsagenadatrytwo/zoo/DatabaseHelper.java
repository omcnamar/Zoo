package com.olegsagenadatrytwo.zoo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by omcna on 8/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create a Category Table
        String CREATE_TABLE = "CREATE TABLE " + AnimalsCategoryListSchema.Category.NAME + "(" +
                AnimalsCategoryListSchema.Category.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                AnimalsCategoryListSchema.Category.Columns.CATEGORY + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);

        //Create an Animal Table
        String CREATE_TABLE_ANIMAL = "CREATE TABLE " + AnimalsCategoryListSchema.Animal.NAME + "(" +
                AnimalsCategoryListSchema.Animal.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                AnimalsCategoryListSchema.Animal.Columns.CATEGORY + " TEXT, " +
                AnimalsCategoryListSchema.Animal.Columns.ANIMALNAME + " TEXT, " +
                AnimalsCategoryListSchema.Animal.Columns.EATS + " TEXT, " +
                AnimalsCategoryListSchema.Animal.Columns.IMAGE + " BLOB" +
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_ANIMAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AnimalsCategoryListSchema.Category.NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AnimalsCategoryListSchema.Animal.NAME);
        onCreate(sqLiteDatabase);
    }

    public long saveNewAnimal(Animal animal) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //convert Bitmap to byte array
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        animal.getImage().compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
        contentValues.put(AnimalsCategoryListSchema.Animal.Columns.CATEGORY, animal.getCategory());
        contentValues.put(AnimalsCategoryListSchema.Animal.Columns.ANIMALNAME, animal.getName());
        contentValues.put(AnimalsCategoryListSchema.Animal.Columns.EATS, animal.getEats());
        //contentValues.put(AnimalsCategoryListSchema.Animal.Columns.IMAGE, byteArray);
        long saved = database.insert(AnimalsCategoryListSchema.Animal.NAME, null, contentValues);
        return saved;
    }

    public long saveNewCategory(String category){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AnimalsCategoryListSchema.Category.Columns.CATEGORY, category);
        long saved = database.insert(AnimalsCategoryListSchema.Category.NAME, null, contentValues);
        return saved;
    }

    public long updateExistingAnimal(Animal a) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //convert Bitmap to byte array
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        myContact.getImage().compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
        contentValues.put(AnimalsCategoryListSchema.Animal.Columns.CATEGORY, a.getCategory());
        contentValues.put(AnimalsCategoryListSchema.Animal.Columns.ANIMALNAME, a.getName());
        contentValues.put(AnimalsCategoryListSchema.Animal.Columns.EATS, a.getEats());
        //contentValues.put(AnimalsCategoryListSchema.Animal.Columns.CATEGORY, a.getCategory());

        String[] v = new String[]{String.valueOf(a.getId())};
        long result = database.update(AnimalsCategoryListSchema.Animal.NAME, contentValues, "id=?", v);
        Log.d(TAG, "updateExistingAnimal: " + result);
        return result;
    }

    public long deleteAnimal(Animal animal) {
        SQLiteDatabase database = this.getWritableDatabase();
        String[] v = new String[]{String.valueOf(animal.getId())};
        long result = database.delete(AnimalsCategoryListSchema.Animal.NAME, "id=?", v);
        return result;
    }

    public ArrayList<String> getCategories() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + AnimalsCategoryListSchema.Category.NAME;
        ArrayList<String> listOfCategories = new ArrayList<>();

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                listOfCategories.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listOfCategories;
    }
    public ArrayList<Animal> getAnimals(String category) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + AnimalsCategoryListSchema.Animal.NAME + " WHERE " +
                AnimalsCategoryListSchema.Animal.Columns.CATEGORY + " = '" + category + "'";
        ArrayList<Animal> listOfAnimals = new ArrayList<>();

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                byte[] array = cursor.getBlob(3);
                Bitmap bitmap  = BitmapFactory.decodeByteArray(array, 0, array.length);
                Animal a = new Animal(cursor.getString(1), cursor.getString(2), cursor.getString(3), bitmap);
                a.setId(Integer.parseInt(cursor.getString(0)));
                listOfAnimals.add(a);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listOfAnimals;
    }
}
