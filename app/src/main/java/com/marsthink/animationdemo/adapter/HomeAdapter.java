package com.marsthink.animationdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marsthink.animationdemo.R;
import com.marsthink.animationdemo.viewholder.ContentViewHolder;
import com.marsthink.animationdemo.viewholder.TitleViewHolder;

public class HomeAdapter extends RecyclerView.Adapter {


    private String[] mData;
    private final static int TYPE_TITLE = 1;
    private final static int TYPE_CONTENT = 2;

    public void setData(String[] data) {
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_TITLE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_title, parent, false);
                return new TitleViewHolder(view);
            case TYPE_CONTENT:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_content, parent, false);
                return new ContentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).setTitle(mData[mData.length - 1]);
        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).setContent(mData[position]);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }
}