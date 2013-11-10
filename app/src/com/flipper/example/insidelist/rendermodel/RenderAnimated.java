package com.flipper.example.insidelist.rendermodel;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.model.RenderViewData;

/**
 * @author flipper83
 */
public class RenderAnimated extends RenderDebug {

    private DisplayMetrics metrics;
    private int mode = 1;

    @Override
    protected View render(Context context, RenderViewData data) {
        View base =  super.render(context, data);

        //Let's animate views
        Animation animation = buildAnimation(context);
        if (animation != null) {
            animation.setDuration(500);
            base.startAnimation(animation);
        }

        return base;
    }

    public void setMetrics(DisplayMetrics metrics){
        this.metrics = metrics;
    }

    public void setAnimationMode(int animationMode) {
        this.mode = animationMode;
    }

    private Animation buildAnimation(Context context) {
        Animation animation = null;

        // Not a good practice do it this way but for
        // demonstration purpose
        switch(mode) {
            case 1:
                //non animation
                animation = new Animation() {};
                break;
            case 2:
                animation = new TranslateAnimation(metrics.widthPixels / 2, 0, 0, 0);
                break;
            case 3:
                animation = new TranslateAnimation(0, 0, metrics.heightPixels, 0);
                break;
            case 4:
                animation = new ScaleAnimation((float)1.0, (float)1.0 ,(float)0, (float)1.0);
                break;
            case 5:
                animation = AnimationUtils.loadAnimation(context, R.anim.shake);
                break;
            case 6:
                animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
                break;
            case 7:
                animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_out);
                break;
            case 8:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
                break;
            case 9:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_left_out);
                break;
            case 10:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
                break;
            case 11:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_up_out);
                break;
            case 12:
                animation = AnimationUtils.loadAnimation(context, R.anim.wave_scale);
                break;
        }

        return animation;
    }
}
