package com.example.myapplication.viewPagers;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.widgets.CircleImageView;

public class OwnCourse extends Fragment implements View.OnClickListener,HeadBar{
    private FrameLayout frame;
    private CircleImageView headPortrait;
    protected TextView headText;
    private TextView mM_info, mLabel, mMon, mTues, mWed, mThur, mFri, mSat;
    private ImageView mPrevious, mAfter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.date_information, null);
        init(layout);
        return layout;
    }

    private void init(View layout){
        frame = layout.findViewById(R.id.frame_all_course);
        headPortrait = layout.findViewById(R.id.headPortrait);
        headText = layout.findViewById(R.id.headText);
        mM_info = layout.findViewById(R.id.M_info);
        mLabel = layout.findViewById(R.id.Label);
        mMon = layout.findViewById(R.id.Mon);
        mTues = layout.findViewById(R.id.Tues);
        mWed = layout.findViewById(R.id.Wed);
        mThur = layout.findViewById(R.id.Thur);
        mFri = layout.findViewById(R.id.Fri);
        mSat = layout.findViewById(R.id.Sat);
        mPrevious = layout.findViewById(R.id.Previous);
        mAfter = layout.findViewById(R.id.After);
        mPrevious.setOnClickListener(this);
        mAfter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getHeadText() {
        return R.string.headOwnCourseInfo;
    }

    @Override
    public int getHeadFrameBGC() {
        return AbstractSearchableViewPager.VIOLET_COLOR;
    }
}
