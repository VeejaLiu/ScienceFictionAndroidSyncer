package com.example.myapplication001;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * 1. onCreate()方法和 onDestroy()方法之间所经历的，就是完整生存期。
 * 一般情况下，一个活动会在 onCreate()方法中完成各种初始化操作，而在 onDestroy()方法中完成释放内存的操作。
 * 2. onStart()方法和 onStop()方法之间所经历的，就是可见生存期。
 * 在可见生存期内，活动对于用户总是可见的，即便有可能无法和用户进行交互。我们可以通过这两个方法，合理地管理那些对用户可见的资源。
 * 3. onResume()方法和 onPause()方法之间所经历的，就是前台生存期。
 * 在前台生存期内，活动总是处于运行状态的，此时的活动是可以和用户进行相互的，我们平时看到和接触最多的也这个状态下的活动。
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 在活动的第一次被创建的时候调用
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // // 旧版隐藏标题栏的方法，如果该类继承的是Activity类，可以使用这种方法
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // // 新版隐藏标题栏的方法
        // if (getSupportActionBar() != null) {
        //     getSupportActionBar().hide();
        // }

        setContentView(R.layout.activity_main);

        /*
         * 打开主窗口 按钮
         */
        Button openMainButton = (Button) findViewById(R.id.openMain);
        openMainButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /*
         * 拉取最新版本 按钮
         */
        Button pushLatestVersionButton = (Button) findViewById(R.id.pullLatestVersion);
        pushLatestVersionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pulling...", Toast.LENGTH_SHORT).show();
            }
        });

        /*
         * 退出应用 按钮
         */
        Button exitApplicationButton = (Button) findViewById(R.id.exitApplication);
        exitApplicationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
                // finish();
            }
        });
    }

    /**
     * 在活动由 不可见 → 可见 的时候调用
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 在活动准备好喝用户进行交互的时候调用。此时活动一定处于返回栈的栈顶，并且处于运行状态。
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 系统准备去启动或者恢复另外一个活动的时候调用。
     * 我们通常会在这个方法中将一些消耗CPU的资源释放掉，以及保存一些关键数据，但这个方法的执行速度一定要快，不然会影响到新的栈顶活动的使用
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 这个方法是在活动完全不可见的时候调用。它和pause方法的主要区别在于，如果启动的新活动是一个对话框式的活动，那么onPause方法会执行，而onStop不会执行。
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 这个方法在活动被销毁之前调用，之后活动的状态将变为销毁状态。
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 这个方法在活动由 停止 → 运行 状态之前调用，也就是活动被重新启动了。
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 右上角菜单 动作定义
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivityForResult(intent, 1);
                break;
            case R.id.gotoGitRep:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://github.com/VeejaLiu/ScienceFictionCollection"));
                startActivity(intent1);
                break;
            case R.id.callToAuthor:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:18300595221"));
                startActivity(intent2);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Toast.makeText(MainActivity.this, returnedData, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}