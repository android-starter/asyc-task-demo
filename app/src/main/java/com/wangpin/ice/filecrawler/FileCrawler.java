package com.wangpin.ice.filecrawler;

import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.Collection;

/**
 * Created by wangpi on 6/30/2016.
 */
public class FileCrawler extends AsyncTask<File, Integer, Integer>{
    private TextView view;
    public FileCrawler(TextView view ){
        this.view = view;
    }

    @Override
    protected Integer doInBackground(File... folders) {
        Collection<File> files = null;
        for(File folder : folders){
            if(folder.exists() && folder.isDirectory()){
                if(files == null) {
                    files = FileUtils.listFiles(folder, null, true);
                }else{
                    files.addAll(FileUtils.listFiles(folder, null, true));
                }
            }
        }
        if(files != null){
            return files.size();
        }else{
            return 0;
        }
    }

    @Override
    protected void onPostExecute(Integer sum){
        view.setText("File amout : " + sum);
    }
}
