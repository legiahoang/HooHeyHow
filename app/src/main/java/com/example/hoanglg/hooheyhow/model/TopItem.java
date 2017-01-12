package com.example.hoanglg.hooheyhow.model;

import com.example.hoanglg.hooheyhow.Constants;
import com.example.hoanglg.hooheyhow.R;

import java.util.ArrayList;
import java.util.stream.Stream;

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

    public void setAmount(int amount) {
        mAmount = amount;
    }

    public static ArrayList<TopItem> createTopItemList() {
        ArrayList<TopItem> topItems = new ArrayList<>();
        TopItem mTopItemBau = new TopItem(Constants.BAU, R.drawable.bau, 0);
        TopItem mTopItemCa = new TopItem(Constants.CA, R.drawable.ca, 0);
        TopItem mTopItemCua = new TopItem(Constants.CUA, R.drawable.cua, 0);
        TopItem mTopItemGa = new TopItem(Constants.GA, R.drawable.ga, 0);
        TopItem mTopItemHuou = new TopItem(Constants.HUOU, R.drawable.huou, 0);
        TopItem mTopItemTom = new TopItem(Constants.TOM, R.drawable.tom, 0);
        topItems.add(mTopItemBau);
        topItems.add(mTopItemCa);
        topItems.add(mTopItemCua);
        topItems.add(mTopItemGa);
        topItems.add(mTopItemHuou);
        topItems.add(mTopItemTom);
        return topItems;
    }

    public int increaseBet() {
        mAmount = mAmount + 1000;
        return mAmount;
    }

    public static void resetValue(ArrayList<TopItem> topItems) {
        if (topItems.size() == 7) {
            topItems.remove(topItems.size() - 1);
        }
        for (TopItem topItem : topItems) {
            topItem.setAmount(0);
        }

    }

}
