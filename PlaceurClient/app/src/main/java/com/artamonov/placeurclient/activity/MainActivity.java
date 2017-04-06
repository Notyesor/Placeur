package com.artamonov.placeurclient.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.activity.adapter.TabPagerAdapter;
import com.artamonov.placeurclient.activity.fragment.RecommendationsFragment;
import com.artamonov.placeurclient.activity.fragment.SettingsFragment;
import com.artamonov.placeurclient.activity.fragment.TopPlacesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    SupportMapFragment mapFragment;

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
    }

    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this);
        adapter.addFragment(mapFragment, "Карта");
        adapter.addFragment(new RecommendationsFragment(), "Рекомендации");
        adapter.addFragment(new TopPlacesFragment(), "Топ мест");
        adapter.addFragment(new SettingsFragment(), "Настройки");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-50.3, -50.5);
        MarkerOptions marker = new MarkerOptions().position(sydney).title("Marker in Sydney").draggable(true);
        googleMap.addMarker(marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.setMaxZoomPreference(googleMap.getMaxZoomLevel());
    }
}
