package com.example.luai.room.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luai.room.R;
import com.example.luai.room.models.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    public interface CourseItemClickListener {
        void onCourseItemClick(Course course);
    }

    private Course[] courses;
    private CourseItemClickListener mCourseItemClickListener;

    public CourseAdapter(CourseItemClickListener courseItemClickListener) {
        mCourseItemClickListener = courseItemClickListener;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CourseViewHolder(inflater.inflate(R.layout.course_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        holder.mTextView.setText(courses[position].getCode());
    }

    @Override
    public int getItemCount() {
        if (courses == null)
            return 0;
        return courses.length;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextView;

        CourseViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.tv_course_code);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mCourseItemClickListener != null)
                mCourseItemClickListener.onCourseItemClick(courses[getAdapterPosition()]);
        }
    }

}
