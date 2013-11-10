package com.flipper.example.insidelist.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.rendermodel.GeneralInfoListener;

/**
 * Base activity that have all base code for all list activities.
 * @author flipper83
 */
public class InsideBaseListActivity extends Activity implements GeneralInfoListener {


    private FrameLayout sampleContent;

    private int numNewRenders;
    private int numCalls;
    private long totalTime;
    private int avgTime;

    private TextView numNewView;
    private TextView numCallsView;
    private TextView avgTimeView;
    private ImageButton ib_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.screen_base);

        sampleContent = (FrameLayout) findViewById(R.id.sample_content);

        //find headers
        numNewView = (TextView) findViewById(R.id.tv_num_news);
        numCallsView = (TextView) findViewById(R.id.tv_num_calls);
        avgTimeView =(TextView) findViewById(R.id.tv_avg_time);
        ib_refresh = (ImageButton) findViewById(R.id.ib_refresh);

        hookListener();

    }

    private void hookListener() {
        ib_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshList();
            }
        });
    }

    protected void refreshList() {

    }

    @Override
    public void setContentView(int layoutResID) {
        getLayoutInflater().inflate(layoutResID, sampleContent);
    }

    @Override
    public void newRenderCreated() {
        numNewRenders ++;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                numNewView.setText("Num Creations= " + numNewRenders);
            }
        });

    }

    @Override
    public void renderTime(long time) {
        numCalls ++;

        totalTime += (time/1000);
        avgTime = (int)(totalTime / numCalls);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                numCallsView.setText("Calls= "+numCalls);
                avgTimeView.setText("Avg time= "+avgTime);

            }
        });

    }
}
