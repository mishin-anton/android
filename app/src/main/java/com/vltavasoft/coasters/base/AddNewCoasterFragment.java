package com.vltavasoft.coasters.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.Utils.BItmapScaler;
import com.vltavasoft.coasters.database.Coaster;
import com.vltavasoft.coasters.database.DataHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AddNewCoasterFragment extends Fragment {

    public static final int REQUEST_FRONT_IMAGE_CAMERA = 20001;
    public static final int REQUEST_BACK_IMAGE_CAMERA = 20002;

    private String mFrontImagePath = null; // path for use with ACTION_VIEW intents
    private String mBackImagePath = null;

    private EditText mNameCoaster;
    private Spinner mShape;
    private EditText mCountry;
    private EditText mCity;
    private ImageView mFrontSide;
    private ImageView mBackSide;
    private Button mSave;
    private DataHelper mDataHelper = new DataHelper();

    public static AddNewCoasterFragment newInstance() {
        return new AddNewCoasterFragment();
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

        List<String> listForSpinner = new ArrayList<String>();
        listForSpinner.add("Circle");
        listForSpinner.add("Square");
        listForSpinner.add("Oval");
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity().getApplicationContext(),
                R.layout.spinner_item, listForSpinner);
        mShape.setAdapter(spinnerAdapter);

        mFrontSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_FRONT_IMAGE_CAMERA);
            }
        });

        mBackSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_BACK_IMAGE_CAMERA);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mNameCoaster.getText().toString().equals("") &&
                        !mCountry.getText().toString().equals("") &&
                        !mCity.getText().toString().equals("") &&
                        mFrontSide.getDrawable() != null &&
                        mBackSide.getDrawable() != null) {
                    Toast.makeText(getActivity().getApplicationContext(), "New coaster added", Toast.LENGTH_SHORT).show();

                    Coaster coaster = new Coaster
                            (0, mNameCoaster.getText().toString(),
                                    mShape.getSelectedItem().toString(),
                                    mCountry.getText().toString(),
                                    mCity.getText().toString(),
                                    mFrontImagePath,
                                    mBackImagePath);

                    mDataHelper.insertNewCoaster(coaster);

                    getFragmentManager()
                            .beginTransaction().replace(R.id.fr_start_container, BaseFragment.newInstance())
                            .commit();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please fill all fields and 2 pictures", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    private void dispatchTakePictureIntent(int idImageView) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createFile(idImageView);
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity().getApplicationContext(),
                        "com.vltavasoft.coasters.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, idImageView);
            }
        }
    }

    private File createFile(int selectedImageView) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        if (selectedImageView == REQUEST_FRONT_IMAGE_CAMERA)
            mFrontImagePath = image.getAbsolutePath();
        if (selectedImageView == REQUEST_BACK_IMAGE_CAMERA)
            mBackImagePath = image.getAbsolutePath();

        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == REQUEST_FRONT_IMAGE_CAMERA || requestCode == REQUEST_BACK_IMAGE_CAMERA)
                && resultCode == RESULT_OK) {

            Uri takenPhotoUri = null;

            if (requestCode == REQUEST_FRONT_IMAGE_CAMERA) {
                takenPhotoUri = Uri.parse(mFrontImagePath);
            }
            if (requestCode == REQUEST_BACK_IMAGE_CAMERA) {
                takenPhotoUri = Uri.parse(mBackImagePath);
            }

            Bitmap rawTakenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
            Bitmap resizedBitmap = BItmapScaler.scaleToFitWidth(rawTakenImage, 200);
            // Config bite output stream
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
            try {
                File resizedFile = createFile(0);
                resizedFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(resizedFile);
                fos.write(bytes.toByteArray());
                fos.close();

                // Get path compressed photo
                if (requestCode == REQUEST_FRONT_IMAGE_CAMERA) {
                    File fileForDelete = new File(mFrontImagePath);
                    fileForDelete.delete(); // Delete big size file

                    mFrontImagePath = resizedFile.getAbsolutePath();
                }
                if (requestCode == REQUEST_BACK_IMAGE_CAMERA) {
                    File fileForDelete = new File(mBackImagePath);
                    fileForDelete.delete(); // Delete big size file

                    mBackImagePath = resizedFile.getAbsolutePath();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set compressed thumbs
            if (requestCode == REQUEST_FRONT_IMAGE_CAMERA) {
                mFrontSide.setImageBitmap(resizedBitmap);
            }

            if (requestCode == REQUEST_BACK_IMAGE_CAMERA) {
                mBackSide.setImageBitmap(resizedBitmap);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle(R.string.app_name);
    }
}
