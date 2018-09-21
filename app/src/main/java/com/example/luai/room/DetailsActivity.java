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

                // TODO (10): Update the text views to show course id, code and description
                mTextViewId.setText(String.valueOf(course.getId()));

            }

        // TODO (11): Get database instance and pass in getApplicationContext
        mDb = ???

        setupViewModel();

    }

    private void setupViewModel() {

        CourseDetailsViewModelFactory factory = new CourseDetailsViewModelFactory(mDb, course.getId());
        final CourseDetailsViewModel viewModel = ViewModelProviders.of(this, factory).get(CourseDetailsViewModel.class);

        viewModel.getCourse().observe(this, new Observer<Course>() {
            @Override
            public void onChanged(@Nullable Course course) {

                // TODO (12): If the course we get is null, set mCourseExists to true and make mTextViewAlreadyAdded visible, else, do the opposite

                if (course != null) {

                } else {

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

                        // TODO (13): If the course does not exist in database, insert it, else, delete it

                        if (!mCourseExists) {
                            mDb.courseDao().???
                        } else {
                            mDb.courseDao().???
                        }

                    }

                });

                return true;

        }

        return super.onOptionsItemSelected(item);

    }

}
