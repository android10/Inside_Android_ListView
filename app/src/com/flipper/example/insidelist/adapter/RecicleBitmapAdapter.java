package com.flipper.example.insidelist.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.rendermodel.RenderBase;
import com.flipper.example.insidelist.rendermodel.RenderBitmapCache;
import com.flipper.example.insidelist.rendermodel.RenderDebug;

import java.util.List;
import java.util.Random;

public class RecicleBitmapAdapter extends BaseInsideAdapter {

    private Random random = new Random();
    private LruCache<String, Bitmap> cache;

    public RecicleBitmapAdapter(Context context, List<DataModel> values) {
        super(context, values);

        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        int memorySize = am.getMemoryClass() * 1024 * 1024;
        cache = new LruCache<String, Bitmap>(memorySize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        long timeStart = System.nanoTime();

        DataModel data = (DataModel) getItem(position);

        RenderBase render = renderBuilder.obtainVideoRender(data, convertView, parent);

        //set debug info
        RenderDebug renderDebug = (RenderDebug) render;
        if (renderDebug.isNew()) {
            renderDebug.setId(contRemake);
            contRemake++;
        }


        Bitmap bitmap = cache.get("" + position);
        if (bitmap != null) {

            ImageView imageView = ((RenderBitmapCache) render).getImageBuffer();
            imageView.setImageBitmap(bitmap);
            imageView.setTag(render);
            convertView = imageView;
        } else {

            View base = render.renderView(context, data);

            //this is ugly code but it's only for test propouses
            if (convertView instanceof ImageView) {
                createCacheBitmap(position, base);
                ((ImageView) convertView).setImageBitmap(cache.get("" + position));
                convertView = base;
            } else {
                final View finalConvertView = base;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        createCacheBitmap(position, finalConvertView);
                    }
                }).run();
                convertView = base;
            }
        }

        //end process
        renderDebug.setOldPosition(position);

        long timeEnd = System.nanoTime();

        renderBuilder.notifyTotalRenderTime(timeEnd - timeStart);

        return convertView;
    }

    private void createCacheBitmap(int position, View view) {
        // create bitmap
        if ((view.getWidth() > 0) && (view.getHeight() > 0)) {
            Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                    view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            view.draw(c);

            cache.put("" + position, bitmap);
        }
    }

}
