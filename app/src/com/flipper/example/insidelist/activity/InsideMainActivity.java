package com.flipper.example.insidelist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.adapter.OptionsAdapter;
import com.flipper.example.insidelist.model.RenderOptionData;
import com.flipper.example.insidelist.rendermodel.RenderOptionView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flipper83
 */
public class InsideMainActivity extends Activity {

    private ListView ll_options;
    private OptionsAdapter adapter;
    private List<RenderOptionData> optionViewData = new ArrayList<RenderOptionData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_main);

        ll_options = (ListView) findViewById(R.id.ll_options);

        initData();

        adapter = new OptionsAdapter(getBaseContext(), optionViewData);
        adapter.registerRender(RenderOptionData.class,RenderOptionView.class);

        ll_options.setAdapter(adapter);

        ll_options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RenderOptionData optionData = optionViewData.get(position);
                startActivity(new Intent(getApplicationContext(), optionData.getOpenActivity()));

            }
        });
    }

    private void initData() {
        optionViewData.add(new RenderOptionData("List that not recycle", NoRecycleActivity.class));
        optionViewData.add(new RenderOptionData("List recycle", RecycleActivity.class));
        optionViewData.add(new RenderOptionData("List recycle and view holders", ViewHolderActivity.class));
        optionViewData.add(new RenderOptionData("List that cache all to Bitmap", RecicleBitmapActivity.class));
        optionViewData.add(new RenderOptionData("Simple List item animation", InsideListViewAnimatedItems.class));
        optionViewData.add(new RenderOptionData("List item animation with transient state", InsideListViewAnimatedItemsTransient.class));
    }
}
