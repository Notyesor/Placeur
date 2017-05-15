package com.artamonov.placeurclient.activity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.dto.MarkedPlaceDTO;
import com.artamonov.placeurclient.dto.TokenDTO;
import com.artamonov.placeurclient.service.ApiFactory;
import com.artamonov.placeurclient.service.TokenStore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendationsFragment extends Fragment {

    ListView listView;
    List<MarkedPlaceDTO> list;

    public RecommendationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendations, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        TokenDTO token = TokenStore.getToken(getActivity().getApplication());
        Call<List<MarkedPlaceDTO>> call = ApiFactory.getRatingService().makeRecommendation(token.getUser().toString());
        call.enqueue(new Callback<List<MarkedPlaceDTO>>() {
            @Override
            public void onResponse(Call<List<MarkedPlaceDTO>> call, Response<List<MarkedPlaceDTO>> response) {
                list = response.body();
                String[] array = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    MarkedPlaceDTO placeDTO = list.get(i);
                    array[i] = placeDTO.getPlaceDTO().getTitle() + " : " + placeDTO.getMark();
                }
                listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array));
            }

            @Override
            public void onFailure(Call<List<MarkedPlaceDTO>> call, Throwable t) {

            }
        });
        return view;
    }

}
