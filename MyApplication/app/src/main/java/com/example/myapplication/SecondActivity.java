package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.*;
import java.util.function.Consumer;

import com.example.myapplication.viewPagers.AllCourse;
import com.example.myapplication.viewPagers.GPA;
import com.example.myapplication.viewPagers.HeadBar;
import com.example.myapplication.viewPagers.Lecture;
import com.example.myapplication.viewPagers.OwnCourse;
import com.example.myapplication.viewPagers.Ref;
import com.example.myapplication.widgets.CircleImageView;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

public class SecondActivity extends AppCompatActivity {
//    private ScaleTextView mTxtWelcome;
    @Titles
    private static final int[] titles = {R.string.own,R.string.all,R.string.lec,R.string.ref,R.string.gpa};
    @NorIcons
    private static final int[] normalIcons = {R.mipmap.tab1_normal,R.mipmap.tab2_normal,R.mipmap.tab3_normal,R.mipmap.tab4_normal,R.mipmap.tab5_normal};
    @SeleIcons
    private static final int[] selectIcons = {R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected,R.mipmap.tab5_selected};

    private List<Fragment> list = new ArrayList<>();
    private List<HeadBar> headBars = new ArrayList<>();

    protected FrameLayout frame;

    protected CircleImageView headPortrait;

    protected TextView headText;

    private ViewPager pager;

    private JPTabBar tabbar;

    private OnTabSelectListener selectListener;

    private OwnCourse ownCourse;

    private AllCourse allCourse;

    private Lecture lecture;

    private Ref ref;

    private GPA gpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        frame = findViewById(R.id.frame_all_course);
        headPortrait = findViewById(R.id.headPortrait);
        headText = findViewById(R.id.headText);

        headText.setGravity(Gravity.CENTER);

        headText.bringToFront();
        headPortrait.bringToFront();
        tabbar = findViewById(R.id.tabbar);
        pager = findViewById(R.id.viewPager);
        tabbar.setTitles(titles).setNormalIcons(normalIcons).setSelectedIcons(selectIcons).generate();
        tabbar.setGradientEnable(true);
        tabbar.setPageAnimateEnable(true);
        ownCourse = new OwnCourse();
        allCourse = new AllCourse();
        lecture = new Lecture();
        ref = new Ref();
        gpa = new GPA();
        list.add(ownCourse);
        list.add(allCourse);
        list.add(lecture);
        list.add(ref);
        list.add(gpa);
        list.forEach(new Consumer<Fragment>() {
            @Override
            public void accept(Fragment fragment) {
                HeadBar h = (HeadBar)fragment;
                headBars.add(h);
            }
        });

        selectListener = new OnTabSelectListener() {
            @Override
            public void onTabSelect(int index) {

                headText.setText(headBars.get(index).getHeadText());
                frame.setBackgroundColor(headBars.get(index).getHeadFrameBGC());

//                Toast.makeText(SecondActivity.this,"choose the tab index is "+index,Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onInterruptSelect(int index) {
                return false;
            }
        };
        tabbar.setTabListener(selectListener);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        tabbar.setContainer(pager);
        tabbar.setDismissListener(new BadgeDismissListener() {
            @Override
            public void onDismiss(int position) {
                System.out.println("clear");
            }
        });
        tabbar.setSelectTab(0);
    }

    public JPTabBar getTabbar(){
        return tabbar;
    }
}
