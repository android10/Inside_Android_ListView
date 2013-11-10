package com.flipper.example.insidelist.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.adapter.ViewHolderAdapterAnimated;
import com.flipper.example.insidelist.component.DebugListView;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.rendermodel.RenderAnimated;

import java.util.ArrayList;

/**
 * @author: Fernando Cejas <fcejas@gmail.com>
 * android10.org
 */
public class InsideListViewAnimatedItems extends InsideBaseListActivity {

    private static final int INIT_VALUES = 100;
    ArrayList<DataModel> listValues = new ArrayList<DataModel>();
    ViewHolderAdapterAnimated adapter;
    private DebugListView lv_list;
    private DisplayMetrics metrics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_list);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mapGUI();
    }

    private void mapGUI() {
        lv_list = (DebugListView) findViewById(R.id.ll_list);
        initAdapter();
    }

    private void initAdapter() {

        //init avatars(this works because resids are consecutive,
        //not good practices)
        //init values to insert
        for (int i = 0; i < INIT_VALUES; i++) {
            DataModel data = new DataModel();
            data.setAvatar(R.drawable.avatar_1 + i);
            data.setTitle("pos " + i);

            listValues.add(data);
        }

        adapter = new ViewHolderAdapterAnimated(getApplicationContext(), listValues, metrics);
        adapter.registerRender(DataModel.class, RenderAnimated.class);

        adapter.setGeneralInfoListener(this);
        lv_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, 1, 0, "No Animation");
        menu.add(Menu.NONE, 2, 0, "Translation Anim 1");
        menu.add(Menu.NONE, 3, 0, "Translation Anim 2");
        menu.add(Menu.NONE, 4, 0, "Scale Anim");
        menu.add(Menu.NONE, 5, 0, "Shake Anim");
        menu.add(Menu.NONE, 6, 0, "Fade In Anim");
        menu.add(Menu.NONE, 7, 0, "Hyperspace Out Anim");
        menu.add(Menu.NONE, 8, 0, "Push Left In Anim");
        menu.add(Menu.NONE, 9, 0, "Push Left Out Anim");
        menu.add(Menu.NONE, 10, 0, "Push Up In Anim");
        menu.add(Menu.NONE, 11, 0, "Push Up Out Anim");
        menu.add(Menu.NONE, 12, 0, "Wave Scale Anim");

        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (adapter != null) {
            adapter.setAnimationMode(item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }
}
