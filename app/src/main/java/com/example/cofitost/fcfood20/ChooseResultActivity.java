package com.example.cofitost.fcfood20;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class ChooseResultActivity extends AppCompatActivity {

    TextView foodName,storeName,price,address;
    String str_foodName,str_storeName,str_price,str_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodName = (TextView)findViewById(R.id.tv_result_foodName);
        storeName = (TextView)findViewById(R.id.tv_result_storeName);
        price = (TextView)findViewById(R.id.tv_result_price);
        address = (TextView)findViewById(R.id.tv_result_address);

        Intent intent = getIntent();
        str_foodName = intent.getStringExtra("foodName_KEY");
        foodName.setText(str_foodName);
        str_storeName = intent.getStringExtra("storeName_KEY");
        storeName.setText(str_storeName);
        str_price = intent.getStringExtra("price_KEY");
        price.setText(str_price);
        str_address = intent.getStringExtra("address_KEY");
        address.setText(str_address);
    }

    public boolean onKeyDown(int keyCode,KeyEvent event)  { //捕捉返回鍵
        if  (keyCode== KeyEvent.KEYCODE_BACK )  {
            Intent intent = new Intent();
            intent.setClass(ChooseResultActivity.this,ChooseActivity.class);
            startActivity(intent);
            finish();
            return true ;
        }
        return super.onKeyDown( keyCode, event );
    }

}
