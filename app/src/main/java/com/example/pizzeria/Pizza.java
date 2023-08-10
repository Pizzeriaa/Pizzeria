package com.example.pizzeria;

import android.os.Parcel;
import android.os.Parcelable;

public class Pizza implements Parcelable {
    private String name;
    private int imageResourceId;

    public Pizza(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    protected Pizza(Parcel in) {
        name = in.readString();
        imageResourceId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageResourceId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };
}
