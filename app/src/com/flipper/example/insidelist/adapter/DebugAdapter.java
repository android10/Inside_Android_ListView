package com.flipper.example.insidelist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.rendermodel.RenderBase;
import com.flipper.example.insidelist.rendermodel.RenderDebug;

import java.util.List;

public class DebugAdapter extends BaseInsideAdapter {

    public DebugAdapter(Context context, List<DataModel> values) {
        super(context, values);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        long timeStart = System.nanoTime();

        DataModel data = (DataModel) getItem(position);

        RenderBase render = renderBuilder.obtainVideoRender(data, convertView, parent);

        //set debug info
        RenderDebug renderDebug = (RenderDebug) render;
        if (renderDebug.isNew()) {
            renderDebug.setId(contRemake);
            contRemake++;
        }

        convertView = render.renderView(context, data);

        //set Debug info
        renderDebug.setOldPosition(position);


        long timeEnd = System.nanoTime();

        renderDebug.setTotalTime((timeEnd - timeStart) / 1000);

        renderBuilder.notifyTotalRenderTime(timeEnd - timeStart);

        return convertView;
    }

    public void deactivateRecycle() {
        renderBuilder.deactivateRecycle();
    }

    public void deactivateViewHolder() {
        renderBuilder.deactivateViewHolder();
    }

}
