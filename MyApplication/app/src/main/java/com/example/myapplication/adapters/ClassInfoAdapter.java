package com.example.myapplication.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.viewPagers.AllCourse;
import com.example.myapplication.R;
import com.example.myapplication.data_base.ClassInfo;

public class ClassInfoAdapter extends AbstractListItemAdapter<ClassInfo> {

    public ClassInfoAdapter(Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getUniquenessId();
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        ClassInfo info = (ClassInfo) getItem(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.extensable_info, parent, false);
        }

        final TextView classId = convertView.findViewById(R.id.classIdExtensed);
        final TextView className = convertView.findViewById(R.id.classNameExtensed);
        final TextView classInfo = convertView.findViewById(R.id.classInfo);
        final Button addToTerm = convertView.findViewById(R.id.addToTerm);
        final Button addToWeek = convertView.findViewById(R.id.addToWeek);
        final ImageView imageView = convertView.findViewById(R.id.isExtense);
        final ViewGroup.LayoutParams params = convertView.getLayoutParams();
        final int time = 80;
        ViewPropertyAnimator imageAnimator = imageView.animate();
        Handler mainHandler=new Handler(Looper.getMainLooper());

        classId.setText(info.getCourseId());
        classId.setBackgroundColor(AllCourse.BOHE_COLOR);
        classId.setGravity(Gravity.CENTER);
        className.setText(info.getName() + "\n" + info.getTeacher());
        classInfo.setText("地点: " + info.getRoom() + "\n时间: " + info.getTime() + "\n学分: " + info.getCredit());
        addToTerm.setText(R.string.addToTerm);
        addToTerm.setBackgroundColor(AllCourse.BOHE_COLOR);
        addToWeek.setText(R.string.addToWeek);
        addToWeek.setBackgroundColor(AllCourse.BOHE_COLOR);

        if (!addToTerm.hasOnClickListeners())
            addToTerm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Add To Term",Toast.LENGTH_SHORT).show();
                }
            });
        if (!addToWeek.hasOnClickListeners())
            addToWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Add To Week",Toast.LENGTH_SHORT).show();
                }
            });

        if (info.isExtense()) {
            imageAnimator.rotation(0).setDuration(time).start();
            params.height = dip2px(context,310);

            final View curr = convertView;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(time);
                    }catch(InterruptedException e){ }
                    classInfo.setVisibility(View.VISIBLE);
                    addToTerm.setVisibility(View.VISIBLE);
                    addToWeek.setVisibility(View.VISIBLE);
                    addToTerm.setFocusable(false);
                    addToWeek.setFocusable(false);
                    curr.setLayoutParams(params);
                }
            });
        } else {
            imageAnimator.rotation(270).setDuration(time).start();
            params.height = dip2px(context,100);
            final View curr = convertView;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(time);
                    }catch(InterruptedException e){ }
                    classInfo.setVisibility(View.INVISIBLE);
                    addToTerm.setVisibility(View.INVISIBLE);
                    addToWeek.setVisibility(View.INVISIBLE);
                    curr.setLayoutParams(params);
                }
            });
        }

        return convertView;
    }
}