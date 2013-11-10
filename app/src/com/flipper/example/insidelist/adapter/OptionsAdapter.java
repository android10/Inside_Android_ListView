package com.flipper.example.insidelist.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.activity.InsideListViewAnimatedItems;
import com.flipper.example.insidelist.activity.InsideListViewAnimatedItemsTransient;
import com.flipper.example.insidelist.activity.NoRecycleActivity;
import com.flipper.example.insidelist.activity.RecicleBitmapActivity;
import com.flipper.example.insidelist.activity.RecycleActivity;
import com.flipper.example.insidelist.activity.ViewHolderActivity;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.model.RenderViewData;
import com.flipper.example.insidelist.rendermodel.RenderBase;
import com.flipper.example.insidelist.rendermodel.RenderDebug;

import java.util.List;

/**
 * @author flipper83
 */
public class OptionsAdapter extends BaseInsideAdapter {


    public OptionsAdapter(Context context,List<? extends RenderViewData> values) {
        super(context,values);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RenderViewData data = (RenderViewData) getItem(position);

        RenderBase render = renderBuilder.obtainVideoRender(data, convertView, parent);
        convertView = render.renderView(context, data);
        return convertView;
    }


}
