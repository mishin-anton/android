package com.vltavasoft.coasters.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vltavasoft.coasters.R;

public class RegistrationFragment extends Fragment {

    private Button btnEnter;

    public static RegistrationFragment newInstanse() {
        return new RegistrationFragment();
    }

    private View.OnClickListener mEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_registration, container, false);

        btnEnter = v.findViewById(R.id.btn_enter);

        return v;
    }
}
