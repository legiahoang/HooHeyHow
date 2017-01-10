package com.example.hoanglg.hooheyhow.model;

/**
 * Created by HoangLG on 1/9/2017.
 */

public class TopItem {
    private String mTag;
    private int mDrawableResource;
    private int mAmount;

    public TopItem(String tag, int drawableResource, int amount) {
        mTag = tag;
        mDrawableResource = drawableResource;
        mAmount = amount;
    }

    public String getTag() {
        return mTag;
    }

    public int getDrawableResource() {
        return mDrawableResource;
    }

    public int getAmount() {
        return mAmount;
    }
}
