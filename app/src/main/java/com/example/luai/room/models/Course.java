package com.example.luai.room.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "course")
public class Course implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private String description;

    @Ignore
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(code);
        parcel.writeString(description);
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

}
