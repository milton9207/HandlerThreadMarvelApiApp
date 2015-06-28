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
 * @class DownloadFromAPI
 *
 * @brief Concrete strategy class used for download data
 *        and download images from the Marvel API
 */
public class DownloadFromAPI implements Runnable {

    String rawData;
    Handler handler;
    DownloadContext downloadContext;
    ArrayList<MarvelItem> data;


    public DownloadFromAPI(DownloadContext downloadContext, Handler handler) {
        this.downloadContext = downloadContext;
        this.handler = handler;
    }


    @Override
    public void run() {

        rawData = downloadContext.downloadApi();

        //Marvel items list in parsed from json
        data = Util.jsonParser(rawData);

        for (MarvelItem item : data) {
            item.setImage(downloadContext.downloadImage(item.getImageURL()));
            Message msg = handler.obtainMessage(DownloadCenterThread.PUBLISH2, item);
            handler.sendMessage(msg);
            Log.i("DOW", "downloading");
        }

        /**
         * Slow way, still need a progress bar
         * */
//        Message msg = handler.obtainMessage(DownloadCenterThread.PUBLISH, data);
//        handler.sendMessage(msg);

        //DownloadCenterThread Quitloop needs to be called in either cases
        Message msg2 = handler.obtainMessage(DownloadCenterThread.QUITlOOP);
        handler.sendMessage(msg2);


    }
}
