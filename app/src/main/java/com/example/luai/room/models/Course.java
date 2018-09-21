package com.example.luai.room.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

// TODO (1): Define course as entity with table name "course"
public class Course implements Parcelable {

    // TODO (2): Make id the PrimaryKey, and set autoGenerate to true
    private int id;
    private String code;
    private String description;

    // TODO (3): Since we have two constructors, we need to ignore one. Room needs the one with id, so ignore this constructor
    public Course(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Course(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    private Course(Parcel in) {
        id = in.readInt();
        code = in.readString();
        description = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String name) {
        this.code = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // TODO (4): To pass in classes in intents, we need to make a class implement Parcelable. Write code and description strings
        parcel.writeInt(id);
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
