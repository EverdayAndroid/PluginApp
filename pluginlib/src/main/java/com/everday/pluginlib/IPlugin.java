package com.everday.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/4/13
 * description: 生命周期
 */
public interface IPlugin {
    //从内部跳转
    int FROM_INIERNAL = 0;
    //外部跳转
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
