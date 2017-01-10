package com.example.cofitost.fcfood20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends AppCompatActivity {

    Button go;
    ImageView image;
    String result,foodName,storeName,price,address;
    AlertDialog.Builder de;
    Handler handler = new Handler();
    AnimationDrawable gyroAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        image = (ImageView)findViewById(R.id.iv_choose);
        setSupportActionBar(toolbar);
        go = (Button)findViewById(R.id.btn_go);
        go.setOnClickListener(Cgo);
        de = new AlertDialog.Builder(ChooseActivity.this);
        handler.post(text);
    }

    public void onGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpGet();
            }
        }).start();
    }

    //GET隨機資料
    public void httpGet(){
      String url = "http://140.134.26.9/Project1/api/PredictApi/GetMax/3";
        HttpClient client = new DefaultHttpClient();
        try {
           HttpGet get = new HttpGet("http://10.21.17.162:8080/android-backend/webapi/food/random");
           //HttpGet get = new HttpGet(url);
            HttpResponse responsePOST = client.execute(get);
            HttpEntity resEntity = responsePOST.getEntity();

            if (resEntity != null) {
                result = EntityUtils.toString(resEntity,HTTP.UTF_8);
                Log.i("abc",result);
                try {
                    JSONObject food = new JSONObject(result);
                    foodName = food.getString("foodName");
                    storeName = food.getString("storeName");
                    price = food.getString("price");
                    address = food.getString("address");
                    Log.d("abc",foodName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }
    }

    public OnClickListener Cgo = new OnClickListener() {
        @Override
        public void onClick(View v) {
            image.setImageResource(R.drawable.gif);
            gyroAnimation = (AnimationDrawable) image.getDrawable();
            gyroAnimation.start();
            //onGet();
            foodName = "義大利麵";
            storeName = "隨便";
            price = "100";
            address = "那裡";
        }
    };

    private Runnable text = new Runnable() {//結果寫在這
        @Override
        public void run() {
            if(foodName!=null) {
                setDialog();
                handler.removeCallbacks(text);
                gyroAnimation.stop();
            }
            else{
                handler.postDelayed(text,3000);
            }
        }
    };

    public void setDialog(){
        de.setTitle("吃這個");
        de.setMessage(foodName);
        de.setNegativeButton("詳細資訊", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent();
                in.setClass(ChooseActivity.this, ChooseResultActivity.class);
                in.putExtra("foodName_KEY",foodName);
                in.putExtra("storeName_KEY",storeName);
                in.putExtra("price_KEY",price);
                in.putExtra("address_KEY",address);
                startActivity(in);
                finish();
            }
        });
        de.setPositiveButton("在一次", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent();
                intent.setClass(ChooseActivity.this, ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });
        de.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_choose) {
            Intent intent = new Intent();
            intent.setClass(ChooseActivity.this,ChooseAddActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        else if(id == R.id.action_settings_showChooseData){
            Intent intent = new Intent();
            intent.setClass(ChooseActivity.this,ShowChooseDataActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode,KeyEvent event)  { //???????^??
        if  (keyCode== KeyEvent.KEYCODE_BACK )  {
            Intent intent = new Intent();
            intent.setClass(ChooseActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            return true ;
        }
        return super.onKeyDown( keyCode, event );
    }

}
