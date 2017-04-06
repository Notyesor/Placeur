package com.artamonov.placeurclient.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.activity.adapter.TabPagerAdapter;
import com.artamonov.placeurclient.activity.fragment.SignInFragment;
import com.artamonov.placeurclient.activity.fragment.SignUpFragment;

public class AuthActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

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
        adapter.addFragment(new SignInFragment(), "Вход");
        adapter.addFragment(new SignUpFragment(), "Регистрация");
        viewPager.setAdapter(adapter);
    }
}
