package com.example.luai.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.luai.room.adapters.CourseAdapter;
import com.example.luai.room.database.AppDatabase;
import com.example.luai.room.models.Course;
import com.example.luai.room.viewmodels.MainViewModel;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements CourseAdapter.CourseItemClickListener {

    private AppDatabase mDb;
    private CourseAdapter mCourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mCourseAdapter = new CourseAdapter(this);

        RecyclerView mRecyclerView = findViewById(R.id.rv_fav_courses);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCourseAdapter);
        mRecyclerView.setHasFixedSize(false);

        mDb = AppDatabase.getInstance(getApplicationContext());

        setupViewModel();

    }

    private void setupViewModel() {

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable List<Course> coursesList) {

                if (coursesList == null)
                    return;

                Course[] courses = new Course[coursesList.size()];

                for (int i = 0; i < coursesList.size(); i++)
                    courses[i] = coursesList.get(i);

                mCourseAdapter.setCourses(courses);

            }
        });

    }

    @Override
    public void onCourseItemClick(Course course) {

        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra(MainActivity.INTENT_KEY_COURSE, course);

        startActivity(intent);

    }
}
