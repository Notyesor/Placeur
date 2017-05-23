package com.artamonov.placeurclient.activity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.dto.ListToken;
import com.artamonov.placeurclient.dto.MarkedPlaceDTO;
import com.artamonov.placeurclient.dto.AuthToken;
import com.artamonov.placeurclient.service.ApiFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopPlacesFragment extends Fragment {

    private static final String PLACE_TITLE = "place_title";
    private static final String PLACE_ADDRESS = "place_address";
    ListView listView;
    List<MarkedPlaceDTO> list;

    public TopPlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_places, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        Call<ListToken> callback = ApiFactory.getRatingService().getTopPlaces();
        callback.enqueue(new Callback<ListToken>() {
            @Override
            public void onResponse(Call<ListToken> call, Response<ListToken> response) {
                ListToken token = response.body();
                list = token.getList();
                List<HashMap<String, Object>> array = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    MarkedPlaceDTO placeDTO = list.get(i);
                    HashMap<String, Object> hm = new HashMap<>();
                    hm.put(PLACE_TITLE, placeDTO.getPlaceDTO().getTitle());
                    hm.put(PLACE_ADDRESS, placeDTO.getMark().toString());
                    array.add(hm);
                }
                SimpleAdapter adapter = new SimpleAdapter(getContext(),
                        array,
                        R.layout.places_list,
                        new String[]{PLACE_TITLE, PLACE_ADDRESS},
                        new int[] {R.id.place_title, R.id.place_address});
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListToken> call, Throwable t) {

            }
        });
        return view;
    }
}
