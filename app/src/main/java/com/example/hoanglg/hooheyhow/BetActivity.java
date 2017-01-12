package com.example.hoanglg.hooheyhow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanglg.hooheyhow.model.TopItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BetActivity extends AppCompatActivity {
    private Button mShakeButton;
    public boolean donePlay = false;
    private TextView mResultTextView;
    ImageView mImageViewLeft;
    ImageView mImageViewRight;
    ImageView mImageViewBottom;
    public String mTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        initReference();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String topItemAsString = extras.getString(Constants.list_item);
        String mAmount = extras.getString(Constants.total_money);
        mTotalAmount = String.valueOf(mAmount);
        Log.e("amount", mAmount);
        Gson gson = new Gson();
        Type type = new TypeToken<List<TopItem>>() {
        }.getType();
        final List<TopItem> topItemList = gson.fromJson(topItemAsString, type);
        for (TopItem topItem : topItemList) {
            Log.e("Top item data", mAmount + "-" + topItem.getTag() + "-" + topItem.getAmount() + "-" + topItem.getDrawableResource());
        }
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                if (i == 10) {
                    handler.removeCallbacks(this);//remove call backs
                    mResultTextView.setVisibility(View.VISIBLE);
                    mResultTextView.setText(lostOrGain(Integer.parseInt(mTotalAmount), (ArrayList<TopItem>) topItemList));
                    Log.e("new1", mImageViewLeft.getTag().toString() + "");
                    Log.e("new2", mImageViewRight.getTag().toString() + "");
                    Log.e("new3", mImageViewBottom.getTag().toString() + "");
                    Log.e("old", topItemList.get(0).getTag() + "");

                    mShakeButton.setText(getString(R.string.keep_playing));
                    mShakeButton.setVisibility(View.VISIBLE);
                    donePlay = true;
                } else {
                    changeImage();
                    i++;
                    handler.postDelayed(this, 1);//post it again
                }
            }
        };

        mShakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!donePlay) {
                    mShakeButton.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(runnable);
                    handler.post(runnable);
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(Constants.total_money, mTotalAmount);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }

            }
        });
    }

    public String lostOrGain(int totalAmount, ArrayList<TopItem> topItems) {
        int balance = 0;
        Log.e("Size", topItems.size() + "");
        for (TopItem topItem : topItems) {
            boolean common = false;
            if (topItem.getAmount() > 0) {
                if (topItem.getTag().equals(mImageViewLeft.getTag())) {
                    balance += (topItem.getAmount() * 2);
                    common = true;
                }
                if (topItem.getTag().equals(mImageViewRight.getTag())) {
                    balance += (topItem.getAmount() * 2);
                    common = true;
                }
                if (topItem.getTag().equals(mImageViewBottom.getTag())) {
                    balance += (topItem.getAmount() * 2);
                    common = true;
                }
                if (!common) {
                    balance -= topItem.getAmount();
                }
            }
        }
        mTotalAmount = String.valueOf(Integer.parseInt(mTotalAmount) + balance);
        if (balance == 0) {
            return getString(R.string.break_even);
        }
        return balance > 0 ? getString(R.string.gain_money) + balance : getString(R.string.lose_money) + balance;
    }


    public void initReference() {
        mImageViewLeft = (ImageView) findViewById(R.id.mImageView1);
        mImageViewRight = (ImageView) findViewById(R.id.mImageView2);
        mImageViewBottom = (ImageView) findViewById(R.id.mImageView3);
        mShakeButton = (Button) findViewById(R.id.shakeButton);
        mResultTextView = (TextView) findViewById(R.id.mResultTextView);
    }

    public void changeImage() {
        Bitmap bauBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bau);
        bauBitmap = Bitmap.createScaledBitmap(bauBitmap, 100, 100, false);
        Bitmap caBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ca);
        caBitmap = Bitmap.createScaledBitmap(caBitmap, 100, 100, false);
        Bitmap cuaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cua);
        cuaBitmap = Bitmap.createScaledBitmap(cuaBitmap, 100, 100, false);
        Bitmap gaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ga);
        gaBitmap = Bitmap.createScaledBitmap(gaBitmap, 100, 100, false);
        Bitmap huouBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.huou);
        huouBitmap = Bitmap.createScaledBitmap(huouBitmap, 100, 100, false);
        Bitmap tomBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tom);
        tomBitmap = Bitmap.createScaledBitmap(tomBitmap, 100, 100, false);
        List<Bitmap> listBitmap = new ArrayList<Bitmap>();
        listBitmap.add(bauBitmap);
        listBitmap.add(caBitmap);
        listBitmap.add(cuaBitmap);
        listBitmap.add(gaBitmap);
        listBitmap.add(huouBitmap);
        listBitmap.add(tomBitmap);
        Random r = new Random();
        int nextInt1 = r.nextInt(listBitmap.size());
        int nextInt2 = r.nextInt(listBitmap.size());
        int nextInt3 = r.nextInt(listBitmap.size());
        mImageViewLeft.setImageBitmap(listBitmap.get(nextInt1));
        if (nextInt1 == 0) {
            mImageViewLeft.setTag(Constants.BAU);
        } else if (nextInt1 == 1) {
            mImageViewLeft.setTag(Constants.CA);
        } else if (nextInt1 == 2) {
            mImageViewLeft.setTag(Constants.CUA);
        } else if (nextInt1 == 3) {
            mImageViewLeft.setTag(Constants.GA);
        } else if (nextInt1 == 4) {
            mImageViewLeft.setTag(Constants.HUOU);
        } else if (nextInt1 == 5) {
            mImageViewLeft.setTag(Constants.TOM);
        }
        mImageViewRight.setImageBitmap(listBitmap.get(nextInt2));
        if (nextInt2 == 0) {
            mImageViewRight.setTag(Constants.BAU);
        } else if (nextInt2 == 1) {
            mImageViewRight.setTag(Constants.CA);
        } else if (nextInt2 == 2) {
            mImageViewRight.setTag(Constants.CUA);
        } else if (nextInt2 == 3) {
            mImageViewRight.setTag(Constants.GA);
        } else if (nextInt2 == 4) {
            mImageViewRight.setTag(Constants.HUOU);
        } else if (nextInt2 == 5) {
            mImageViewRight.setTag(Constants.TOM);
        }
        mImageViewBottom.setImageBitmap(listBitmap.get(nextInt3));
        if (nextInt3 == 0) {
            mImageViewBottom.setTag(Constants.BAU);
        } else if (nextInt3 == 1) {
            mImageViewBottom.setTag(Constants.CA);
        } else if (nextInt3 == 2) {
            mImageViewBottom.setTag(Constants.CUA);
        } else if (nextInt3 == 3) {
            mImageViewBottom.setTag(Constants.GA);
        } else if (nextInt3 == 4) {
            mImageViewBottom.setTag(Constants.HUOU);
        } else if (nextInt3 == 5) {
            mImageViewBottom.setTag(Constants.TOM);
        }
    }

}
