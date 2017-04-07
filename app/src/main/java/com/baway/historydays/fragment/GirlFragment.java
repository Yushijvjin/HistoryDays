package com.baway.historydays.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.historydays.activity.BigImageActivity;
import com.baway.historydays.R;
import com.baway.historydays.adapter.GirlAdapter;
import com.baway.historydays.bean.GirlBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import costomview.library.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/17 20:14
 */

public class GirlFragment extends Fragment {

    private View view;
    private XRecyclerView girl_xrecycler;
    private GirlAdapter girlAdapter;
    private String path = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/11/2";
    private List<GirlBean.ResultsBean> results;
    private int page = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                String grilData = (String) msg.obj;
                Gson gson = new Gson();
                GirlBean girlBean = gson.fromJson(grilData, GirlBean.class);
                if (results == null) {
                    results = girlBean.results;
                } else {
                    List<GirlBean.ResultsBean> beanList = girlBean.results;
                    results.addAll(beanList);
                }
                girlAdapter.setData(results);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.girl, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData(path);
    }

    private void initData(String path) {
        OkHttpUtils.get(path, new Callback() {
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

        girl_xrecycler = (XRecyclerView) view.findViewById(R.id.girl_xrecycler);

        girl_xrecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        girlAdapter = new GirlAdapter(getActivity());
        girl_xrecycler.setAdapter(girlAdapter);
        girl_xrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Random random = new Random();
                page = random.nextInt(41);

                initData("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/11/" + page);
                girl_xrecycler.refreshComplete(); //下拉刷新完成
            }

            @Override
            public void onLoadMore() {
                Random random = new Random();
                page = random.nextInt(41);
                initData("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/11/" + page);
                girl_xrecycler.loadMoreComplete();//加载更多完成
            }
        });
        girlAdapter.setOnItemClickLitener(new GirlAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in = new Intent(getActivity(), BigImageActivity.class);
                in.putExtra("big", results.get(position - 1).url);
                startActivity(in);
            }
        });
    }
}
