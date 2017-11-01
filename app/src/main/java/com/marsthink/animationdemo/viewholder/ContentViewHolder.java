package com.marsthink.animationdemo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marsthink.animationdemo.R;

public class ContentViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView tv;

    public ContentViewHolder(View view) {
        super(view);
        this.view = view;
        tv = (TextView) view.findViewById(R.id.id_content);
    }

    public View getView() {
        return view;
    }

    public void setContent(String title) {
        tv.setText(title);
    }
}