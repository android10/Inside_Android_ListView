package com.flipper.example.insidelist.activity;

import android.os.Bundle;
import android.view.Menu;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.adapter.DebugAdapter;
import com.flipper.example.insidelist.component.DebugListView;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.rendermodel.RenderDebug;

import java.util.ArrayList;

public class ViewHolderActivity extends InsideBaseListActivity {

    private static final int INIT_VALUES = 100;
    ArrayList<DataModel> listValues = new ArrayList<DataModel>();
    DebugAdapter adapter;
    private DebugListView lv_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_list);
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

        adapter = new DebugAdapter(getApplicationContext(), listValues);
        adapter.registerRender(DataModel.class, RenderDebug.class);

        adapter.setGeneralInfoListener(this);
        lv_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
