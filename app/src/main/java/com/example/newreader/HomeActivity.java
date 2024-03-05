package com.example.newreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button TheThao,ThoiSu,KinhDoanh,Startup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TheThao = (Button) findViewById(R.id.TheThao);
        ThoiSu = (Button) findViewById(R.id.ThoiSu);
        KinhDoanh = (Button) findViewById(R.id.KinhDoanh);
        Startup = (Button) findViewById(R.id.Startup);
        TheThao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Thể thao";
                String linkTheThao = "https://vnexpress.net/rss/the-thao.rss";
                chuyenDuongLink(linkTheThao,name);
            }
        });

        ThoiSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Thời sự";
                String linkThoiSu = "https://vnexpress.net/rss/the-gioi.rss";
                chuyenDuongLink(linkThoiSu,name);
            }
        });

        KinhDoanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi click vào nút Kinh Doanh
                String linkKinhDoanh = "https://vnexpress.net/rss/kinh-doanh.rss";
                String name = "Kinh doanh";
                chuyenDuongLink(linkKinhDoanh,name);
            }
        });

        Startup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi click vào nút Startup
                String linkStartup = "https://vnexpress.net/rss/phap-luat.rss";
                String name = "Pháp luật";
                chuyenDuongLink(linkStartup,name);
            }
        });
    }

    private void chuyenDuongLink(String link, String name) {
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        intent.putExtra("link",link);
        intent.putExtra("Title",name);
        startActivity(intent);
    }
}