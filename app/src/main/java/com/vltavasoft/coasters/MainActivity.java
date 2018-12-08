package com.vltavasoft.coasters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ImageView;

import com.vltavasoft.coasters.base.BaseFragment;

public class MainActivity extends AdapterActivity {
    static final int REQUEST_PERMISSION_CAMERA = 1001;
    static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1002;
    int permissionCount = 0;

    @Override
    protected Fragment getFragment() {

        return BaseFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Request permission
        if (checkSelfPermission(Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_PERMISSION_CAMERA);
        } else {
            permissionCount++;
        }

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        } else {
            permissionCount++;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSION_CAMERA:
            case REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionCount++;
                }
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Log.d("STACK", "->  " + fragmentManager.getBackStackEntryCount());

        fragmentManager
                .beginTransaction().replace(R.id.fr_start_container, BaseFragment.newInstance())
                .commit();
    }
}
