package com.vltavasoft.coasters.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.vltavasoft.coasters.MainActivity;
import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.database.Coaster;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageThumbs;
    private String mId = "One";
    private Context context;
    private BitmapDecoder bitmapDecoder;

    public ViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        this.context = context;
        imageThumbs = itemView.findViewById(R.id.iv_thumbs);
        //imageThumbs.setImageResource(R.drawable.ic_logo);

        Log.d("CONT", "Value ViewHolder decode " + context);
        bitmapDecoder = new BitmapDecoder(this.context);
        imageThumbs.setImageBitmap(bitmapDecoder.readAndSetImage());
    }

    public void bind (int position) {
        // Тут установить визуальные элементы

    }

    public void setListener (final CoastersAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onItemClick("ONE");
                Log.d("TAG", "MSG");
            }
        });
    }
}
