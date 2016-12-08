package com.example.samvidmistry.recyclerviewadditions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by samvidmistry on 11/20/16.
 */

public class PhotoItemDecoration extends RecyclerView.ItemDecoration {
    private Rect mRect = new Rect();
    private final Paint mPaint;

    public PhotoItemDecoration() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int save = c.save();

        int top = 0;
        int bottom = parent.getHeight();

        for (int i = 0, size = parent.getChildCount(); i < size; i++) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mRect);
            mRect.left += 10;
            mRect.right -= 10;
            mRect.top += 2;
            mRect.bottom -= 2;
            c.drawRect(mRect, mPaint);
        }

        c.restoreToCount(save);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(10, 0, 10, 0);
    }
}
