package com.example.cofitost.fcfood20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseAddActivity extends AppCompatActivity {

    EditText storeName,meal,address,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button addFinish = (Button)findViewById(R.id.btn_addFinish);
        addFinish.setOnClickListener(CaddFinish);

        meal = (EditText)findViewById(R.id.edt_meal);
        storeName = (EditText)findViewById(R.id.edt_storeName);
        price = (EditText)findViewById(R.id.edt_price);
        address = (EditText)findViewById(R.id.edt_address);
    }

    public OnClickListener CaddFinish = new OnClickListener() {
        @Override
        public void onClick(View v) {

                onPost();
                //Toast.makeText(ChooseAddActivity.this, "沒網路QQ" ,Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.setClass(ChooseAddActivity.this,ChooseActivity.class);
            startActivity(intent);
            finish();
        }
    };

    /*private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();//可變動的字元字串
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){//params1=a&params2=b&params3=c
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));//字串插入
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }*/

    /*void httpPost() {

        String str_meal = meal.getText().toString();
        String str_storeName = storeName.getText().toString();
        String str_price = price.getText().toString();
        String str_address = address.getText().toString();

        try {
            URL url = new URL("http://10.21.0.130:8080/android-backend/webapi/food/add");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            HashMap<String, String> postDataParams = new HashMap<>();

            postDataParams.put("foodName", str_meal);
            postDataParams.put("storeName", str_storeName);
            postDataParams.put("price", str_price);
            postDataParams.put("address", str_address);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            Log.i("abc",postDataParams.toString());
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void onPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpPost();
            }
        }).start();
    }

    public void httpPost(){
        String result = null;

        HttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost("http://192.168.1.106:8080/android-backend/webapi/food/add");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("key",value));
            //params.add(new BasicNameValuePair("hour",postHour));
            params.add(new BasicNameValuePair("foodName",meal.getText().toString()));
            params.add(new BasicNameValuePair("storeName",storeName.getText().toString()));
            params.add(new BasicNameValuePair("price",price.getText().toString()));
            params.add(new BasicNameValuePair("address",address.getText().toString()));

            UrlEncodedFormEntity ent = null;
            Log.d("abc",params.toString());

            ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

            if (resEntity != null) {
                result = EntityUtils.toString(resEntity);
                Log.d("abc",result);
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

    public boolean onKeyDown(int keyCode,KeyEvent event)  { //捕捉返回鍵
        if  (keyCode== KeyEvent.KEYCODE_BACK )  {
            Intent intent = new Intent();
            intent.setClass(ChooseAddActivity.this,ChooseActivity.class);
            startActivity(intent);
            finish();
            return true ;
        }
        return super.onKeyDown( keyCode, event );
    }

}
