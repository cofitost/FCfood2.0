package com.example.cofitost.fcfood20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ChooseActivity extends AppCompatActivity {

    Button go;
    int choose;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        go = (Button)findViewById(R.id.btn_go);
        go.setOnClickListener(Cgo);

        image = (ImageView)findViewById(R.id.iv_choose);
    }

    public OnClickListener Cgo = new OnClickListener() {
        @Override
        public void onClick(View v) {
            image.setImageResource(R.drawable.gif);
            final AnimationDrawable gyroAnimation = (AnimationDrawable) image.getDrawable();
            gyroAnimation.start();

            new Handler().postDelayed(new Runnable() {
                public void run() {

                    gyroAnimation.stop();

                    AlertDialog.Builder de = new AlertDialog.Builder(ChooseActivity.this);
                    de.setTitle("就決定吃這個");
                    //choose = (int)(Math.random()* 39+1);
                    de.setMessage("Result");
                    de.setNegativeButton("詳細資訊", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent in = new Intent();
                            in.setClass(ChooseActivity.this, ChooseResultActivity.class);
                            in.putExtra("KEY",choose);
                            startActivity(in);
                            finish();
                        }
                    });
                    de.setPositiveButton("再一次", new DialogInterface.OnClickListener() {
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
            }, 3000);
        }
    };

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

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode,KeyEvent event)  { //捕捉返回鍵
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
