package com.marsthink.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marsthink.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListViewCheckBoxActivity extends Activity {

    private ListView listView;
    private List<CheckData> list;
    private Adapter1 adapter1;
    private ImageView imageView;

    private String TAG = "jamal.jo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_checkbox);
        initDate();
        listView = (ListView) findViewById(R.id.listView);
        adapter1 = new Adapter1();
        listView.setAdapter(adapter1);
        imageView = findViewById(R.id.image);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(ListViewCheckBoxActivity.this).load("http://i.imgur.com/DvpvklR.png").into(imageView);
            }
        });
    }


    /**
     * 模拟40个数据，奇数数据为选中状态，偶数数据为非选中状态
     */
    private void initDate() {
        list = new ArrayList<CheckData>();
        CheckData checkData;
        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {
                checkData = new CheckData(i + "号位", false);
                list.add(checkData);
            } else {
                checkData = new CheckData(i + "号位", false);
                list.add(checkData);
            }
        }
    }

    class Adapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(ListViewCheckBoxActivity.this).inflate(
                        R.layout.item_listview_checkbox, null);
                viewHolder.layout = convertView.findViewById(R.id.layout);
                viewHolder.textView = convertView.findViewById(R.id.tv);
                viewHolder.checkBox = convertView.findViewById(R.id.checkbox);
                viewHolder.checkBox.setTag(position);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                Log.d(TAG, "positon: " + position);
            }
            viewHolder.checkBox.setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                boolean isChecked) {
                            list.get(position).isChecked = isChecked;
                            Log.d(TAG, "checked positon: " + position);
                        }
                    });
            viewHolder.textView.setText(list.get(position).name);
            viewHolder.checkBox.setChecked(list.get(position).isChecked);
            return convertView;
        }
    }

    class ViewHolder {
        RelativeLayout layout;
        TextView textView;
        CheckBox checkBox;
    }

    class CheckData {
        public CheckData(String name, boolean isChecked) {
            this.name = name;
            this.isChecked = isChecked;
        }

        boolean isChecked;
        String name;
    }
}