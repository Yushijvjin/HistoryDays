package com.baway.historydays.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baway.historydays.R;
import com.baway.historydays.adapter.CollectAdapter;
import com.baway.historydays.db.DetauilsBean;
import com.baway.historydays.db.DetauilsDao;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/17 20:14
 */

public class CollectFragment extends Fragment {

    private View view;
    private ListView collect_listView;
    private CollectAdapter collectAdapter;
    private ArrayList<DetauilsBean> query;
    private DetauilsDao dao;
    private MaterialRefreshLayout materialRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.collect, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();

    }

    private void initData() {
        dao = new DetauilsDao(getActivity());
        if (query == null) {
            query = dao.query();
            collectAdapter.setData(query);

        } else {
            query.clear();
            query = dao.query();
            collectAdapter.setData(query);
        }
    }

    private void initView() {
        collect_listView = (ListView) view.findViewById(R.id.collect_listView);
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        collectAdapter = new CollectAdapter(getActivity());
        collect_listView.setAdapter(collectAdapter);


        collect_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.icon);
                builder.setTitle("是否删除");
//                builder.setMessage("Message");
                builder.setPositiveButton("确认删除",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
//                                setTitle("点击了对话框上的Button1");
                                query.remove(i);
                                collectAdapter.notifyDataSetChanged();

                            }
                        });
//                builder.setNeutralButton("Button2",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                setTitle("点击了对话框上的Button2");
//                            }
//                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
//                                setTitle("点击了对话框上的Button3");
                            }
                        });
                builder.show();

                return false;
            }
        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                query.clear();
                query = dao.query();
                collectAdapter.setData(query);

                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onfinish() {
                Toast.makeText(getActivity(), "finish", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                Toast.makeText(getActivity(), "load more", Toast.LENGTH_LONG).show();
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 3000);
            }
        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        collectAdapter.notifyDataSetChanged();
//    }
}
