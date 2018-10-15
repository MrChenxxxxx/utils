package com.skylele.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.skylele.myapplication.activity.WidgetUseActivity;
import com.skylele.myapplication.utils.CommonUtil;
import com.skylele.myapplication.widget.password.IPayPwdListerner;
import com.skylele.myapplication.widget.password.PayPwdEditText;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        CommonUtil.changeTextStyle(this, tv1, 2, 3, R.style.Text_red_Style_15);
        CommonUtil.changeTextStyle(this, tv2, 31, 3, R.style.Text_red_Style_15);
        CommonUtil.changeTextStyle(this, tv2, 11, 31, R.style.Text_red_Style_15);
        initListener();
        
        
    }

    private void initListener() {
        findViewById(R.id.btn_seekbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SeekbarActivity.class));
            }
        });
        findViewById(R.id.btn_widget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WidgetUseActivity.class));
            }
        });

        ((PayPwdEditText) findViewById(R.id.ppet)).setListerner(new IPayPwdListerner() {
            @Override
            public void onSelectListern(View view, String s, int position) {
                Log.i("cxg",s);
                ((TextView)view.findViewById(R.id.ppet_tv_content)).setText(s);
            }
        });
    }
}
