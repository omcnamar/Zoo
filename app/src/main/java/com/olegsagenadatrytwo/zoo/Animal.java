package com.olegsagenadatrytwo.zoo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by omcna on 8/10/2017.
 */

public class Animal implements Parcelable {
    private String category;
    private String name;
    private String eats;
    private Bitmap image;
    private int id;

    public Animal(String category, String name, String eats, Bitmap image) {
        this.category = category;
        this.name = name;
        this.eats = eats;
        this.image = image;

    }

    protected Animal(Parcel in) {
        category = in.readString();
        name = in.readString();
        eats = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
        id = in.readInt();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEats() {
        return eats;
    }

    public void setEats(String eats) {
        this.eats = eats;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(name);
        dest.writeString(eats);
        dest.writeParcelable(image, flags);
        dest.writeInt(id);
    }
}
