package com.vltavasoft.coasters.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.database.Coaster;
import com.vltavasoft.coasters.database.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class CoastersAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Coaster> mData;
    private OnItemClickListener mListener;
    private DataHelper mDataHelper = new DataHelper();

    public CoastersAdapter() {
        mData = new ArrayList<>();
        mData = mDataHelper.getAllCoasters();
    }

    public List<Coaster> getDatafromAdapter() {
        return mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_object, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mData.get(position));
        holder.setListener(mListener);
    }

    @Override
    public int getItemCount() {
        //return mCursor != null ? mCursor.getCount() : 0;

        return mData.size();
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }
}
