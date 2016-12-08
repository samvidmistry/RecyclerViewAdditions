package com.example.samvidmistry.recyclerviewadditions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by samvidmistry on 11/20/16.
 */

public class TextItemDecoration extends RecyclerView.ItemDecoration {
    private Rect mRect = new Rect();
    private final Paint mPaint;
    private Path mPath;

    public TextItemDecoration() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15f);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int save = c.save();

        for (int i = 0, size = parent.getChildCount(); i < size; i++) {
            View child = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(child) == 0) {
                continue;
            }
            parent.getDecoratedBoundsWithMargins(child, mRect);
            mRect.left += 10;
            mRect.right -= 10;
            mRect.top += 20;
            mRect.bottom -= 20;
            c.drawRect(mRect, mPaint);
        }

        c.restoreToCount(save);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.set(25, 25, 25, 25);
    }
}
