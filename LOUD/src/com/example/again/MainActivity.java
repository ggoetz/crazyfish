package com.example.again;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*LayoutInflater mInflater = LayoutInflater.from(this);  
        View contentView = mInflater.inflate(R.layout.activity_main, null); 
        RelativeLayout root = (RelativeLayout) contentView.findViewById(R.id.root);// mContainerIconExtension in your case
        // root.addView(new TextView(this));
        setContentView(root);
        
        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);*/
        
        (new Thread(new RTPlayback())).start();
        setContentView(R.layout.activity_main);
        
    }
}