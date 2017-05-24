package com.artamonov.placeurclient.activity.fragment;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.activity.AuthActivity;
import com.artamonov.placeurclient.dto.UserDTO;
import com.artamonov.placeurclient.store.Store;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    TextView mNicknameView;
    TextView mCityView;

    DialogFragment cityDialog;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mNicknameView = (TextView) view.findViewById(R.id.loginView);
        mCityView = (TextView) view.findViewById(R.id.cityView);

        Button helpButton = (Button) view.findViewById(R.id.helpButton);
        Button cityButton = (Button) view.findViewById(R.id.cityButton);
        Button changePasswordButton = (Button) view.findViewById(R.id.changePasswordButton);
        Button logoutButton = (Button) view.findViewById(R.id.logoutButton);

        cityDialog = new CityChangeAlertDialog();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity parentActivity = getActivity();
                parentActivity.startActivity(new Intent(parentActivity, AuthActivity.class));
                parentActivity.finish();
            }
        });
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityDialog.show(getFragmentManager(), "citydlg");
            }
        });

        UserDTO user = Store.getUser(getActivity().getApplication().getApplicationContext());
        mNicknameView.setText(user.getNickname());
        return view;
    }

}
