package com.averagecoders.clef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by harish on 18-Dec-17.
 * <p>
 * Dear Maintainer:
 * When I wrote the code, only I and God knew what it was.
 * Now, Only God knows!
 * <p>
 * total_hours =
 */
public class CustomTouchListener implements RecyclerView.OnItemTouchListener {

    //Gesture detector to intercept the touch events
    GestureDetector gestureDetector;
    private onItemClickListener clickListener;

    public CustomTouchListener(Context context, final onItemClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {

        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, recyclerView.getChildLayoutPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public interface onItemClickListener {

        public void onClick(View view, int index);
    }

}
