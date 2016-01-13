package com.hotelquickly.com.hqinterview.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hotelquickly.com.hqinterview.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Rj Mangubat on 12/01/16.
 */
public class Utilities {

    private Utilities(){
        //Prevent instantiation
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static String loadResource(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.index);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferReader = new BufferedReader(inputReader);
            int n;
            while ((n = bufferReader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            inputStream.close();
        } catch (IOException ioException) {
            return null;
        }

        return writer.toString();
    }

}
