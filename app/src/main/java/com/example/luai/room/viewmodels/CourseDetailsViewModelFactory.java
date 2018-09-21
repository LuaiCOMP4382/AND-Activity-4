package com.example.luai.room.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.luai.room.database.AppDatabase;

public class CourseDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mCourseId;

    public CourseDetailsViewModelFactory(AppDatabase database, int courseId) {
        mDb = database;
        mCourseId = courseId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CourseDetailsViewModel(mDb, mCourseId);
    }

}
