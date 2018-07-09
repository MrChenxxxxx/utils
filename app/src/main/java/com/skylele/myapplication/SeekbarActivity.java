package com.skylele.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Created by cxg on 2018/4/13.
 *
 * @version 1.0.0
 */

public class SeekbarActivity extends Activity {

    private SeekBar mSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                log("onProgressChanged : " +i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                log("onStartTrackingTouch : ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                log("onStopTrackingTouch : ");
            }
        });
    }

    private void log(String msg) {
        Log.i("Seekbar", msg);
    }
}
