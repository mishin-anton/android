package com.vltavasoft.coasters.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vltavasoft.coasters.R;

public class AuthFragment extends Fragment {

    private EditText mUserName;
    private EditText mPassword;
    private Button mLogIn;
    private Button mSignUp;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener mOnLogInClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isLogin = false;
            // ЗАГЛУШКА ЕСЛИ EMAIL валидный и пароль валидный
            if ((mLogIn.getText().toString() == "admin") &&
                (mSignUp.getText().toString() == "1")){
                isLogin = true;
            }
        }
    };

    private View.OnClickListener mSignUpClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fr_start_container, RegistrationFragment.newInstanse())
                    .addToBackStack(RegistrationFragment.class.getName())
                    .commit();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_auth, container, false);
        mUserName = v.findViewById(R.id.et_username);
        mPassword = v.findViewById(R.id.et_password);
        mLogIn = v.findViewById(R.id.btn_logIn);
        mSignUp = v.findViewById(R.id.btn_sign_up);

        mLogIn.setOnClickListener(mOnLogInClickListener);
        mPassword.setOnClickListener(mSignUpClickListener);

        return v;
    }
}
