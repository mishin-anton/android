package com.vltavasoft.coasters.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vltavasoft.coasters.R;

public class BaseFragment extends Fragment{

    private RecyclerView mRecycler;
    private FloatingActionButton mBtnAdd;
    private final CoastersAdapter mCoasterAdapter = new CoastersAdapter();
    private CoastersAdapter.OnItemClickListener mListener;

    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();

        return fragment;
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
        mBtnAdd = view.findViewById(R.id.btn_add);

        getActivity().setTitle(R.string.app_name);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fr_start_container, AddNewCoasterFragment.newInstance())
                        //.addToBackStack(AddNewCoasterFragment.class.getName())
                        .commit();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecycler.setAdapter(mCoasterAdapter);
        mCoasterAdapter.setListener(mListener);
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}
