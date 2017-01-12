package com.example.hoanglg.hooheyhow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoanglg.hooheyhow.model.TopItem;

import java.util.List;

/**
 * Created by HoangLG on 1/11/2017.
 */

public class TopItemAdapter extends RecyclerView.Adapter<TopItemAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mAmountTextView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_icon);
            mAmountTextView = (TextView) itemView.findViewById(R.id.text_amount);
        }

    }

    private List<TopItem> mTopItemList;
    private Context mContext;

    public TopItemAdapter(List<TopItem> topItemList, Context context) {
        mTopItemList = topItemList;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //inflate the custom layout
        View topItemLayout = inflater.inflate(R.layout.item_top_screen, parent, false);
        //return a new holder instance
        return new ViewHolder(topItemLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TopItem topItem = mTopItemList.get(position);
        ImageView imageView = holder.mImageView;
        final TextView textView = holder.mAmountTextView;
        imageView.setImageResource(topItem.getDrawableResource());
        textView.setText(String.valueOf(topItem.getAmount()));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(topItem.increaseBet()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTopItemList == null ? 0 : mTopItemList.size();
    }


}
