package com.vltavasoft.coasters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vltavasoft.coasters.R;

public abstract class AdapterActivity extends AppCompatActivity {

    protected abstract Fragment getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_start_fragment);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fr_start_container, getFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        if (fragmentManager.getBackStackEntryCount() == 1) {
//            Log.d("STACK", "Value ");
//            finish();
//        }else {
//            fragmentManager.popBackStack();
//        }
    }
}
