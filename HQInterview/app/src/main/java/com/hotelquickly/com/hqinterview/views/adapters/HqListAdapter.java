package com.hotelquickly.com.hqinterview.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotelquickly.com.hqinterview.R;
import com.hotelquickly.com.hqinterview.models.HqEntity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rj Mangubat on 11/01/16.
 */
public class HqListAdapter extends RecyclerView.Adapter<HqListAdapter.ViewHolder> {

    private Context mContext;
    ArrayList<HqEntity> mHqEntityList;

    private static ClickListener clickListener;

    public HqListAdapter(Context context, ArrayList<HqEntity> hqEntityList) {
        mContext = context;
        mHqEntityList = hqEntityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.webview_item, viewGroup, false)
        );

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mHqEntityList.get(position).getKey());


    }

    @Override
    public int getItemCount() {
        return mHqEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.key)
        protected TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        HqListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }


}
