package com.flipper.example.insidelist.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import com.flipper.example.insidelist.R;
import com.flipper.example.insidelist.adapter.DebugAdapter;
import com.flipper.example.insidelist.component.DebugListView;
import com.flipper.example.insidelist.model.DataModel;
import com.flipper.example.insidelist.rendermodel.RenderDebug;

import java.util.ArrayList;

/**
 * @author: Fernando Cejas <fcejas@gmail.com>
 * android10.org
 */
public class InsideListViewAnimatedItemsTransient extends InsideBaseListActivity {

    private static final int INIT_VALUES = 100;
    ArrayList<DataModel> listValues = new ArrayList<DataModel>();
    DebugAdapter adapter;
    private DebugListView lv_list;
    private CheckBox cb_vpa;
    private CheckBox cb_setTransientState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_list_transient);

        mapGUI();
    }

    private void mapGUI() {
        cb_vpa = (CheckBox) findViewById(R.id.cb_vpa);
        cb_setTransientState = (CheckBox) findViewById(R.id.cb_setTransientState);

        lv_list = (DebugListView) findViewById(R.id.ll_list);
        initAdapter();

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final DataModel item = (DataModel) parent.getItemAtPosition(position);
                if (cb_vpa.isChecked()) {
                    view.animate().setDuration(1000).alpha(0).
                            withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    listValues.remove(item);
                                    adapter.notifyDataSetChanged();
                                    view.setAlpha(1);
                                }
                            });
                } else {
                    // Here's where the problem starts - this animation will animate a View object.
                    // But that View may get recycled if it is animated out of the container,
                    // and the animation will continue to fade a view that now contains unrelated
                    // content.
                    ObjectAnimator anim = ObjectAnimator.ofFloat(view, View.ALPHA, 0);
                    anim.setDuration(1000);
                    if (cb_setTransientState.isChecked()) {
                        // Here's the correct way to do this: if you tell a view that it has
                        // transientState, then ListView ill avoid recycling it until the
                        // transientState flag is reset.
                        // A different approach is to use ViewPropertyAnimator, which sets the
                        // transientState flag internally.
                        view.setHasTransientState(true);
                    }
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            listValues.remove(item);
                            adapter.notifyDataSetChanged();
                            view.setAlpha(1);
                            if (cb_setTransientState.isChecked()) {
                                view.setHasTransientState(false);
                            }
                        }
                    });
                    anim.start();
                }
            }

        });
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
}
