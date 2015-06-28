package com.example.handlerthreadmarvelapiapp.app.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.handlerthreadmarvelapiapp.app.R;
import com.example.handlerthreadmarvelapiapp.app.Util.MarvelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by milton on 27/06/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private List<MarvelItem> data;

    public RecyclerAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<MarvelItem>();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.marvel_row,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

        MarvelItem item = data.get(i);

        myViewHolder.titleTextView.setText(item.getTitle());
        myViewHolder.descrptionTextView.setText(item.getDescription());
        myViewHolder.image.setImageBitmap(item.getImage());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItem(MarvelItem item)
    {
        data.add(item);
    }

    public void setData(ArrayList<MarvelItem> data){ this.data = data; }

     class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleTextView;
        TextView descrptionTextView;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.rowTextView);
            descrptionTextView = (TextView) itemView.findViewById(R.id.rowDescription);
            image = (ImageView) itemView.findViewById(R.id.rowImage);

        }
    }
}
