package com.example.luai.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luai.room.database.AppDatabase;
import com.example.luai.room.executers.AppExecutors;
import com.example.luai.room.models.Course;
import com.example.luai.room.viewmodels.CourseDetailsViewModel;
import com.example.luai.room.viewmodels.CourseDetailsViewModelFactory;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private Course course;
    private TextView mTextViewAlreadyAdded;
    private boolean mCourseExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView mTextViewId = findViewById(R.id.tv_id);
        TextView mTextViewCode = findViewById(R.id.tv_code);
        TextView mTextViewDescription = findViewById(R.id.tv_description);

        mTextViewAlreadyAdded = findViewById(R.id.tv_already_added);

        Intent i = getIntent();

        if (i != null)
            if (i.hasExtra(MainActivity.INTENT_KEY_COURSE)) {

                course = (Course) i.getParcelableExtra(MainActivity.INTENT_KEY_COURSE);

                mTextViewId.setText(String.valueOf(course.getId()));
                mTextViewCode.setText(String.valueOf(course.getCode()));
                mTextViewDescription.setText(String.valueOf(course.getDescription()));

            }

        mDb = AppDatabase.getInstance(getApplicationContext());

        setupViewModel();

    }

    private void setupViewModel() {

        CourseDetailsViewModelFactory factory = new CourseDetailsViewModelFactory(mDb, course.getId());
        final CourseDetailsViewModel viewModel = ViewModelProviders.of(this, factory).get(CourseDetailsViewModel.class);

        viewModel.getCourse().observe(this, new Observer<Course>() {
            @Override
            public void onChanged(@Nullable Course course) {

                if (course != null) {
                    mCourseExists = true;
                    mTextViewAlreadyAdded.setVisibility(View.VISIBLE);
                } else {
                    mCourseExists = false;
                    mTextViewAlreadyAdded.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedItemId = item.getItemId();

        switch (selectedItemId) {

            case R.id.mi_add_to_fav:

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        if (!mCourseExists) {
                            mDb.courseDao().insertCourse(course);
                            //Toast.makeText(getApplicationContext(), "Added course", Toast.LENGTH_SHORT).show();
                        } else {
                            mDb.courseDao().deleteCourse(course);
                            //Toast.makeText(getApplicationContext(), "Removed course", Toast.LENGTH_SHORT).show();
                        }

                    }

                });

                return true;

        }

        return super.onOptionsItemSelected(item);

    }

}
