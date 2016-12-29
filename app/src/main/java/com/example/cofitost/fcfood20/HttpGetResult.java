package com.example.cofitost.fcfood20;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class HttpGetResult {

    void get(){
        try{
            URL url = new URL("http://10.21.0.130:8080/android-backend/webapi/food/random");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            HashMap<String, String> getDataParams = new HashMap<>();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        }catch (Exception e){

        }
    }
}
