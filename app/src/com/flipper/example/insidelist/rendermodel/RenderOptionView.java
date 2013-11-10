package com.flipper.example.insidelist.rendermodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.model.RenderOptionData;
import com.flipper.example.insidelist.model.RenderViewData;

/**
 * @author flipper83
 */
public class RenderOptionView extends RenderBase {

    TextView option;

    @Override
    public View createRender(Context context, LayoutInflater inflater, ViewGroup parent) {
        base = inflater.inflate(R.layout.listitem_option, parent, false);
        return base;
    }


    @Override
    public void setupView() {
        option = (TextView) base.findViewById(R.id.tv_option);
    }

    @Override
    protected View render(Context context, RenderViewData data) {
        RenderOptionData dataOption = (RenderOptionData) data;
        option.setText(dataOption.getTitle());
        return base;
    }
}
