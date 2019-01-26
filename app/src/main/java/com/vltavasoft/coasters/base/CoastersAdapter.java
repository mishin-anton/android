package com.vltavasoft.coasters.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.database.Coaster;

import java.util.ArrayList;
import java.util.List;

public class CoastersAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Coaster> mData;
    private List<Coaster> mCopyList;
    private OnItemClickListener mListener;

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

    public void addData(List<Coaster> coasters) {
        mData = coasters;
        mCopyList = new ArrayList<Coaster>();
        mCopyList.addAll(mData);
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    public void filter(String queryText) {
        mData.clear();

        if (queryText.isEmpty()) {
            mData.addAll(mCopyList);
        } else {

            for (Coaster name : mCopyList) {
                if (name.getName().toString().toLowerCase().contains(queryText.toLowerCase()) ||
                        name.getCity().toString().toLowerCase().contains(queryText.toLowerCase())) {
                    mData.add(name);
                }
            }

        }

        notifyDataSetChanged();
    }
}
