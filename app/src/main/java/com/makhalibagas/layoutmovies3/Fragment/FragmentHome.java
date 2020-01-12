package com.makhalibagas.layoutmovies3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.makhalibagas.layoutmovies3.Model.Movies;
import com.makhalibagas.layoutmovies3.Adapter.MoviesAdapter;
import com.makhalibagas.layoutmovies3.Retrofit.MoviesGetCallBack;
import com.makhalibagas.layoutmovies3.Retrofit.MoviesRepository;
import com.makhalibagas.layoutmovies3.R;
import com.makhalibagas.layoutmovies3.Slider.Slide;
import com.makhalibagas.layoutmovies3.Slider.ViewPagerAdapter;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private MoviesAdapter moviesAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Slide> slides;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);


        //viewpager menggunakan penyimpanan sendiri bukan dar internet
        viewPager = view.findViewById(R.id.viewpager);
        slides = new ArrayList<>();
        slides.add(new Slide(R.color.colorAccent));
        slides.add(new Slide(R.color.color2));
        slides.add(new Slide(R.color.color3));
        slides.add(new Slide(R.color.colorAccent));
        slides.add(new Slide(R.color.color2));
        slides.add(new Slide(R.color.color3));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPagerAdapter.setSlideList(slides);
        viewPager.setAdapter(viewPagerAdapter);

        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        MoviesRepository moviesRepository = MoviesRepository.getInstance();
        moviesRepository.getDataDiscover(new MoviesGetCallBack() {
            @Override
            public void onSuccess(ArrayList<Movies> movies) {
                moviesAdapter = new MoviesAdapter(getContext());
                recyclerView.setAdapter(moviesAdapter);
                moviesAdapter.setListNya(movies);
            }

            @Override
            public void onError() {

            }
        });
        return view;
    }

}
