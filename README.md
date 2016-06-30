# asyc-task-demo
异步线程示例

![fd](https://raw.githubusercontent.com/android-starter/async-task-demo/master/filecrawler.gif)

## 主要代码
*　FileCrawler.java 异步线程
FileCrawler 继承了 AsyncTask，并实现 doInBackground 和 onPostExecute（可选）方法。

```java
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

```

* MainActivity.java
activity中开始异步线程
```java

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File[] folders = {Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)};
        new FileCrawler((TextView)findViewById(R.id.info)).execute(folders);
    }
}

```

## 第三方jar包
为了简化文件操作，这里使用了 [common io](http://commons.apache.org/proper/commons-io/)
