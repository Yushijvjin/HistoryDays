package com.baway.historydays.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.historydays.R;
import com.baway.historydays.db.DetauilsBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/28 22:51
 */

public class CollectAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<DetauilsBean> list;


    public CollectAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<DetauilsBean> list) {
        this.list = list;
        this.notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if (view == null) {
            holder = new MyViewHolder();
            view = View.inflate(context, R.layout.collect_item, null);
            holder.collect_date = (TextView) view.findViewById(R.id.collect_date);
            holder.collect_title = (TextView) view.findViewById(R.id.collect_title);
            holder.collect_image = (ImageView) view.findViewById(R.id.collect_image);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }

        holder.collect_date.setText(list.get(i).getDate());
        holder.collect_title.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getUrl()).into(holder.collect_image);
        return view;
    }

    class MyViewHolder {

        private TextView collect_date;
        private TextView collect_title;
        private ImageView collect_image;

    }
}
