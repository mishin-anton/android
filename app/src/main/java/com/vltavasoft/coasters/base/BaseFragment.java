package com.vltavasoft.coasters.base;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;

import com.vltavasoft.coasters.R;

public class BaseFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
    CoastersAdapter.OnItemClickListener{

    public static int subscreensOnTheStack = 0;
    private RecyclerView mRecycler;
    private FloatingActionButton mBtnAdd;
    private CoastersAdapter mCoasterAdapter;

    private CoastersAdapter.OnItemClickListener mListener;
    private String[] dataForTest = {"1"};


    public static BaseFragment newInstance() {
        return new BaseFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CoastersAdapter.OnItemClickListener) {
            mListener = (CoastersAdapter.OnItemClickListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler);
        mBtnAdd = (FloatingActionButton)view.findViewById(R.id.btn_add);

        mCoasterAdapter = new CoastersAdapter(getContext(), dataForTest);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fr_start_container, AddNewFragment.newInstance())
                        .addToBackStack(AddNewFragment.class.getName())
                        .commit();
                subscreensOnTheStack++;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecycler.setAdapter(mCoasterAdapter);
        mCoasterAdapter.setListener(mListener);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    public void onItemClick(String id) {
        Log.d("TAG", "TAG");
    }
}
