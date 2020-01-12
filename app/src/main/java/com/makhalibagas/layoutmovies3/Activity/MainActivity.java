package com.makhalibagas.layoutmovies3.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.makhalibagas.layoutmovies3.Fragment.FragmentDiscover;
import com.makhalibagas.layoutmovies3.Fragment.FragmentFavorite;
import com.makhalibagas.layoutmovies3.Fragment.FragmentHome;
import com.makhalibagas.layoutmovies3.Fragment.FragmentSearch;
import com.makhalibagas.layoutmovies3.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener bottom = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    fragment = new FragmentHome();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout,fragment,fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_search:
                    fragment = new FragmentSearch();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout,fragment,fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_discover:
                    fragment = new FragmentDiscover();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout,fragment,fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_star:
                    fragment = new FragmentFavorite();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout,fragment,fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomview = findViewById(R.id.bottom_navigation);
        bottomview.setOnNavigationItemSelectedListener(bottom);
        if (savedInstanceState == null){
            bottomview.setSelectedItemId(R.id.navigation_home);
        }
    }
}
