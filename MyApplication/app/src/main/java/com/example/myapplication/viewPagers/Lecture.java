package com.example.myapplication.viewPagers;

import android.view.View;
import android.widget.AdapterView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AbstractListItemAdapter;
import com.example.myapplication.adapters.LectureInfoAdapter;
import com.example.myapplication.data_base.ClassInfo;
import com.example.myapplication.data_base.LectureInfo;
import com.jpeng.jptabbar.JPTabBar;

import java.util.ArrayList;
import java.util.Arrays;

public class Lecture extends AbstractSearchableViewPager<LectureInfo> {
    public Lecture(JPTabBar tabbar) {
        super(tabbar);
    }

    //TODO 通信获取课程
    @Override
    protected ArrayList<LectureInfo> getInfos(){
        return new ArrayList<LectureInfo>(Arrays.asList(
                new LectureInfo[]{new LectureInfo("计算机","某某某讲座","某大佬","荔园一栋102","周二 08:00-09:50",0x002F)
                        ,new LectureInfo("人文","考古下的长安","某大佬","荔园一栋101","周二 14:00-15:50 ",0xFFFE)}));
    }

    @Override
    //TODO 通信获取可选专业列表
    protected String[] getDepartment(){
        return new String[]{"计算机","人文","数学"};
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
    protected View.OnClickListener getHeadPortraitOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };//TODO
    }

    @Override
    protected String getQueryHint() {
        return "输入讲座所属系/讲座名";
    }

    @Override
    protected int getHeadText() {
        return R.string.allLectureInfo;
    }

    @Override
    protected int getHeadFrameBGC() {
        return SKY_COLOR;
    }

    @Override
    protected AbstractListItemAdapter<LectureInfo> getOwnAdapter() {
        return new LectureInfoAdapter(getContext());
    }

}
