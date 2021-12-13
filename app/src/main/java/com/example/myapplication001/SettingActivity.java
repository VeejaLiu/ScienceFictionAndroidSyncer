package com.example.myapplication001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 新版隐藏标题栏的方法
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_setting);
        Button returnHomepageButton = (Button) findViewById(R.id.returnHomepage);
        returnHomepageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backDataToMainActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        backDataToMainActivity();
        super.onBackPressed();
    }


    public void backDataToMainActivity() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "设置已更新");
        setResult(RESULT_OK, intent);
        finish();
    }
}