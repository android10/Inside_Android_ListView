package com.flipper.example.insidelist.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.model.RenderViewData;
import com.flipper.example.insidelist.rendermodel.GeneralInfoListener;
import com.flipper.example.insidelist.rendermodel.RenderBase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author flipper83
 */
public class RenderBuilder {

    private Map<Class<? extends RenderViewData>, Class<? extends RenderBase>> registerRender =
            new HashMap<Class<? extends RenderViewData>, Class<? extends RenderBase>>();
    private Context context;
    private LayoutInflater inflater;
    private GeneralInfoListener generalInfoListener;
    private boolean deactivateRecycle = false;
    private boolean deactivateViewHolder = false;


    public RenderBuilder(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;

    }

    /**
     * this return a valid render
     * @param renderViewData
     * @param convertView
     * @param parent
     * @return
     */
    public RenderBase obtainVideoRender(RenderViewData renderViewData, View convertView, ViewGroup parent) {
        Class<? extends RenderBase> renderModelType = registerRender.get(renderViewData.getClass());

        if (renderModelType == null) {
            throw new IllegalArgumentException("not exist any RenderBase register for this dataModel");
        }

        //Create a new instance of the view Model or reuse the old one
        RenderBase viewModel;
        if (convertView != null && !deactivateRecycle && !deactivateViewHolder) {
            viewModel = (RenderBase) convertView.getTag();
            viewModel.setBaseView(convertView);
        } else {
            try {
                viewModel = renderModelType.newInstance();
            } catch (InstantiationException e) {
                throw new IllegalStateException("can not create a new renderModel");
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("can not create a new renderModel");
            }

            if (convertView == null || deactivateRecycle) {
                convertView = viewModel.createRender(context, inflater, parent);
            } else {
                viewModel.setBaseView(convertView);
            }
            viewModel.setupView();

            if (!deactivateViewHolder && !deactivateRecycle) {
                convertView.setTag(viewModel);
            }

            if (generalInfoListener != null) {
                generalInfoListener.newRenderCreated();
            }
        }

        return viewModel;

    }

    public void registerRender(Class<? extends RenderViewData> model, Class<? extends RenderBase> render) {
        registerRender.put(model, render);
    }


    //this methods are for debug propouse

    public void setGeneralInfoListener(GeneralInfoListener generalInfoListener) {
        this.generalInfoListener = generalInfoListener;
    }

    public void notifyTotalRenderTime(long time) {
        if (generalInfoListener != null) {
            generalInfoListener.renderTime(time);
        }
    }

    public void deactivateRecycle() {
        this.deactivateRecycle = true;
    }

    public void deactivateViewHolder() {
        this.deactivateViewHolder = true;
    }
}
