package com.anwesome.ui.bottomactionsheet;

import android.animation.Animator;
import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class AnimationController implements ValueAnimator.AnimatorUpdateListener,Animator.AnimatorListener {
    private BottomActionSheetView bottomActionSheetView;
    private ActionButton actionButton;
    private ValueAnimator openAnimator,closeAnimator;
    private int dir = 0,h;
    public AnimationController(BottomActionSheetView bottomActionSheetView,ActionButton actionButton,int h) {
        this.bottomActionSheetView  = bottomActionSheetView;
        this.actionButton = actionButton;
        this.h = h;
    }
    public void open() {
        if(openAnimator == null) {
            openAnimator = ValueAnimator.ofFloat(h, h - bottomActionSheetView.getMeasuredHeight());
            openAnimator.setDuration(500);
            openAnimator.addUpdateListener(this);
            openAnimator.addListener(this);
        }
        openAnimator.start();
        dir = 1;
    }
    public void close() {
        if(closeAnimator == null) {
            closeAnimator = ValueAnimator.ofFloat( h - bottomActionSheetView.getMeasuredHeight(),h);
            closeAnimator.setDuration(500);
            closeAnimator.addUpdateListener(this);
            closeAnimator.addListener(this);
            dir = -1;
        }
        closeAnimator.start();
    }
    public void onAnimationUpdate(ValueAnimator animator) {
        if(dir == 1) {
            actionButton.setRotation(180* animator.getAnimatedFraction());
        }
        if(dir == -1) {
            actionButton.setRotation(180*(1-animator.getAnimatedFraction()));
        }
        bottomActionSheetView.setY((float)animator.getAnimatedValue());

    }
    public void onAnimationEnd(Animator animator) {
        if(dir == 1) {

        }
        else if(dir == -1) {

        }
        dir = 0;
    }
    public void onAnimationCancel(Animator animator) {

    }
    public void onAnimationRepeat(Animator animator) {

    }
    public void onAnimationStart(Animator animator) {

    }
}
