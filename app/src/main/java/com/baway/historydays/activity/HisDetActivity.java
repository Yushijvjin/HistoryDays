package com.baway.historydays.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.historydays.R;
import com.baway.historydays.bean.HisDetBean;
import com.baway.historydays.db.DetauilsDao;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import costomview.library.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class HisDetActivity extends AppCompatActivity implements View.OnClickListener {

    private String path = "http://v.juhe.cn/todayOnhistory/queryDetail.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&e_id=";
    private HisDetBean.ResultBean resultBean;
    private ImageView deta_background;
    private ImageView deta_like;
    private TextView deta_content;
    private String pos;
    private Toolbar toolbar;
    private DetauilsDao dao;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                String obj = (String) msg.obj;
                Gson gson = new Gson();
                HisDetBean hisDetBean = gson.fromJson(obj, HisDetBean.class);
                resultBean = hisDetBean.result.get(0);
                deta_content.setText(resultBean.content);
                toolbar.setTitle(resultBean.title);
                toolbar.setTitleTextColor(Color.WHITE);

                List<HisDetBean.ResultBean.PicUrlBean> picUrl = resultBean.picUrl;
                if (picUrl.size() == 0) {
//                    Glide.with(HisDetActivity.this).load("http://images.juheapi.com/history/1_2.jpg").into(deta_background);

                } else {
                    String url = picUrl.get(0).url;
                    Glide.with(HisDetActivity.this).load(url).into(deta_background);

                }
            }
        }
    };

    int flag = 0;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_det);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        pos = intent.getStringExtra("pos");
        date = intent.getStringExtra("date");
        Toast.makeText(this, "" + pos, Toast.LENGTH_LONG).show();


        initView();
        initData(pos);

    }

    private void initView() {
        dao = new DetauilsDao(this);
        deta_background = (ImageView) findViewById(R.id.deta_background);
        deta_like = (ImageView) findViewById(R.id.deta_like);
        deta_content = (TextView) findViewById(R.id.deta_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.finish_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        deta_like.setOnClickListener(this);

    }

    private void initData(String position) {

        OkHttpUtils.get("http://v.juhe.cn/todayOnhistory/queryDetail.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&e_id=" + position, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                handler.obtainMessage(0, string).sendToTarget();

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deta_like:

                if (flag == 0) {
                    flag = 1;

                    deta_like.setImageResource(R.drawable.ic_toolbar_like_p);

                    String content = resultBean.content;
                    String title = resultBean.title;
                    Log.i("-------","content:"+content+"title:"+title+"date:"+date+"flag:"+flag);
                    if (resultBean.picUrl.size() != 0) {
                        String url = resultBean.picUrl.get(0).url;
                        Log.i("++++++++","url:"+url);
                        dao.add(url, content,date, title, flag);
                    } else {
                        dao.add("http://images.juheapi.com/history/1_2.jpg", content, date,title, flag);
                    }
                }else if (flag==1){
                    flag=0;
                    deta_like.setImageResource(R.drawable.ic_unlike);

//                    dao.delete();
//                    ArrayList<DetauilsBean> query = dao.query();
//                    Log.i("title",query.size()+"--------");
                }
                break;
        }
    }
}
