package com.artamonov.placeurclient.activity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artamonov.placeurclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopPlacesFragment extends Fragment {


    public TopPlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_places, container, false);
    }

}
