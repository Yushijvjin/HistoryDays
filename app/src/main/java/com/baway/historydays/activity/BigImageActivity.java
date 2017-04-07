package com.baway.historydays.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.historydays.R;
import com.bumptech.glide.Glide;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class BigImageActivity extends AppCompatActivity {

    private PhotoView photo_view;
    private PhotoViewAttacher mAttacher;
    private TextView title_text;
    private ImageView title_image;
    private ImageView title_xiazai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        getSupportActionBar().hide();

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String big = intent.getStringExtra("big");
        Glide.with(BigImageActivity.this).load(big).into(photo_view);
        mAttacher = new PhotoViewAttacher(photo_view);
    }

    private void initView() {
        photo_view = (PhotoView) findViewById(R.id.photo_view);

        title_text = (TextView) findViewById(R.id.girl_title_text);
        title_image = (ImageView) findViewById(R.id.girl_title_image);
        title_xiazai = (ImageView) findViewById(R.id.girl_title_xiazai);
        title_xiazai.setVisibility(View.VISIBLE);

        title_text.setText("妹纸");
        title_text.setTextColor(Color.WHITE);
        title_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("11111111111111111","被点击了");
//                Toast.makeText(BigImageActivity.this,"被点击了11111111111",Toast.LENGTH_LONG).show();
                BigImageActivity.this.finish();
            }
        });

        title_xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("22222222","被点击了");
                Toast.makeText(BigImageActivity.this, "下载成功", Toast.LENGTH_LONG).show();
                Random random = new Random();
                int aaa = random.nextInt(100);

                download(aaa);
            }
        });

    }

    private void download(int aaa) {
        Bitmap bitmap = photo_view.getVisibleRectangleBitmap();
        try {


            saveFile(bitmap, aaa + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public void saveFile(Bitmap bm, String fileName) throws IOException {
        File path = getExternalCacheDir();
        File dirFile = new File(path, fileName);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }


}
