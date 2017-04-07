package com.baway.historydays.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.historydays.R;
import com.baway.historydays.activity.HisDetActivity;
import com.baway.historydays.bean.HistoryBean;

import java.util.List;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/18 8:47
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context context;
    private List<HistoryBean.ResultBean> result;
//    private OnItemClickLitener mOnItemClickLitener;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HistoryBean.ResultBean> result) {
        this.result = result;
        this.notifyDataSetChanged();
    }

//    public interface OnItemClickLitener {
//        void onItemClick(View view, int position, String date);
//    }
//
//
//    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item, parent, false));
    }


    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, final int position) {

        holder.history_date.setText(result.get(position).date);
        holder.history_text.setText(result.get(position).title);
//        String e_id = result.get(position).e_id;


        holder.history_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e_id = result.get(position).e_id;
                String date = result.get(position).date;
                Intent intent = new Intent(context, HisDetActivity.class);
                intent.putExtra("pos", e_id);
                intent.putExtra("date", date);
                context.startActivity(intent);


            }
        });

//        // 如果设置了回调，则设置点击事件
//        if (mOnItemClickLitener != null) {
//            holder.history_text.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String date = result.get(position).date;
//                    int pos = holder.getLayoutPosition();
//                    mOnItemClickLitener.onItemClick(holder.history_text, pos,date);
//                }
//            });
//        }


    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView history_date;
        private TextView history_text;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            history_date = (TextView) itemView.findViewById(R.id.history_date);
            history_text = (TextView) itemView.findViewById(R.id.history_text);

        }
    }
}
