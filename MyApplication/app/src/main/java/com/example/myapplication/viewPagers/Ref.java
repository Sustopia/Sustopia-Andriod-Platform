package com.example.myapplication.viewPagers;

import android.view.View;
import android.widget.AdapterView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AbstractListItemAdapter;
import com.example.myapplication.adapters.RefInfoAdapter;
import com.example.myapplication.data_base.RefInfo;
import com.jpeng.jptabbar.JPTabBar;

import java.util.ArrayList;
import java.util.Arrays;

public class Ref extends AbstractSearchableViewPager<RefInfo>{

    @Override
    protected ArrayList<RefInfo> getInfos() {
        return new ArrayList<>(Arrays.asList(new RefInfo[]{new RefInfo("计算机", "计算机程序设计基础","何明昕","19","26","71.4%",0XDDAAE)}));
    }

    @Override
    protected AdapterView.OnItemSelectedListener getOnItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };//TODO
    }

    @Override
    protected String getQueryHint() {
        return "输入课程名/课程号";
    }

    @Override
    public int getHeadText() {
        return R.string.headRefInfo;
    }

    @Override
    public int getHeadFrameBGC() {
        return GOLD_COLOR;
    }

    @Override
    protected AbstractListItemAdapter<RefInfo> getOwnAdapter() {
        return new RefInfoAdapter(getContext());
    }
}
