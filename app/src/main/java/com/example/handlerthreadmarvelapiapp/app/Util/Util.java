package com.example.handlerthreadmarvelapiapp.app.Util;

import android.graphics.Bitmap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by milton on 27/06/15.
 *
 * @class Util
 * Helper class with different utility mechanisms
 */
public class Util {

    //Debe ir en utileria
    public static String readStream(InputStream in)
    {
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer("");

        try {

            reader = new BufferedReader(new InputStreamReader(in));
            String line ="";
            while ((line = reader.readLine())!=null)
            {
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return data.toString();
    }

    public static ArrayList<MarvelItem> jsonParser(String json){
        ArrayList<MarvelItem> result = new ArrayList<MarvelItem>();

        try
        {
            JSONObject jsonObject = (JSONObject) new JSONTokener(json).nextValue();
            JSONObject apiData = jsonObject.getJSONObject("data");
            JSONArray apiResult = apiData.getJSONArray("results");

            for(int i=0; i<apiResult.length(); i++)
            {
                JSONObject comicResult = (JSONObject) apiResult.get(i);

                JSONArray images = comicResult.getJSONArray("images");
                JSONObject imageResult = (JSONObject) images.get(0);
                String imageUrl = imageResult.getString("path") + ".jpg";
//                final Bitmap image = Util.downloadImage(imageUrl);

                result.add(new MarvelItem(comicResult.getString("title"),comicResult.getString("description"),imageUrl));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
