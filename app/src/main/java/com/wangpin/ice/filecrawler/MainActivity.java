package com.wangpin.ice.filecrawler;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File[] folders = {Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)};
        new FileCrawler((TextView)findViewById(R.id.info)).execute(folders);
    }
}
