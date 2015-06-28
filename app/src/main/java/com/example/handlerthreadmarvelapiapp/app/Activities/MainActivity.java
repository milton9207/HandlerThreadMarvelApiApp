package com.example.handlerthreadmarvelapiapp.app.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import com.example.handlerthreadmarvelapiapp.app.Adapters.RecyclerAdapter;
import com.example.handlerthreadmarvelapiapp.app.R;
import com.example.handlerthreadmarvelapiapp.app.Threads.DownloadCenterThread;
import com.example.handlerthreadmarvelapiapp.app.Util.DownloadContext;

public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    private ProgressBar progressBar;

    DownloadContext downloadContext;
    DownloadCenterThread downloadCenterThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerMainList);
        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        downloadContext = new DownloadContext(adapter,this);
        downloadCenterThread = new DownloadCenterThread("DCenter",downloadContext);
        downloadCenterThread.start();


    }

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
}
