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
 *
 * @class DownloadCenterThread
 *
 * @brief The intention of this thread is for managing multiple downloads
 *        threads and communicate with the main thread
 */
public class DownloadCenterThread extends HandlerThread implements Handler.Callback{

    public static final int PUBLISH = 0;
    public static final int PUBLISH2 = 1;
    public static final int QUITlOOP = 2;

    Handler handler;
    //DownloadContext can vary depending on the source of download
    DownloadContext downloadContext;
    Thread imageThread;

    public DownloadCenterThread(String name, DownloadContext downloadContext) {
        super(name);

        this.downloadContext = downloadContext;
        //downloadImageThread = new DownloadFromAPI()
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();

        handler = new Handler(this);

        imageThread = new Thread(new DownloadFromAPI(downloadContext,handler));
        imageThread.start();

    }

    /**
     * @param command Runnable for executin in this handlerThread
     *
     */
    private void postOnDownloadCenterThread(Runnable command)
    {
        handler.post(command);
    }

    /**
     * @param msg
     * @return true
     *
     * Here the communication with the mainUI Thread is made
     *
     */
    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what)
        {
            case PUBLISH:
                //Slow way
                downloadContext.displayData((ArrayList<MarvelItem>) msg.obj);
                break;

            case PUBLISH2:
                //In-time download
                downloadContext.displayItem((MarvelItem) msg.obj);
                break;

            case QUITlOOP:
                Looper.myLooper().quit();
                break;
        }

        return true;
    }
}
