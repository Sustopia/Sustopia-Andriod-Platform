package com.example.myapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class FlipableScrollView extends HorizontalScrollView {
    private int currentPageID;
    private int currentPage = 0;
    private int totalPages = 3;


    public FlipableScrollView(Context context) {
        super(context);
    }

    public FlipableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlipableScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
