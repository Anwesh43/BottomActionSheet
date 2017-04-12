package com.anwesome.ui.bottomactionsheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class OverlayView extends View {
    public OverlayView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#88000000"));
    }
}
