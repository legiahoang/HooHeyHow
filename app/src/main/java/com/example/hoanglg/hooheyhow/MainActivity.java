package com.example.hoanglg.hooheyhow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoanglg.hooheyhow.model.TopItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int mMoney = 30000;
    private TextView mBugetAmountTextView;
    private Button mResetButton;
    private Button mBetButton;
    List<TopItem> mTopItems;
    RecyclerView recyclerView;
    TopItemAdapter topItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //set toolbar to act as the ActionBar for this Activity windows
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initItem();
        initReference();
    }

    public void initReference() {
        mBugetAmountTextView = (TextView) findViewById(R.id.textview_amount);
        mBugetAmountTextView.setText(mMoney+"");
        mResetButton = (Button) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBugetAmountTextView.setText(30000+"");
                TopItem.resetValue((ArrayList<TopItem>) mTopItems);
                topItemAdapter.notifyDataSetChanged();
            }
        });
        mBetButton = (Button) findViewById(R.id.bet_button);
        mBetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open bet activity
                Intent intent = new Intent(getApplicationContext(), BetActivity.class);
                Gson gson = new Gson();
                String jsonTopItem = gson.toJson(mTopItems);
                Bundle extras = new Bundle();
                extras.putString(Constants.list_item,jsonTopItem);
                extras.putString(Constants.total_money, String.valueOf(mMoney));
                intent.putExtras(extras);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent,1);
                }
            }
        });
    }

    public void initItem() {
        recyclerView = (RecyclerView) findViewById(R.id.rvItem);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mTopItems = new ArrayList<>();
        mTopItems.addAll(TopItem.createTopItemList());
        topItemAdapter = new TopItemAdapter(mTopItems, getApplicationContext());
        recyclerView.setAdapter(topItemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra(Constants.total_money);
                mMoney = Integer.parseInt(result);
                mBugetAmountTextView.setText(mMoney+"");
            }
        }
    }
}
