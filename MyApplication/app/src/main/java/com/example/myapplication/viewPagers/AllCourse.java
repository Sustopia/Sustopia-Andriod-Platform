package com.example.myapplication.viewPagers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.Nullable;

import com.example.myapplication.adapters.AbstractListItemAdapter;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ClassInfoAdapter;
import com.example.myapplication.data_base.ClassInfo;
import com.jpeng.jptabbar.JPTabBar;

import java.util.ArrayList;
import java.util.Arrays;

public class AllCourse extends AbstractSearchableViewPager<ClassInfo> {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.searchable_view_pager, null);
        init(layout);
        return layout;
    }

    @Override
    //TODO 通信获取课程
    protected ArrayList<ClassInfo> getInfos(){
        return new ArrayList<>(Arrays.asList(
                new ClassInfo[]{new ClassInfo("CS 203","数据结构","唐博","荔园一栋102","周二 08:00-09:50 周二 14:00-15:50",3,0x007F)
                        ,new ClassInfo("CS 209A","计算机系统设计与应用","何明昕","荔园一栋101","周二 14:00-15:50 周二 16:20-18:10",3,0xFFFF)}));
    }

    //TODO 通信获取可选专业列表
    @Override
    protected String[] getDepartment(){
        return new String[]{"Test","wcsl","sdlwsl"};
    }

    //TODO 修改过滤器时重新获取课表
    @Override
    protected AdapterView.OnItemSelectedListener getOnItemSelectedListener(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listAdapter.getList().add(new ClassInfo((String)departmentSpinner.getSelectedItem(),(String)weekSpinner.getSelectedItem()
                        ,(String)timeSpinner.getSelectedItem(),(String)departmentSpinner.getSelectedItem(),(String)timeSpinner.getSelectedItem(),0,0XFAB112));
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }


    @Override
    protected String getQueryHint() {
        return "输入课程名/课程号/教授";
    }

    @Override
    public int getHeadText() {
        return R.string.headAllCourseInfo;
    }

    @Override
    public int getHeadFrameBGC() {
        return BOHE_COLOR;
    }

    @Override
    protected AbstractListItemAdapter<ClassInfo> getOwnAdapter() {
        return new ClassInfoAdapter(getContext());
    }


}
