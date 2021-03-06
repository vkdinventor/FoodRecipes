package com.vkdinventor.app.foodrecipes.recipesmain.ui;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by einfochips on 19/6/17.
 */

public class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private SwipeGestureListener listener;

    public SwipeGestureDetector(SwipeGestureListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float swipeValue = e2.getX()-e1.getX();

        Log.v("vikash","swipe test: swipe value :"+swipeValue+" vleocity :"+velocityX);

        if(Math.abs(swipeValue) > SWIPE_THRESHOLD  && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
            if(swipeValue > 0){
                Log.v("vikash","swipe right :"+swipeValue);
                listener.onKeep();
            }else {
                Log.v("vikash","swipe left to dismiss :" +swipeValue);
                listener.onDismiss();
            }
        }

        return true;
    }
}
