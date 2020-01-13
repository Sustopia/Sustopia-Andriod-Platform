package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
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
import com.jpeng.jptabbar.OnTabSelectListener;

public class SecondActivity_Left extends AppCompatActivity implements View.OnClickListener {
    //    private ScaleTextView mTxtWelcome;

    private static final int[] titles = {R.string.own,R.string.all,R.string.lec,R.string.ref,R.string.gpa};

    private List<Fragment> fragments = new ArrayList<>();
    private List<HeadBar> headBars = new ArrayList<>();
    private List<TextView> switches = new ArrayList<>();

    private DrawerLayout drawerLayout;

    protected FrameLayout frame,frame_left;

    private LinearLayout linearLayout_left;

    protected CircleImageView headPortrait,headPortrait_left;

    protected TextView headText,headText_left,ownCourse_left,allCourse_left,lecture_left,ref_left,GPA_left;

    private ImageView ownCourse_left_image,allCourse_left_image,lecture_left_image,ref_left_image,GPA_left_image;

    private Button setting, logout;

    private ViewPager pager;

    private OwnCourse ownCourse;

    private AllCourse allCourse;

    private Lecture lecture;

    private Ref ref;

    private GPA gpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.left_menu);

        drawerLayout = findViewById(R.id.drawerLayout);
        frame = findViewById(R.id.frame_all_course);
        headPortrait = findViewById(R.id.headPortrait);
        headText = findViewById(R.id.headText);

        headText.setGravity(Gravity.CENTER);

        headText.bringToFront();
        headPortrait.bringToFront();
        pager = findViewById(R.id.viewPager);
        ownCourse = new OwnCourse();
        allCourse = new AllCourse();
        lecture = new Lecture();
        ref = new Ref();
        gpa = new GPA();
        fragments.add(ownCourse);
        fragments.add(allCourse);
        fragments.add(lecture);
        fragments.add(ref);
        fragments.add(gpa);
        fragments.forEach(new Consumer<Fragment>() {
            @Override
            public void accept(Fragment fragment) {
                HeadBar h = (HeadBar)fragment;
                headBars.add(h);
            }
        });

        linearLayout_left = findViewById(R.id.leftMenu);
        frame_left = findViewById(R.id.frame_left);
        headPortrait_left = findViewById(R.id.headPortraitLeft);
        headText_left = findViewById(R.id.headTextLeft);

        ownCourse_left = findViewById(R.id.ownCourse_left);
        allCourse_left = findViewById(R.id.allCourse_left);
        lecture_left = findViewById(R.id.lecture_left);
        ref_left = findViewById(R.id.ref_left);
        GPA_left = findViewById(R.id.GPA_left);

        ownCourse_left_image = findViewById(R.id.ownCourse_left_image);
        allCourse_left_image = findViewById(R.id.allCourse_left_image);
        lecture_left_image = findViewById(R.id.lecture_left_image);
        ref_left_image = findViewById(R.id.ref_left_image);
        GPA_left_image = findViewById(R.id.GPA_left_image);

        setting = findViewById(R.id.setting);
        logout = findViewById(R.id.loggout);
        switches.add(ownCourse_left);
        switches.add(allCourse_left);
        switches.add(lecture_left);
        switches.add(ref_left);
        switches.add(GPA_left);
        switches.forEach(new Consumer<TextView>() {
            @Override
            public void accept(TextView textView) {
                textView.setOnClickListener(SecondActivity_Left.this);
            }
        });

        linearLayout_left.setBackgroundColor(Color.rgb(255,255,255));
//        linearLayout_left.setAlpha(0);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                headText.setText(headBars.get(position).getHeadText());
                frame.setBackgroundColor(headBars.get(position).getHeadFrameBGC());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setCurrentItem(0,false);
    }

    @Override
    public void onClick(View v) {
        int index = switches.indexOf(v);
        System.out.println(index);
        if(index==-1) return;
        pager.setCurrentItem(index,true);
        drawerLayout.closeDrawers();
    }
}
