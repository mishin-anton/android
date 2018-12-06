package com.vltavasoft.coasters.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.database.Coaster;
import com.vltavasoft.coasters.database.DataHelper;

public class DescriptionFragment extends Fragment {

    public static DescriptionFragment newInstance(){
        return new DescriptionFragment();
    }

    private Coaster coaster;
    private TextView mNameCoaster;
    private TextView mShape;
    private TextView mCountry;
    private TextView mCity;
    private ImageView mFrontSide;
    private ImageView mBackSide;
    private Button mQuit;
    private Button mDelete;
    private DataHelper mDataHelper = new DataHelper();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_description, container, false);
        getActivity().setTitle("About");

        mNameCoaster = v.findViewById(R.id.tv_name);
        mShape = v.findViewById(R.id.tv_shape);
        mCountry = v.findViewById(R.id.tv_country);
        mCity = v.findViewById(R.id.tv_city);
        mFrontSide = v.findViewById(R.id.iv_get_front);
        mBackSide = v.findViewById(R.id.iv_back_side);
        mQuit = v.findViewById(R.id.btn_quit);
        mDelete = v.findViewById(R.id.btn_delete);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNameCoaster.setText(coaster.getName());
        mShape.setText(coaster.getmShape());
        mCountry.setText(coaster.getmCountry());
        mCity.setText(coaster.getmCity());

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("CLICK", "ID " + coaster.getmId());

                mDataHelper.deleteCoasterById(coaster.getmId());

                getFragmentManager()
                        .beginTransaction().replace(R.id.fr_start_container, BaseFragment.newInstance())
                        .addToBackStack(BaseFragment.class.getName())
                        .commit();
            }
        });
    }

    public void setCoaster (Coaster coaster) {
        this.coaster = coaster;
    }
}