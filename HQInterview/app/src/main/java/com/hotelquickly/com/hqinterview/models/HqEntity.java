package com.hotelquickly.com.hqinterview.models;

import java.io.Serializable;

/**
 * Created by Rj Mangubat on 11/01/16.
 */
public class HqEntity implements Serializable {

    private String key;
    private String url;
    private boolean isCacheEnabled;

    public HqEntity(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public boolean isCacheEnabled() {
        return isCacheEnabled;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIsCacheEnabled(boolean isCacheEnabled) {
        this.isCacheEnabled = isCacheEnabled;
    }

}
