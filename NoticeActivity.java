package com.example.studyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        TextView noticeTitle = findViewById(R.id.noticeTitle);
        TextView noticeContent = findViewById(R.id.noticeContent);
        TextView noticeName = findViewById(R.id.noticeName);
        TextView noticeDate= findViewById(R.id.noticeDate);
        Intent intent = getIntent();
        noticeTitle.setText(intent.getStringExtra("noticeTitle"));
        noticeContent.setText(intent.getStringExtra("noticeContent"));
        noticeName.setText(intent.getStringExtra("noticeName"));
        noticeDate.setText(intent.getStringExtra("noticeDate"));
    }
}
