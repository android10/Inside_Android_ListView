package com.flipper.example.insidelist.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.rendermodel.RenderAnimated;
import com.flipper.example.insidelist.rendermodel.RenderBase;
import com.flipper.example.insidelist.rendermodel.RenderDebug;

import java.util.List;

/**
 * @author: Fernando Cejas <fcejas@gmail.com>
 * android10.org
 */
public class ViewHolderAdapterAnimated extends BaseInsideAdapter {

	private final DisplayMetrics metrics;

	//Animation mode
	private int mode = 1;

	public ViewHolderAdapterAnimated(Context context, List<DataModel> values, DisplayMetrics metrics) {
		super(context, values);
		this.metrics = metrics;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

        long timeStart = System.nanoTime();

        DataModel data = (DataModel) getItem(position);

        //we set null to this one for not recycle
        RenderBase render = renderBuilder.obtainVideoRender(data, convertView, parent);

        //set debug info
        RenderDebug renderDebug = (RenderDebug) render;
        if (renderDebug.isNew()) {
            renderDebug.setId(contRemake);
            contRemake++;
        }

        //provide animate info
        ((RenderAnimated)render).setMetrics(metrics);
        ((RenderAnimated)render).setAnimationMode(mode);

        convertView = render.renderView(context, data);

        //set Debug info
        renderDebug.setOldPosition(position);


        long timeEnd = System.nanoTime();

        renderDebug.setTotalTime((timeEnd - timeStart) / 1000);

        renderBuilder.notifyTotalRenderTime(timeEnd - timeStart);

        return convertView;
	}

    public void setAnimationMode(int animationMode) {
        this.mode = animationMode;
    }
}
