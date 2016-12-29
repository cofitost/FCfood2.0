/*package com.example.cofitost.fcfood20;

import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpPostAdd {
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
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
    }

    void httpPost(){
        ChooseAddActivity add = new ChooseAddActivity();//

        String str_meal = add.meal.getText().toString();
        String str_storeName = add.storeName.getText().toString();
        String str_price = add.price.getText().toString();
        String str_address = add.address.getText().toString();

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

            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpPost();//
            }
        }).start();
    }
}*/
