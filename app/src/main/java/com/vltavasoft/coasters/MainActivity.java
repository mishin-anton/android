package com.vltavasoft.coasters;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.v7.widget.Toolbar;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.vltavasoft.coasters.Billing.InAppBillingResources;
import com.vltavasoft.coasters.base.BaseFragment;

public class MainActivity extends AdapterActivity {

    static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1002;
    int permissionCount = 0;

    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Request permission
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
            case REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionCount++;
                } else {
                    if (permissionCount == 0) {
                        new AlertDialog.Builder(this)
                                .setMessage("Without permission you can't save coasters to disk")
                                .setPositiveButton("OK, I understood", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(MainActivity.this,
                                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
                                    }
                                })
                                .show();
                    }
                    permissionCount++;
                }
            default:
                break;
        }
    }

    @Override
    protected Fragment getFragment() {

        return BaseFragment.newInstance();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getFragments() == fragmentManager.findFragmentById(R.id.fr_base)) {
         finish();
        }

        fragmentManager
                .beginTransaction().replace(R.id.fr_start_container, BaseFragment.newInstance())
                .commit();

    }

}
