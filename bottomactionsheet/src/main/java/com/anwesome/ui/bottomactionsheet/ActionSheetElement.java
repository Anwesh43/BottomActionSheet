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
    public ActionSheetElement(String option) {
        this.option = option;
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
        canvas.translate(x,y);
        paint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(-w/2,-h/2,w/2,h/2),paint);
        canvas.save();
        paint.setColor(Color.parseColor("#88FFFFFF"));
        canvas.scale(scale,scale);
        canvas.drawRect(new RectF(-w/2,-h/2,w/2,h/2),paint);
        canvas.restore();
        String optionStr = getAdjustedString(paint);
        canvas.drawText(optionStr,-paint.measureText(optionStr)/2,paint.getTextSize()/2,paint);
        canvas.restore();
    }
    private String getAdjustedString(Paint paint) {
        String msg = "";
        for(int i=0;i<option.length();i++) {
            if(paint.measureText(msg+option.charAt(i))>4*w/5) {
                msg = msg+"...";
            }
            else {
                msg += option.charAt(0);
            }
        }
        return msg;
    }
    public void update() {
        scale+=dir*0.1f;
        if(scale>=1) {
            dir = 0;
            scale = 0;
        }
    }
    public boolean stop() {
        return dir == 0;
    }
    public int hashCode() {
        return option.hashCode()+(int)(x+y+w+h+scale+dir);
    }
}
