package com.example.cofitost.fcfood20;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ChargeActivity extends AppCompatActivity {

    EditText foodName,price,date;
    Button finish;
    AlertDialog.Builder total;
    String result;
    String money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodName = (EditText)findViewById(R.id.edt_charge_foodName);
        price = (EditText)findViewById(R.id.edt_charge_price);
        date = (EditText)findViewById(R.id.edt_charge_date);

        finish = (Button)findViewById(R.id.btn_charge_finish);
        finish.setOnClickListener(Cfinish);

        total = new AlertDialog.Builder(ChargeActivity.this);
    }

    public OnClickListener Cfinish = new OnClickListener() {
        @Override
        public void onClick(View v) {
            onPost();
            /*foodName.setText("");
            price.setText("");
            date.setText("");*/
            Toast.makeText(ChargeActivity.this,"資料登入成功",Toast.LENGTH_LONG).show();
        }
    };

    public void onGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpGet();
            }
        }).start();
    }

    //GET總額
    public void httpGet(){
        String url = "http://140.134.26.9/Project1/api/PredictApi/GetMax/3";
        HttpClient client = new DefaultHttpClient();
        try {
            HttpGet get = new HttpGet("http://10.21.17.162:8080/android-backend/webapi/food/random");
            //HttpGet get = new HttpGet(url);
            HttpResponse responsePOST = client.execute(get);
            HttpEntity resEntity = responsePOST.getEntity();

            if (resEntity != null) {
                money = EntityUtils.toString(resEntity,HTTP.UTF_8);
                Log.i("abc",money);
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

    public void onPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpPost();
            }
        }).start();
    }

    //POST單筆消費
    public void httpPost(){

        HttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost("http://10.21.17.162:8080/android-backend/webapi/charge/add");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("key",value));
            //params.add(new BasicNameValuePair("hour",postHour));
            params.add(new BasicNameValuePair("foodName",foodName.getText().toString()));
            params.add(new BasicNameValuePair("price",price.getText().toString()));
            params.add(new BasicNameValuePair("date",date.getText().toString()));

            UrlEncodedFormEntity ent = null;
            Log.d("abc",params.toString());

            ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

            if (resEntity != null) {
                result = EntityUtils.toString(resEntity);
                Log.d("abcd",result);
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

    public void setDialog(){
        total.setTitle("總額");
        total.setMessage(money);
        total.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        total.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_charge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_charge) {
            money = "87";
            setDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode,KeyEvent event)  { //捕捉返回鍵
        if  (keyCode== KeyEvent.KEYCODE_BACK )  {
            Intent intent = new Intent();
            intent.setClass(ChargeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            return true ;
        }
        return super.onKeyDown( keyCode, event );
    }

}
