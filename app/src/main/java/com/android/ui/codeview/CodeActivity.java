package com.android.ui.codeview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.ui.R;


public class CodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        readData();
    }

    private void readData(){
        String code=getIntent().getStringExtra("code");
        ((TextView)findViewById(R.id.tv_code)).setText(code);
    }
}
