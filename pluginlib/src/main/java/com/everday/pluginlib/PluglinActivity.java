package com.everday.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PluglinActivity extends Activity implements IPlugin {

    private int mFrom = FROM_INIERNAL;

    private Activity mProxyActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       if(savedInstanceState != null){
           mFrom = savedInstanceState.getInt("FROM");
       }
       if(mFrom == FROM_INIERNAL){
           super.onCreate(savedInstanceState);
           mProxyActivity = this;
       }
    }

    @Override
    public void attach(Activity proxyActivity) {
        this.mProxyActivity = proxyActivity;
    }

    @Override
    public void setContentView(View view) {
        if(mFrom == FROM_INIERNAL){
            super.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mFrom == FROM_INIERNAL){
            super.setContentView(layoutResID);
        }else{
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {
        if(mFrom == FROM_INIERNAL){
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if(mFrom == FROM_INIERNAL){
            super.onRestart();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mFrom == FROM_INIERNAL){
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onResume() {
        if(mFrom == FROM_INIERNAL){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if(mFrom == FROM_INIERNAL){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if(mFrom == FROM_INIERNAL){
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if(mFrom == FROM_INIERNAL){
            super.onDestroy();
        }
    }
}
