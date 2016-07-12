package com.lingxian.lingxian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.lingxian.lingxian.R;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class RegistActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.registview);
        initViews();
    }

    private void initViews() {
    }
}
