package com.artamonov.placeurclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.activity.adapter.TabPagerAdapter;
import com.artamonov.placeurclient.activity.fragment.RecommendationsFragment;
import com.artamonov.placeurclient.activity.fragment.SettingsFragment;
import com.artamonov.placeurclient.activity.fragment.TopPlacesFragment;
import com.artamonov.placeurclient.dto.ListToken;
import com.artamonov.placeurclient.dto.MarkedPlaceDTO;
import com.artamonov.placeurclient.dto.AuthToken;
import com.artamonov.placeurclient.dto.PlaceDTO;
import com.artamonov.placeurclient.dto.UserDTO;
import com.artamonov.placeurclient.service.ApiFactory;
import com.artamonov.placeurclient.store.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    SupportMapFragment mapFragment;
    List<MarkedPlaceDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        UserDTO user = Store.getUser(getApplication().getApplicationContext());
        Toast toast = Toast.makeText(getApplicationContext(), "Добро пожаловать, " + user.getNickname() + "!", Toast.LENGTH_SHORT);
        toast.show();


        Call<ListToken> callback = ApiFactory.getRatingService().getTopPlaces();
        callback.enqueue(new Callback<ListToken>() {
            @Override
            public void onResponse(Call<ListToken> call, Response<ListToken> response) {
                ListToken token = response.body();
                list = token.getList();
            }

            @Override
            public void onFailure(Call<ListToken> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ошибка!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this);
        adapter.addFragment(mapFragment, "Карта");
        adapter.addFragment(new RecommendationsFragment(), "Рекомендации");
        adapter.addFragment(new TopPlacesFragment(), "Топ мест");
        adapter.addFragment(new SettingsFragment(), "Профиль");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (MarkedPlaceDTO placeDTO : list) {
            LatLng latLng = new LatLng(placeDTO.getPlaceDTO().getLatitude(), placeDTO.getPlaceDTO().getLongitude());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLng)
                    .title(placeDTO.getPlaceDTO().getTitle())
                    .snippet(placeDTO.getPlaceDTO().getAddress())
                    .draggable(false);
            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(placeDTO);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
            googleMap.setOnInfoWindowClickListener(new OnPlaceClickListener(this));
            googleMap.setBuildingsEnabled(true);
        }
    }

    private class OnPlaceClickListener implements GoogleMap.OnInfoWindowClickListener {

        Activity activity;

        OnPlaceClickListener(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onInfoWindowClick(Marker marker) {
            MarkedPlaceDTO place = (MarkedPlaceDTO) marker.getTag();
            Intent intent = new Intent(activity, PlaceActivity.class);
            intent.putExtra("place", new Gson().toJson(place));
            startActivity(intent);
        }
    }
}
