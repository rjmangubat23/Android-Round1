package com.hotelquickly.com.hqinterview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hotelquickly.com.hqinterview.R;
import com.hotelquickly.com.hqinterview.models.HqEntity;
import com.hotelquickly.com.hqinterview.utils.Constants;
import com.hotelquickly.com.hqinterview.utils.Utilities;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.webView)
    protected WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize the WebView
        webView.getSettings().setSupportZoom(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebViewClient(new WebViewClient());

        Intent i = getIntent();
        HqEntity hqEntity = (HqEntity) i.getSerializableExtra(Constants.HQ_KEY);
        allowCacheAccess(hqEntity.isCacheEnabled());

        // Load the URLs inside the WebView, not in the external web browser
        webView.loadUrl(hqEntity.getUrl());

    }

    private void allowCacheAccess(boolean isCacheEnabled) {
        if (isCacheEnabled) {
            webView.getSettings().setAppCachePath(getCacheDir().getAbsolutePath());
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // load online by default

            if (!Utilities.isNetworkAvailable(WebViewActivity.this)) { // loading offline
                webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        }
    }

    @Override
    public File getCacheDir() {
        return getApplicationContext().getCacheDir();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
