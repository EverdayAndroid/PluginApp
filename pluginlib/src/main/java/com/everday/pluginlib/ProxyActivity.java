package com.everday.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

public class ProxyActivity extends Activity {
    private String mClassName;
    private PluginApk mPluginApk;
    private IPlugin iPlugin;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstance().getPluginApk();

        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if(mPluginApk == null){
            try {
                throw new Exception("加载不了apk文件");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Class<?> clazz = mPluginApk.mDexClassLoader.loadClass(mClassName);
            //实例化Activity，注意：这里的Activity是没有生命周期，也没有上下文
            Object instance = clazz.newInstance();
            if(instance instanceof IPlugin) {
                iPlugin = (IPlugin) instance;
                iPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                iPlugin.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk != null ? mPluginApk.mResources:super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk != null ? mPluginApk.mAssetManager:super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk != null ? mPluginApk.mDexClassLoader:super.getClassLoader();
    }
}
