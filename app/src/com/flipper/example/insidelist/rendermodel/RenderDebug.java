package com.flipper.example.insidelist.rendermodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class RenderDebug extends RenderBase{


    private int id;
    private int oldPosition;
    private TextView tv_title;
    private ImageView iv_avatar;
    private boolean newRow = true;

    private Random random = new Random();
    private TextView tv_render_time;
    private TextView tv_calls;

    private long timeFindById;
    private TextView tv_total_time;

    @Override
    public View createRender(Context context, LayoutInflater inflater, ViewGroup parent) {

        base = inflater.inflate(R.layout.listitem_example_bitmap, parent,
                false);

        this.newRow = true;

        return base;
    }

    public void setupView() {
        long start = System.nanoTime();

        this.tv_title = (TextView) base
                .findViewById(R.id.tv_title);
        this.iv_avatar = (ImageView) base.findViewById(R.id.iv_avatar);
        this.tv_render_time = (TextView) base.findViewById(R.id.tv_render_time);
        this.tv_calls = (TextView) base.findViewById(R.id.tv_render_calls);
        this.tv_total_time = (TextView) base.findViewById(R.id.tv_total_time);

        timeFindById = (System.nanoTime() - start)/1000;
    }


    protected View render(Context context, RenderViewData dataBase) {
        DataModel data = (DataModel) dataBase;

        //inc data mode render calls
        data.incCalls();

        long start = System.nanoTime();

        if (newRow) {
            base.setBackgroundColor(context.getResources().getColor(
                    R.color.red));
        } else {
            base.setBackgroundColor(context.getResources().getColor(
                    R.color.green));
        }


        tv_title.setText(id + ".- " + " old " + oldPosition + " " + data.getTitle());

        //we do this bitmap factory all time, because if I set directly from resource, it's will be cache by resource
        //manager
        Bitmap avatar = BitmapFactory.decodeResource(context.getResources(),data.getAvatar());
        iv_avatar.setImageBitmap(avatar);

         //this code depends of android cache
        //iv_avatar.setImageResource(data.getAvatar());

        tv_calls.setText("num Calls= "+data.getCalls());

        long end = System.nanoTime();
        long timeRender = (end - start)/1000;

        tv_render_time.setText("render = "+ timeRender + " findById ="+ timeFindById );

        //reset timers
        timeFindById = 0;

        return base;
    }

    public void setOldPosition(int oldPosition) {
        this.oldPosition = oldPosition;
    }


    public void setTotalTime(long totalTime) {
        tv_total_time.setText("total time="+totalTime);
    }

    public boolean isNew(){
        return newRow;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setBaseView(View baseView) {
        super.setBaseView(baseView);
        newRow = false;
    }
}
