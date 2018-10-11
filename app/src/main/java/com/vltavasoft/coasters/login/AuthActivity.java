package com.vltavasoft.coasters.login;

import android.support.v4.app.Fragment;

public class AuthActivity extends CreaterFragmentActivity {
    @Override
    protected Fragment getFragment() {

        return AuthFragment.newInstance();
    }
}
