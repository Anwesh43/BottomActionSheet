package com.anwesome.ui.bottomactionsheet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class ActionSheet {
    private Activity activity;
    private List<ActionSheetElement> elements = new ArrayList<>();
    private BottomActionSheetView bottomActionSheetView;
    private ActionButton actionButton;
    private AnimationController animationController;
    private OverlayView overlayView;
    private int w,h;
    private boolean isShown = false;
    public ActionSheet(Activity activity) {
        this.activity = activity;
    }
    public void addActionSheetElement(ActionSheetElement actionSheetElement) {
        if(!isShown) {
            elements.add(actionSheetElement);
        }
    }
    public void addingViews() {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h= (size.y*9)/10;
            int hBottom = (h/12)*elements.size();
            overlayView = new OverlayView(activity);
            overlayView.setVisibility(View.INVISIBLE);
            activity.addContentView(overlayView,new ViewGroup.LayoutParams(w,h));
            bottomActionSheetView = new BottomActionSheetView(activity);
            bottomActionSheetView.setActionSheetElementList(elements);
            bottomActionSheetView.setY(h);
            bottomActionSheetView.setX(0);
            activity.addContentView(bottomActionSheetView,new ViewGroup.LayoutParams(w,hBottom));
            actionButton = new ActionButton(activity);
            actionButton.setX(w/2-w/20);
            actionButton.setY(19*h/20-h/12);
            activity.addContentView(actionButton,new ViewGroup.LayoutParams(w/10,w/10));

            animationController = new AnimationController(bottomActionSheetView,actionButton,overlayView,19*h/20);
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animationController.toggle();
                }
            });
        }
    }
    public void show() {
        if(!isShown) {
            addingViews();
            isShown = true;
        }
    }
}
