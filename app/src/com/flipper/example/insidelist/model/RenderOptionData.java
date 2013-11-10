package com.flipper.example.insidelist.model;

import android.app.Activity;

/**
 * @author flipper83
 */
public class RenderOptionData extends RenderViewData{
    private final String title;
    private Class<? extends Activity> openActivity;

    public RenderOptionData(String title , Class<? extends Activity> openActivity){
        this.title = title;
        this.openActivity = openActivity;
    }

    public Class<? extends Activity> getOpenActivity() {
        return openActivity;
    }

    public String getTitle() {
        return title;
    }
}
