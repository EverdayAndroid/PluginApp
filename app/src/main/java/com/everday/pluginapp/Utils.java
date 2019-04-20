package com.everday.pluginapp;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Utils {

    public static String copyAssetAndWrite(Context context,String fileName){
        try{
            File cacheDir = context.getCacheDir();
            if(!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir,fileName);
            if(!outFile.exists()){
                boolean newFile = outFile.createNewFile();
                if(newFile) {
                    InputStream inputStream = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffes = new byte[inputStream.available()];
                    int byteCount;
                    while ((byteCount = inputStream.read(buffes)) != -1) {
                        fos.write(buffes, 0, byteCount);
                    }
                    fos.flush();
                    fos.close();
                    inputStream.close();
                    Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
