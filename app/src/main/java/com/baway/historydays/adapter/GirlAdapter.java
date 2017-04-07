package com.baway.historydays.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baway.historydays.R;
import com.baway.historydays.bean.GirlBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/18 8:47
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder> {

    private Context context;
    private List<GirlBean.ResultsBean> results;

    public GirlAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<GirlBean.ResultsBean> results) {
        this.results = results;
        this.notifyDataSetChanged();

    }


    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlViewHolder(LayoutInflater.from(context).inflate(R.layout.girl_item, parent, false));
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final GirlViewHolder holder, int position) {
        Glide.with(context).load(results.get(position).url).into(holder.girl_image);
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.girl_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder {
        private ImageView girl_image;

        public GirlViewHolder(View itemView) {
            super(itemView);
            girl_image = (ImageView) itemView.findViewById(R.id.girl_item_image);
        }
    }
}
