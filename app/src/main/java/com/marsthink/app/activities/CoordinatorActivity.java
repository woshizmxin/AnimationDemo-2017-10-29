package com.marsthink.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marsthink.app.R;
import com.marsthink.app.adapter.HomeAdapter;

/**
 * Created by zhoumao on 2017/11/1.
 * Description:
 */

public class CoordinatorActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private String[] data = {"1", "2", "3", "4", "2", "3", "4",
            "2", "3", "4", "2", "3", "4", "2", "3",
            "2", "3", "4", "2", "3", "4", "2", "3",
            "2", "3", "4", "2", "3", "4", "2", "3",
            "4", "2", "3", "4"};
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        initData();
    }

    void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
    }

    void initData() {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }
}
