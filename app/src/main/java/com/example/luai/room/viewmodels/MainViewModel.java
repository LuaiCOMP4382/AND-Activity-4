package com.example.luai.room.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.luai.room.database.AppDatabase;
import com.example.luai.room.models.Course;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Course>> courses;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        courses = database.courseDao().loadAllCourses();
    }

    public LiveData<List<Course>> getCourses() {
        return courses;
    }

}
