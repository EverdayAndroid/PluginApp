package com.everday.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/4/13
 * description: 插件apk信息的实体对象
 */
public class PluginApk {
    public PackageInfo mPackageInfo;

    public DexClassLoader mDexClassLoader;

    public AssetManager mAssetManager;

    public Resources mResources;

    public PluginApk(PackageInfo mPackageInfo, DexClassLoader mDexClassLoader, AssetManager mAssetManager, Resources mResources) {
        this.mPackageInfo = mPackageInfo;
        this.mDexClassLoader = mDexClassLoader;
        this.mAssetManager = mAssetManager;
        this.mResources = mResources;
    }
}
