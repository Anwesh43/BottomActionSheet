package com.anwesome.ui.bottomactionsheet;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class AnimationController implements ValueAnimator.AnimatorUpdateListener,Animator.AnimatorListener {
    private BottomActionSheetView bottomActionSheetView;
    private ActionButton actionButton;
    private ValueAnimator openAnimator,closeAnimator;
    private int dir = -1,h;
    private OverlayView overlayView;
    public AnimationController(BottomActionSheetView bottomActionSheetView,ActionButton actionButton,OverlayView overlayView,int h) {
        this.bottomActionSheetView  = bottomActionSheetView;
        this.actionButton = actionButton;
        this.overlayView = overlayView;
        this.h = h;
    }
    private void open() {
        if(openAnimator == null) {
            openAnimator = ValueAnimator.ofFloat(h, h - bottomActionSheetView.getMeasuredHeight());
            openAnimator.setDuration(500);
            openAnimator.addUpdateListener(this);
            openAnimator.addListener(this);
        }
        openAnimator.start();
        dir = 1;
    }
    private void close() {
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
            overlayView.setVisibility(View.INVISIBLE);
        }
    }
    public void onAnimationCancel(Animator animator) {

    }
    public void onAnimationRepeat(Animator animator) {

    }
    public void onAnimationStart(Animator animator) {

    }
    public void toggle() {
        if(dir == 1) {
            close();
        }
        else if(dir == -1) {
            open();
        }
    }
}
