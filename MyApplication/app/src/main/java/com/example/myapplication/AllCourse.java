package com.example.myapplication;

import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.jpeng.jptabbar.JPTabBar;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class AllCourse extends Fragment {

    private FrameLayout frame;

    private CircleImageView headPortrait;

    private TextView headText;

    private SearchView searchView;

    private CheckBox isFree;

    private SearchableSpinner departmentSpinner;

    private SearchableSpinner weekSpinner;

    private SearchableSpinner timeSpinner;

    private JPTabBar tabBar;

    public AllCourse(JPTabBar tabbar){
        this.tabBar = tabBar;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.all_course, null);
        init(layout);
        return layout;
    }
//
    private void init(View layout){
        frame = (FrameLayout)layout.findViewById(R.id.frame_all_course);
        headPortrait = (CircleImageView) layout.findViewById(R.id.headPortrait);
        headText = (TextView)layout.findViewById(R.id.headText);
        headText.setText(R.string.allCourseInfo);
        headText.setGravity(Gravity.CENTER);
        frame.setBackgroundColor(Color.argb(170,215,255,220));
        searchView = (SearchView)layout.findViewById(R.id.searchView);
//        searchView.setIconified(false);
        searchView.setQueryHint("输入课程名/课程号/教授");
        isFree = (CheckBox)layout.findViewById(R.id.isFree);
        isFree.setText("空闲");
        isFree.setSelected(true);
        headText.bringToFront();
        headPortrait.bringToFront();
        departmentSpinner = (SearchableSpinner)layout.findViewById(R.id.department);
        weekSpinner = (SearchableSpinner)layout.findViewById(R.id.week);
        timeSpinner = (SearchableSpinner)layout.findViewById(R.id.time);
//        headPortrait.setImageURI();//TODO 设置头像
    }
}
