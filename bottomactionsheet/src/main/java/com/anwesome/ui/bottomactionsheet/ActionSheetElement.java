package com.anwesome.ui.bottomactionsheet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class ActionSheetElement {
    private String option;
    private float x,y,w,h,scale = 0,dir = 0;
    private OnElementClickListener onElementClickListener;
    public ActionSheetElement(String option,OnElementClickListener onElementClickListener) {
        this.option = option;
        this.onElementClickListener = onElementClickListener;
    }
    public void setDimension(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setTextSize(h/3);
        canvas.save();
        canvas.translate(x+w/2,y+h/2);
        paint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(-w/2,-h/2,w/2,h/2),paint);
        canvas.save();
        paint.setColor(Color.parseColor("#99E0E0E0"));
        canvas.scale(scale,scale);
        canvas.drawRect(new RectF(-w/2,-h/2,w/2,h/2),paint);
        canvas.restore();
        String optionStr = getAdjustedString(paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(optionStr,-paint.measureText(optionStr)/2,paint.getTextSize()/2,paint);
        paint.setStrokeWidth(w/90);
        canvas.drawLine(-w/2,h/2,w/2,h/2,paint);

        canvas.restore();
    }
    private String getAdjustedString(Paint paint) {
        String msg = "";
        for(int i=0;i<option.length();i++) {
            if(paint.measureText(msg+option.charAt(i))>4*w/5) {
                msg = msg+"...";
            }
            else {
                msg += option.charAt(i);
            }
        }
        return msg;
    }
    public void update() {
        scale+=dir*0.2f;
        if(scale>=1.2F) {
            dir = 0;
            scale = 0;
            if(onElementClickListener!=null) {
                onElementClickListener.onElementClick();
            }
        }
    }
    public boolean stop() {
        return dir == 0;
    }
    public int hashCode() {
        return option.hashCode()+(int)(x+y+w+h+scale+dir);
    }
    public interface OnElementClickListener {
        void onElementClick();
    }
    public boolean handleTap(float x,float y) {
        boolean condition = x>=this.x && x<=this.x+w && y>=this.y && y<=this.y+h && dir == 0;
        if(condition) {
            dir = 1;
        }
        return condition;
    }
}
