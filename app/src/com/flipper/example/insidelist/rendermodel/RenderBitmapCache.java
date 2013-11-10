package com.flipper.example.insidelist.rendermodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author flipper83
 */
public class RenderBitmapCache extends RenderDebug {

    ImageView imageBuffer;

    @Override
    public View createRender(Context context, LayoutInflater inflater, ViewGroup parent) {
        View base = super.createRender(context, inflater, parent);

        imageBuffer = new ImageView(context);

        return base;
    }

    public ImageView getImageBuffer() {
        return imageBuffer;
    }
}
