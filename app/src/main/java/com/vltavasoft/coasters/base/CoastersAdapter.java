package com.vltavasoft.coasters.base;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vltavasoft.coasters.R;

public class CoastersAdapter extends RecyclerView.Adapter<ViewHolder> {

    private String[] mData;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;
    private Context context;

    // data is passed into constructor
    CoastersAdapter (Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.li_object, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
        holder.setListener(mListener);

    }

    @Override
    public int getItemCount() {
        //return mCursor != null ? mCursor.getCount() : 0;
        return 1;
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }


}
