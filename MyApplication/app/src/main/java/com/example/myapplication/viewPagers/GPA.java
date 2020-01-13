package com.example.myapplication.viewPagers;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class GPA extends Fragment implements HeadBar{
    @Override
    public int getHeadText() {
        return R.string.headGpaInfo;
    }

    @Override
    public int getHeadFrameBGC() {
        return AbstractSearchableViewPager.TURKEY_COLOR;
    }
}
