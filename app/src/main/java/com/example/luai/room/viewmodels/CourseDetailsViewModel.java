package com.example.luai.room.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.luai.room.database.AppDatabase;
import com.example.luai.room.models.Course;

public class CourseDetailsViewModel extends ViewModel {

    private LiveData<Course> course;

    public CourseDetailsViewModel(AppDatabase database, int courseId) {
        // TODO (14): Find course by id from the database
        course = ???;
    }

    public LiveData<Course> getCourse() {
        return course;
    }

}
