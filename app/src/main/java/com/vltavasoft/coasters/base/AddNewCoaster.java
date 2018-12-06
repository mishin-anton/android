package com.vltavasoft.coasters.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.vltavasoft.coasters.database.Coaster;
import com.vltavasoft.coasters.database.DataHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class AddNewCoaster extends Fragment {

    public static final int SELECT_FRONT_PHOTO = 1;
    public static final int SELECT_BACK_PHOTO = 2;

    private EditText mNameCoaster;
    private EditText mShape;
    private EditText mCountry;
    private EditText mCity;
    private ImageView mFrontSide;
    private ImageView mBackSide;
    private Button mSave;
    private DataHelper mDataHelper = new DataHelper();

    public static AddNewCoaster newInstance() {
        return new AddNewCoaster();
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

        mFrontSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_FRONT_PHOTO);
            }
        });

        mBackSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_BACK_PHOTO);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coaster coaster = new Coaster
                        (mNameCoaster.getText().toString(),
                                mShape.getText().toString(),
                                mCountry.getText().toString(),
                                mCity.getText().toString(),
                                null,
                                null);
                mDataHelper.insertNewCoaster(coaster);


                getFragmentManager()
                        .beginTransaction().replace(R.id.fr_start_container, BaseFragment.newInstance())
                        .addToBackStack(BaseFragment.class.getName())
                        .commit();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case SELECT_FRONT_PHOTO:
                mFrontSide.setImageBitmap(prepareImageFromChooser(resultCode, imageReturnedIntent));
                break;
            case SELECT_BACK_PHOTO:
                mBackSide.setImageBitmap(prepareImageFromChooser(resultCode, imageReturnedIntent));
                break;
        }
    }

    private Bitmap prepareImageFromChooser(int resultCode, Intent imageReturnedIntent) {
        if(resultCode == RESULT_OK){
            Uri selectedImage = imageReturnedIntent.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContext().getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);

            return yourSelectedImage;
        }

        return null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle(R.string.app_name);
    }
}
