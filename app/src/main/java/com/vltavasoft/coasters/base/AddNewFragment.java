package com.vltavasoft.coasters.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.vltavasoft.coasters.R;

public class AddNewFragment extends Fragment{

    private EditText mNameCoaster;
    private EditText mShape;
    private EditText mCountry;
    private EditText mCity;
    private ImageView mFrontSide;
    private ImageView mBackSide;
    private Button mSave;

    public static AddNewFragment newInstance() {
        return new AddNewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_add_new, container, false);
        getActivity().setTitle("Add new");

        mNameCoaster = v.findViewById(R.id.et_name);
        mShape = v.findViewById(R.id.et_shape);
        mCountry = v.findViewById(R.id.et_country);
        mCity = v.findViewById(R.id.et_city);
        mFrontSide = v.findViewById(R.id.iv_front_side);
        mBackSide = v.findViewById(R.id.iv_back_side);
        mSave = v.findViewById(R.id.btn_save);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCoaster();
            }
        });

        return v;
    }

    private void saveCoaster() {
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle(R.string.app_name);
    }
}
