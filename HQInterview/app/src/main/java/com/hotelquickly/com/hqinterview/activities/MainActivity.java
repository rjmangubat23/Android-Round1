package com.hotelquickly.com.hqinterview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hotelquickly.com.hqinterview.R;
import com.hotelquickly.com.hqinterview.models.HqEntity;
import com.hotelquickly.com.hqinterview.utils.Constants;
import com.hotelquickly.com.hqinterview.utils.Utilities;
import com.hotelquickly.com.hqinterview.views.adapters.HqListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    private ArrayList<HqEntity> hqEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        String response = Utilities.loadResource(MainActivity.this);
        hqEntityList = getData(response);
        HqListAdapter hqListAdapter = new HqListAdapter(MainActivity.this, hqEntityList);
        hqListAdapter.setOnItemClickListener(new HqListAdapter.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i = new Intent(MainActivity.this, WebViewActivity.class);
                i.putExtra(Constants.HQ_KEY,
                        hqEntityList.get(position));
                startActivity(i);
            }
        });
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(hqListAdapter);

    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(MainActivity.this);
    }

    private ArrayList<HqEntity> getData(String response) {

        ArrayList<HqEntity> tempList = new ArrayList<>();

        try {
            JSONObject outer = new JSONObject(response);
            Iterator<String> keys = outer.keys();
            HqEntity hqEntity = null;
            String key;
            JSONObject inside;
            while (keys.hasNext()) {
                key = keys.next();
                inside = outer.getJSONObject(key);

                hqEntity = new HqEntity(key);
                hqEntity.setUrl(inside.getString(Constants.URL_KEY)
                                .replaceAll("\\{userId\\}", Constants.USER_ID)
                                .replaceAll("\\{appSecretKey\\}", Constants.APP_SECRET_KEY)
                                .replaceAll("\\{currencyCode\\}", Constants.CURRENCY_CODE)
                                .replaceAll("\\{offerId\\}", Constants.OFFER_ID)
                                .replaceAll("\\{selectedVouchers\\}", Constants.SELECTED_VOUCHERS));
                hqEntity.setIsCacheEnabled(inside.getBoolean(Constants.CACHE_KEY));

                tempList.add(hqEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempList;
    }


}
