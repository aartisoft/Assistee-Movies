package com.makhalibagas.layoutmovies3.Slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.makhalibagas.layoutmovies3.R;
import com.makhalibagas.layoutmovies3.Slider.Slide;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    ImageView backdrop;
    private Context context;
    private List<Slide> slideList;

    public ViewPagerAdapter(Context context) {
        this.context = context;
        this.slideList = new ArrayList<>();
    }

    public void setSlideList(ArrayList<Slide> slideList){
        this.slideList = slideList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.viewpageritem,container,false);

        Slide slide = slideList.get(position);
        backdrop = view.findViewById(R.id.backdropViewPager);
        backdrop.setImageResource(slide.getBackdrop());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return slideList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
