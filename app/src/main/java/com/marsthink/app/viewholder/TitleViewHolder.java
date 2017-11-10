package com.marsthink.app.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marsthink.app.R;

public class TitleViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView tv;

    public TitleViewHolder(View view) {
        super(view);
        this.view = view;
        tv = (TextView) view.findViewById(R.id.id_title);
    }

    public View getView() {
        return view;
    }

    public void setTitle(String title) {
        tv.setText(title);
    }
}