package com.baway.historydays.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baway.historydays.R;
import com.baway.historydays.activity.CircleCalendarActivity;
import com.baway.historydays.activity.MainActivity;
import com.baway.historydays.adapter.HistoryAdapter;
import com.baway.historydays.bean.HistoryBean;
import com.baway.historydays.calendar.DateUtil;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;

import costomview.library.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/17 20:14
 */

public class HistoryFragment extends Fragment {
    private int year;
    private int month;
    private int day;
    //    private String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date="+month+"/"+day;
    private Button history_calendar;
    private Button history_left;
    private Button history_right;
    private TextView history_date;

    private View view;
    private XRecyclerView history_recycle;
    private HistoryAdapter historyAdapter;
    private List<HistoryBean.ResultBean> result;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                String grilData = (String) msg.obj;
                Gson gson = new Gson();
                HistoryBean historyBean = gson.fromJson(grilData, HistoryBean.class);
//                if (result == null) {
                result = historyBean.result;
//                } else {
//                    List<HistoryBean.ResultBean> beanList = historyBean.result;
//                    result.addAll(beanList);
//                }
                historyAdapter.setData(result);
            }
        }
    };
    private MainActivity activity;
    private int year1;
    private int rili_year;
    private int rili_month;
    private int rili_day;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.history, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        year = DateUtil.getYear();
        month = DateUtil.getMonth();
        day = DateUtil.getCurrentMonthDay();

        initView();
        initData(month, day);
        rili();

    }

    private void rili() {
        activity = (MainActivity) getActivity();

        rili_year = activity.getIntent().getIntExtra("year", 00);
        rili_month = activity.getIntent().getIntExtra("month", 00);
        rili_day = activity.getIntent().getIntExtra("day", 00);

        if (rili_month != 0 && rili_day != 0) {
            year = rili_year;
            month = rili_month;
            day = rili_day;

            history_date.setText(year + "年" + month + "月" + day + "日");
            initData(month, day);
        }

    }

    private void initData(int month, int day) {
        OkHttpUtils.get("http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=" + month + "/" + day, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String data = response.body().string();
                handler.obtainMessage(0, data).sendToTarget();
            }
        });
    }

    private void initView() {
        //                Toast.makeText(getApplication(), "year" + DateUtil.getYear() + "month" + DateUtil.getMonth()+"date"+DateUtil.getCurrentMonthDay(), Toast.LENGTH_LONG).show();

        history_recycle = (XRecyclerView) view.findViewById(R.id.history_recycle);
        history_left = (Button) view.findViewById(R.id.history_left);
        history_right = (Button) view.findViewById(R.id.history_right);
        history_date = (TextView) view.findViewById(R.id.history_date);
        history_date.setText(year + "年" + month + "月" + day + "日");

        history_calendar = (Button) view.findViewById(R.id.history_calendar);
        history_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        historyAdapter = new HistoryAdapter(getActivity());
        history_recycle.setAdapter(historyAdapter);
        history_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                history_recycle.refreshComplete(); //下拉刷新完成
            }
            @Override
            public void onLoadMore() {
                history_recycle.loadMoreComplete();//加载更多完成
            }
        });

        history_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (DateUtil.getMonthDaysNum(year, month - 1) == 31 && day == 1) {
                    month = month - 1;
                    day = 31;
                } else if (DateUtil.getMonthDaysNum(year, month - 1) == 30 && day == 1) {
                    month = month - 1;
                    day = 30;
                } else if (DateUtil.getMonthDaysNum(year, month - 1) == 29 && day == 1) {
                    month = month - 1;
                    day = 29;
                } else if (DateUtil.getMonthDaysNum(year, month - 1) == 28 && day == 1) {
                    month = month - 1;
                    day = 28;
                } else {
                    day = day - 1;
                }


                history_date.setText(year + "年" + month + "月" + day + "日");
                initData(month, day);
            }
        });
        history_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (DateUtil.getMonthDaysNum(year, month) == 31 && day == 31) {
                    month = month + 1;
                    day = 1;
                } else if (DateUtil.getMonthDaysNum(year, month) == 30 && day == 30) {
                    month = month + 1;
                    day = 1;
                } else if (DateUtil.getMonthDaysNum(year, month) == 28 && day == 28) {
                    month = month + 1;
                    day = 1;
                } else if (DateUtil.getMonthDaysNum(year, month) == 29 && day == 29) {
                    month = month + 1;
                    day = 1;
                } else {
                    day = day + 1;
                }


                history_date.setText(year + "年" + month + "月" + day + "日");
                initData(month, day);
            }
        });


//        history_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
//
//
//            }
//        });

        history_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CircleCalendarActivity.class);
                startActivity(intent);

            }
        });
//        historyAdapter.setOnItemClickLitener(new HistoryAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position, String date) {

//                Intent intent = new Intent(getActivity(), HisDetActivity.class);
//                intent.putExtra("pos",position);
//                intent.putExtra("date",date);
//                startActivity(intent);
//
//
//            }
//        });


    }
}
