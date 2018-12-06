package com.vltavasoft.coasters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.vltavasoft.coasters.base.BaseFragment;

public class MainActivity extends AdapterActivity {
    @Override
    protected Fragment getFragment() {

        return BaseFragment.newInstance();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            Log.d("STACK", "Value ");
            fragmentManager.popBackStack();

        }else {
            finish();
        }
    }
}
