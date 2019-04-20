package com.everday.pluginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.everday.pluginlib.PluginManager;
import com.everday.pluginlib.PluglinActivity;
import com.everday.pluginlib.ProxyActivity;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);
        findViewById(R.id.jiazai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取apk路径
                String apkPath = Utils.copyAssetAndWrite(MainActivity.this,"pluginapk-debug.apk");

                //加载apk
                PluginManager.getInstance().loadApk(apkPath);

            }
        });

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //指定跳转的类型
                Intent intent =new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.everday.pluginapk.ChajianActivity");
                startActivity(intent);

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
