package com.example.handlerthreadmarvelapiapp.app.Threads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.example.handlerthreadmarvelapiapp.app.Util.DownloadContext;
import com.example.handlerthreadmarvelapiapp.app.Util.MarvelItem;

import java.util.ArrayList;

/**
 * Created by milton on 27/06/15.
 */
public class DownloadCenterThread extends HandlerThread implements Handler.Callback{

    public static final int PUBLISH = 0;

    Handler handler;
    DownloadContext downloadContext;
    DownloadImageThread downloadImageThread;
    Thread imageThread;

    public DownloadCenterThread(String name, DownloadContext downloadContext) {
        super(name);

        this.downloadContext = downloadContext;
        //downloadImageThread = new DownloadImageThread()
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();

        handler = new Handler(this);

//        String rawData = downloadContext.downloadApi();

        imageThread = new Thread(new DownloadImageThread(downloadContext,handler));
        imageThread.start();

    }

    private void postOnDownloadCenterThread(Runnable command)
    {
        handler.post(command);
    }

    @Override
    public boolean handleMessage(Message msg) {


        switch (msg.what)
        {
            case PUBLISH:

                downloadContext.displayData((ArrayList<MarvelItem>) msg.obj);

                break;

        }

        Looper.myLooper().quit();

        return true;
    }
}
