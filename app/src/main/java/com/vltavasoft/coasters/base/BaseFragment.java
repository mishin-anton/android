package com.vltavasoft.coasters.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.database.Coaster;
import com.vltavasoft.coasters.database.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView mRecycler;
    private FloatingActionButton mBtnAdd;
    private final CoastersAdapter mCoasterAdapter = new CoastersAdapter();
    private CoastersAdapter.OnItemClickListener mListener;

    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
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

        mRecycler.addItemDecoration(new RecyclerDecorator(10, 3));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<Coaster> list = mCoasterAdapter.getDatafromAdapter();
        list = filter(list, query);


        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return false;
    }

    private static List<Coaster> filter(List<Coaster> coasters, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Coaster> filteredModelList = new ArrayList<>();

        for (Coaster coaster : coasters) {
            final String name = coaster.getName().toLowerCase();

            if (name.contains(lowerCaseQuery)) {
                filteredModelList.add(coaster);
            }
        }
        return filteredModelList;
    }


    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

}
