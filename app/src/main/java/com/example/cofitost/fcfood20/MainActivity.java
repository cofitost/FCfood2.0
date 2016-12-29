package com.example.cofitost.fcfood20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button choose = (Button)findViewById(R.id.btn_choose);
        choose.setOnClickListener(Cchoose);

        Button charge = (Button)findViewById(R.id.btn_charge);
        charge.setOnClickListener(Ccharge);
    }

    public OnClickListener Cchoose = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ChooseActivity.class);
            startActivity(intent);
            finish();
        }
    };

    public OnClickListener Ccharge = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ChargeActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode,KeyEvent event)  { //捕捉返回鍵
        if  (keyCode== KeyEvent.KEYCODE_BACK )  {
            exit();
            return true ;
        }
        return super.onKeyDown( keyCode, event );
    }

    private void exit(){
        AlertDialog.Builder de = new AlertDialog.Builder(this);
        de.setMessage("確定退出?");
        de.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface di, int i) {

                    }
                };

        de.setPositiveButton(" 取消 ", listener);
        de.show();
    }
}
