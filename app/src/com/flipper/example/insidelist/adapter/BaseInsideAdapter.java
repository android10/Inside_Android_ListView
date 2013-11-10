package com.flipper.example.insidelist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.flipper.example.insidelist.builder.RenderBuilder;
import com.flipper.example.insidelist.model.RenderViewData;
import com.flipper.example.insidelist.rendermodel.GeneralInfoListener;
import com.flipper.example.insidelist.rendermodel.RenderBase;

import java.util.List;


/**
 * this adapter will be extend by all adapters, hide some inicializations.
 * @author flipper83
 */
public abstract class BaseInsideAdapter extends BaseAdapter {

    final LayoutInflater inflater;
    final Context context;
    final RenderBuilder renderBuilder;
    protected int contRemake = 0;
    List<? extends RenderViewData> values;

    public BaseInsideAdapter(Context context, List<? extends RenderViewData> values) {
        contRemake = 0;
        this.values = values;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        renderBuilder = new RenderBuilder(context, LayoutInflater.from(context));

    }

    public int getCount() {
        return values.size();
    }

    public Object getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public abstract View getView(int position, View convertView, ViewGroup parent);

    public void setGeneralInfoListener(GeneralInfoListener listener) {
        renderBuilder.setGeneralInfoListener(listener);
    }

    public void registerRender(Class<? extends RenderViewData> dataModelClass, Class<? extends RenderBase> renderClass) {
        renderBuilder.registerRender(dataModelClass, renderClass);
    }
}
