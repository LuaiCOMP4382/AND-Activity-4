package com.example.luai.room.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.luai.room.models.Course;

import java.util.List;

@Dao
public interface CourseDAO {

    @Query("SELECT * FROM course")
    LiveData<List<Course>> loadAllCourses();

    @Insert
    void insertCourse(Course course);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM course WHERE id = :id")
    LiveData<Course> findCourseById(int id);

}
