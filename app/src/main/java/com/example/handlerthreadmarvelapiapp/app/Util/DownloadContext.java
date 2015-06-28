package com.example.handlerthreadmarvelapiapp.app.Util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.example.handlerthreadmarvelapiapp.app.Adapters.RecyclerAdapter;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by milton on 27/06/15.
 *
 * @class DownloadContext
 *
 * @brief Class that defines methods used for download data
 *        and download Images from the API, used by the
 *        concurrency context.
 */
public class DownloadContext {


    /**
     *  Debug TAG for loggin debug outptu to LogCat
     */
    private final String TAG =
            DownloadContext.class.getSimpleName();

    /**
     * RecyclerAdpater use to update data to the mainUI
     * */
    private final WeakReference<RecyclerAdapter> adapter;

    /**
     * Reference from the Activity for doing UI operations
     * if needed
     * */
    private final WeakReference<Activity> mActivity;

    /**
     * Default image Url to download
     * */
    private final static String mDefaultURL = "http://zahiddehara.com/static/curso_verano/media/office.jpg";

    /**
     * URL to fetch API data
     * */
    private static final String URL =
            "http://gateway.marvel.com/v1/public/comics?format=comic&formatType=comic&noVariants=true&dateRange=2012-01-01%2C2012-01-10&orderBy=title&ts=1&apikey=a3849c2a58ac031f8bfdee77cf9f3d65&hash=b8c1aa9178c4be5d7b32d913bcea2b3e";


    public DownloadContext(RecyclerAdapter adapter, Activity mActivity) {

        this.adapter = new WeakReference<RecyclerAdapter>(adapter);
        this.mActivity = new WeakReference<Activity>(mActivity);
    }


    /**
     * @param url
     * @return Bitmap or null
     *
     *
     */
    public Bitmap downloadImage(String url) {
        //  Use the default URL if the user doesn't supply one.
        if (url.equals(""))
            url = mDefaultURL;

        try {
            // Connect to a remote server, download the contents of
            // the image, and provide access to it via an Input
            // Stream.
            InputStream is =
                    (InputStream) new URL(url).getContent();

            // Decode an InputStream into a Bitmap.
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.e("Fragment", "Error downloading image");
            e.printStackTrace();
            return null;
        }
    }

    public String downloadApi()
    {
        String data ="";
        HttpURLConnection httpURLConnection = null;
        try
        {
            httpURLConnection = (HttpURLConnection) new URL(URL).openConnection();
            httpURLConnection.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

            data = Util.readStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(null != httpURLConnection)
                httpURLConnection.disconnect();
        }

        return data;

    }

    /**
     * Display a downloaded bitmap image if it's non-null; otherwise,
     * it reports an error via a Toast that's displayed on the UI
     * Thread.  This method can be called from either the UI Thread or
     * a background Thread.
     *
     * @param image
     *            The bitmap image
     */

    //display data?
    public void displayImage(final Bitmap image)
    {
        // If this method is run in the UI Thread then display the
        // image.
        if (onUiThread()) {
            if (image == null)
                showToast("image is corrupted,"
                        + " please check the requested URL.");
            else {
                // Display the image on the user's screen.
//                mImageView.get().setImageBitmap(image);

                // Indicate we're done with this image.  This call
                // runs in the UI Thread, so we don't need to
                // synchronize it.
//                mCompletionCommand.run();

                //Add image
            }
        }
        // Otherwise, create a new Runnable command that's posted to
        // the UI Thread to display the image.
        else {
            mActivity.get().runOnUiThread(new Runnable() {
                    public void run() {
                        // Display the downloaded image to the user.
                        displayImage(image);
                    }});


        }
    }

    public void displayData(final ArrayList<MarvelItem> data)
    {
        // If this method is run in the UI Thread then display the
        // image.
        if (onUiThread()) {
            if (data == null)
                showToast("data is corrupted,"
                        + " please check the requested URL.");
            else {
                // Display the image on the user's screen.
//                mImageView.get().setImageBitmap(image);

                // Indicate we're done with this image.  This call
                // runs in the UI Thread, so we don't need to
                // synchronize it.
//                mCompletionCommand.run();

                //Add data

                Log.i(TAG,"dentro de display data");
                adapter.get().setData(data);
                adapter.get().notifyDataSetChanged();
            }
        }
        // Otherwise, create a new Runnable command that's posted to
        // the UI Thread to display the image.
        else {
            mActivity.get().runOnUiThread(new Runnable() {
                public void run() {
                    // Display the downloaded image to the user.
                    displayData(data);
                }});


        }
    }

        /**
         * Show a toast to the user.
         */
    public void showToast(String toastString) {
        Toast.makeText(mActivity.get(),
                toastString,
                Toast.LENGTH_SHORT).show();
    }

        /**
         * Returns true if the calling Thread is the UI Thread, else
         * false.
         */
    private Boolean onUiThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }





}
