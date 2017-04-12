package com.anwesome.ui.bottomactionsheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class BottomActionSheetView extends View {
    private List<ActionSheetElement> actionSheetElementList = new ArrayList<>();
    private boolean isAnimated = false;
    private int time = 0,newY = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ConcurrentLinkedQueue<ActionSheetElement> elements = new ConcurrentLinkedQueue<>();
    public void setActionSheetElementList(List<ActionSheetElement> actionSheetElementList) {
        this.actionSheetElementList = actionSheetElementList;
    }
    public BottomActionSheetView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            int w = canvas.getWidth(),h = canvas.getHeight(),y=h-h/25*(actionSheetElementList.size());
            newY = y;
            for(ActionSheetElement actionSheetElement:actionSheetElementList) {
                actionSheetElement.setDimension(0,y,w,h/25);
                y+=h/25;
            }
        }
        for(ActionSheetElement actionSheetElement:actionSheetElementList) {
            actionSheetElement.draw(canvas,paint);
        }
        time++;
        if(isAnimated) {
            for(ActionSheetElement element:elements) {
                element.update();
                if(element.stop()) {
                    elements.remove(element);
                    if(elements.size() == 0) {
                        isAnimated = false;
                    }
                }
            }
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public float getNewY() {
        return newY;
    }
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(),y = event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(y>newY) {
                for (ActionSheetElement actionSheetElement : actionSheetElementList) {
                    if (actionSheetElement.handleTap(x, y - newY)) {
                        elements.add(actionSheetElement);
                        if(!isAnimated) {
                            isAnimated = true;
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }
}
