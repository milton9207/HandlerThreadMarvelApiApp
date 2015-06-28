package com.example.handlerthreadmarvelapiapp.app.Threads;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.example.handlerthreadmarvelapiapp.app.Util.DownloadContext;
import com.example.handlerthreadmarvelapiapp.app.Util.MarvelItem;
import com.example.handlerthreadmarvelapiapp.app.Util.Util;

import java.util.ArrayList;

/**
 * Created by milton on 27/06/15.
 */
public class DownloadImageThread implements Runnable {

    String rawData;
    Handler handler;
    DownloadContext downloadContext;
    ArrayList<MarvelItem> jsonData;
    String URL;

    public DownloadImageThread(String rawData)
    {
        this.rawData = rawData;
    }

    public DownloadImageThread(String rawData, Handler handler)
    {
        this.rawData = rawData;
        this.handler = handler;
    }
    public DownloadImageThread(DownloadContext downloadContext, Handler handler)
    {
        this.downloadContext = downloadContext;
        this.handler = handler;
    }


    @Override
    public void run() {

        rawData = downloadContext.downloadApi();
        jsonData = Util.jsonParser(rawData);

        for(MarvelItem item: jsonData)
        {
            item.setImage(downloadContext.downloadImage(item.getImageURL()));
            Log.i("DOW","downloading");
        }

        Message msg = handler.obtainMessage(DownloadCenterThread.PUBLISH, jsonData);
        handler.sendMessage(msg);


    }
}
