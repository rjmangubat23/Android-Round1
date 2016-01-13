package com.hotelquickly.com.hqinterview.utils;

import android.app.Application;
import android.os.Environment;

import java.io.File;

/**
 * Created by Rj Mangubat on 12/01/16.
 */
public class ApplicationExt extends Application {
    // NOTE: the content of this path will be deleted
    //       when the application is uninstalled (Android 2.2 and higher)
    protected File extStorageAppBasePath;

    protected File extStorageAppCachePath;

    @Override
    public void onCreate() {
        super.onCreate();

        // Check if the external storage is writable
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // Retrieve the base path for the application in the external storage
            File externalStorageDir = Environment.getExternalStorageDirectory();

            if (externalStorageDir != null) {
                extStorageAppBasePath = new File(externalStorageDir.getAbsolutePath() +
                        File.separator + "Android" + File.separator + "data" +
                        File.separator + getPackageName());
            }

            if (extStorageAppBasePath != null) {

                extStorageAppCachePath = new File(extStorageAppBasePath.getAbsolutePath() +
                        File.separator + "cache");

                boolean isCachePathAvailable = true;

                if (!extStorageAppCachePath.exists()) {
                    // Create the cache path on the external storage
                    isCachePathAvailable = extStorageAppCachePath.mkdirs();
                }

                if (!isCachePathAvailable) {
                    // Unable to create the cache path
                    extStorageAppCachePath = null;
                }
            }
        }
    }

    @Override
    public File getCacheDir() {
        // NOTE: this method is used in Android 2.2 and higher

        if (extStorageAppCachePath != null) {
            // Use the external storage for the cache
            return extStorageAppCachePath;
        } else {
            return super.getCacheDir();
        }
    }
}