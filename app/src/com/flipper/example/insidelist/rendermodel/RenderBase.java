package com.flipper.example.insidelist.rendermodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.model.RenderViewData;

import java.util.Random;

/**
 * @author flipper83
 *         <p/>
 *         This class contains the render model info and render the cell.
 */
public abstract class RenderBase {

    private static final String LOGTAG = "RenderBase";

    protected View base;

    public abstract  View createRender(Context context, LayoutInflater inflater, ViewGroup parent) ;

    public abstract void setupView();

    public final View renderView(Context context, RenderViewData data){
        long timeOnNano = System.nanoTime();

        View view = render(context, data);

        long timeRendering = (System.nanoTime() - timeOnNano)/1000;

        Log.v(LOGTAG, "time Render ("+this+")"+"= "+timeRendering+" µs");

        return view;
    }
    protected abstract View render(Context context, RenderViewData data);

    public void setBaseView(View baseView) {
        this.base = baseView;
    }
}
