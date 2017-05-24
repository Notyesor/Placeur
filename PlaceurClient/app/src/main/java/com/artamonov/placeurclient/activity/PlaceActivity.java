package com.artamonov.placeurclient.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.dto.MarkedPlaceDTO;
import com.artamonov.placeurclient.dto.PublicRatingDTO;
import com.artamonov.placeurclient.dto.RatingDTO;
import com.artamonov.placeurclient.dto.RatingsToken;
import com.artamonov.placeurclient.service.ApiFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String USER_NAME = "USER_NAME";
    public static final String MARK = "MARK";
    public static final String COMMENT = "COMMENT";

    MarkedPlaceDTO place;

    TextView mRatingView;
    TextView mAddressView;
    TextView mDescriptionView;
    SupportMapFragment mapFragment;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        String serializedPlace = getIntent().getStringExtra("place");
        place = new GsonBuilder().setLenient().create().fromJson(serializedPlace, MarkedPlaceDTO.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRatingView = (TextView) findViewById(R.id.rateView);
        mAddressView = (TextView) findViewById(R.id.addressView);
        mDescriptionView = (TextView) findViewById(R.id.descriptionView);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        listView = (ListView) findViewById(R.id.comments);

        mapFragment.getMapAsync(this);

        Double mark = place.getMark();
        mark = Math.rint(mark * 10) / 10;
        mRatingView.setText(mark.toString());
        mAddressView.setText("Адрес: " + place.getPlaceDTO().getAddress());
        mDescriptionView.setText("Описание: " + place.getPlaceDTO().getDescription());

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setTitle(place.getPlaceDTO().getTitle());


        Call<RatingsToken> callback = ApiFactory.getRatingService().getRatings(place.getPlaceDTO().getId().toString());
        callback.enqueue(new Callback<RatingsToken>() {
            @Override
            public void onResponse(Call<RatingsToken> call, Response<RatingsToken> response) {
                RatingsToken token = response.body();
                List<PublicRatingDTO> list = token.getRatings();
                List<HashMap<String, Object>> array = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    PublicRatingDTO rating = list.get(i);
                    HashMap<String, Object> hm = new HashMap<>();
                    hm.put(USER_NAME, rating.getUser());
                    hm.put(MARK, rating.getRatingDTO().getMark());
                    hm.put(COMMENT, rating.getRatingDTO().getDescription());
                    array.add(hm);
                }
                SimpleAdapter adapter = new SimpleAdapter(listView.getContext(),
                        array,
                        R.layout.comment_list,
                        new String[]{USER_NAME, MARK, COMMENT},
                        new int[] {R.id.nicknameId, R.id.markId, R.id.commentId});
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RatingsToken> call, Throwable t) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(place.getPlaceDTO().getLatitude(), place.getPlaceDTO().getLongitude());
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(place.getPlaceDTO().getTitle())
                .snippet(place.getPlaceDTO().getAddress())
                .draggable(false);
        Marker marker = googleMap.addMarker(markerOptions);
        CameraPosition position = new CameraPosition.Builder()
                .target(latLng)
                .zoom(17)
                .tilt(60)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
        googleMap.setBuildingsEnabled(true);
    }
}
