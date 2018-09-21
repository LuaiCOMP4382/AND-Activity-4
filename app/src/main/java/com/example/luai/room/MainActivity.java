package com.example.luai.room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.luai.room.adapters.CourseAdapter;
import com.example.luai.room.database.AppDatabase;
import com.example.luai.room.models.Course;

public class MainActivity extends AppCompatActivity implements CourseAdapter.CourseItemClickListener {

    public static final String INTENT_KEY_COURSE = "course";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CourseAdapter courseAdapter = new CourseAdapter(this);

        RecyclerView mRecyclerView = findViewById(R.id.rv_courses);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(courseAdapter);
        mRecyclerView.setHasFixedSize(false);

        Course[] courses = new Course[]
                {
                        new Course(1, "COMP433", "Software Engineering"),
                        new Course(10, "COMP133", "C Programming"),
                        new Course(777, "COMP435", "Computer Graphics"),
                        new Course(2, "COMP439", "Compiler Construction"),
                        new Course(68, "COMP233", "Discrete Mathematics"),
                        new Course(678, "COMP333", "Database Systems"),
                        new Course(222, "MATH234", "Introduction to Linear Algebra"),
                        new Course(1412, "MATH331", "Ordinary Differential Equations"),
                        new Course(9, "COMP242", "Data Structures"),
                };

        courseAdapter.setCourses(courses);

    }

    @Override
    public void onCourseItemClick(Course course) {

        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra(INTENT_KEY_COURSE, course);

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedItemId = item.getItemId();

        switch (selectedItemId) {

            case R.id.mi_fav:

                Intent intent = new Intent(this, FavoriteActivity.class);
                startActivity(intent);

                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
