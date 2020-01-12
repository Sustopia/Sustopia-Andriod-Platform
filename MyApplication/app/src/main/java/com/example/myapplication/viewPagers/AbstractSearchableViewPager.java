package com.example.myapplication.viewPagers;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AbstractListItemAdapter;
import com.example.myapplication.data_base.Extensable;
import com.example.myapplication.widgets.CircleImageView;
import com.jpeng.jptabbar.JPTabBar;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchableViewPager<ITEM_TYPE extends Extensable> extends Fragment {

    public static final @ColorInt
    int BOHE_COLOR = Color.argb(170,195,255,200);
    public static final @ColorInt
    int SKY_COLOR = Color.argb(170,135,205,255);
    public static final @ColorInt
    int GOLD_COLOR = Color.argb(170,240,220,132);

    protected FrameLayout frame;

    protected CircleImageView headPortrait;

    protected TextView headText;

    protected SearchView searchView;

    protected CheckBox isFree;

    protected SearchableSpinner departmentSpinner;

    protected Spinner weekSpinner;

    protected Spinner timeSpinner;

    protected ListView listView;

    protected List<ITEM_TYPE> infoList;

    protected AbstractListItemAdapter<ITEM_TYPE> listAdapter;

    protected JPTabBar tabBar;

    public AbstractSearchableViewPager(JPTabBar tabbar){
        this.tabBar = tabBar;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.searchable_view_pager, null);
        init(layout);
        return layout;
    }
    //
    protected void init(View layout){
        frame = layout.findViewById(R.id.frame_all_course);
        headPortrait = layout.findViewById(R.id.headPortrait);
        headText = layout.findViewById(R.id.headText);
        headText.setText(getHeadText());//TODO BE OVERRIDE

        headText.setGravity(Gravity.CENTER);
        frame.setBackgroundColor(getHeadFrameBGC());//TODO BE OVERRIDE
        searchView = layout.findViewById(R.id.searchView);
        searchView.setQueryHint(getQueryHint());//TODO BE OVERRIDE
        isFree = layout.findViewById(R.id.isFree);
        isFree.setText("空闲");

        headText.bringToFront();
        headPortrait.bringToFront();

        departmentSpinner = layout.findViewById(R.id.department);
        weekSpinner = layout.findViewById(R.id.week);
        timeSpinner = layout.findViewById(R.id.time);

        headPortrait.setOnClickListener(getHeadPortraitOnClickListener());//TODO BE OVERRIDE
        departmentSpinner.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.department_choose,getDepartment()));//TODO BE OVERRIDE
        departmentSpinner.setOnItemSelectedListener(getOnItemSelectedListener());//TODO BE OVERRIDE
        weekSpinner.setOnItemSelectedListener(getOnItemSelectedListener());//TODO
        timeSpinner.setOnItemSelectedListener(getOnItemSelectedListener());//TODO

        listView = layout.findViewById(R.id.classList);
        listAdapter = getOwnAdapter();//TODO BE OVERRIDE
        infoList = getInfos();//TODO BE OVERRIDE
        listAdapter.setList(infoList);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listAdapter.getList().get(position).changeExtense();
                listAdapter.notifyDataSetChanged(listView,position);
            }
        });
//        classList.setAdapter();
//        headPortrait.setImageResource(R.drawable.ic_launcher_background);
//        headPortrait.setImageURI();//TODO 设置头像?
    }

    //TODO 通信获取课程
    protected abstract ArrayList<ITEM_TYPE> getInfos();

    //TODO 通信获取可选专业列表
    protected String[] getDepartment(){
        return new String[]{"Test","wcsl","sdlwsl"};
    }

    //TODO 修改过滤器时重新获取课表
    protected abstract AdapterView.OnItemSelectedListener getOnItemSelectedListener();

    protected abstract View.OnClickListener getHeadPortraitOnClickListener();

    protected abstract String getQueryHint();

    protected abstract int getHeadText();

    protected abstract int getHeadFrameBGC();

    protected abstract AbstractListItemAdapter<ITEM_TYPE> getOwnAdapter();
}
