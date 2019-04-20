package com.everday.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.FileHandler;

import dalvik.system.DexClassLoader;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/4/13
 * description: 加载插件apk
 */
public class PluginManager {

    private static final PluginManager instance = new PluginManager();
    private PluginManager(){}
    public static PluginManager getInstance(){
        return instance;
    }

    private Context mContext;
    private PluginApk mPluginApk;

    public void init(Context context){
        mContext = context.getApplicationContext();
    }
    //加载插件apk
    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES
                | PackageManager.GET_SERVICES);
        if(packageInfo == null){
            return;
        }

        DexClassLoader classLoader = createDexClassLoader(apkPath);
        AssetManager am = createAssetManager(apkPath);
        Resources resources = createResource(am);
        mPluginApk = new PluginApk(packageInfo,classLoader,am,resources);
    }

    public PluginApk getPluginApk() {
        return mPluginApk;
    }

    //创建访问插件apk的DexClassLoader
    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }
    //创建访问插件apk资源的Aseetmanager对象
    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(am,apkPath);
            return am;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
    //创建访问插件apk资源的Resource对象
    private Resources createResource(AssetManager am) {
        Resources resources = mContext.getResources();
        return new Resources(am,resources.getDisplayMetrics(),resources.getConfiguration());
    }

}
