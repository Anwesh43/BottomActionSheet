package com.anwesome.ui.bottomactionsheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class ActionButton extends View {
    private OnClickListener onClickListener;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ActionButton(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth();
        paint.setColor(Color.parseColor("#009688"));
        paint.setStrokeWidth(w/20);
        canvas.drawCircle(w/2,w/2,w/2,paint);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawLine(w/2,w/4,w/4,w/2,paint);
        canvas.drawLine(w/2,w/4,w/2+w/4,w/2,paint);
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(onClickListener!=null) {
            onClickListener.onClick(this);
        }
        return true;
    }
}
