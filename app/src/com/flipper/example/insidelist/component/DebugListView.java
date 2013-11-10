package com.flipper.example.insidelist.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

public class DebugListView extends ListView {

    private static final String LOGTAG = "DebugListView";
    private float media;

    public DebugListView(Context context) {
        super(context);
    }

    public DebugListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DebugListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void requestLayout() {
        printTrace("requestLayout");
        super.requestLayout();

    }

    @Override
    public void forceLayout() {
        printTrace("forceLayout");
        super.forceLayout();
    }

    void printTrace(String method){
        StringBuilder stringBuilder = new StringBuilder();

        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        stringBuilder.append("-->");
        stringBuilder.append(method);
        stringBuilder.append(" ");
        for (StackTraceElement value : traces) {
            stringBuilder.append(value.getClassName());
            stringBuilder.append(".");
            stringBuilder.append(value.getMethodName());
            stringBuilder.append('\n');
        }

        Log.d(LOGTAG,stringBuilder.toString());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long timeStart = System.nanoTime();

        super.onDraw(canvas);

        //calculate time
        long timeEnd = System.nanoTime();
        long timeDiff = timeEnd - timeStart;
        if (media == 0) {
            media = timeDiff;
        } else {
            media = (media + timeDiff) / 2;
        }

        Log.d(LOGTAG, "timeElapse= " + timeDiff + " media= " + media);
    }

}
