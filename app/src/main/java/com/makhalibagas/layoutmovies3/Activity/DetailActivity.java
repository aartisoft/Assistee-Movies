package com.makhalibagas.layoutmovies3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makhalibagas.layoutmovies3.Adapter.MoviesAdapter;
import com.makhalibagas.layoutmovies3.Adapter.MoviesAdapterDate;
import com.makhalibagas.layoutmovies3.Adapter.MoviesAdapterDetail;
import com.makhalibagas.layoutmovies3.Model.Movies;
import com.makhalibagas.layoutmovies3.R;
import com.makhalibagas.layoutmovies3.Retrofit.MoviesGetCallBack;
import com.makhalibagas.layoutmovies3.Retrofit.MoviesRepository;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIES = "extra_movies";
    ImageView backdrop,poster;
    TextView title,date,desc;
    RatingBar ratingBar;
    private RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        NgambilDataUntukDetail();
    }

    public void NgambilDataUntukDetail(){
        Movies movies = getIntent().getParcelableExtra(EXTRA_MOVIES);
        if (movies != null){
            backdrop = findViewById(R.id.backdropDetail);
            poster = findViewById(R.id.posterDetail);
            title = findViewById(R.id.titleDetail);
            date = findViewById(R.id.dateDetail);
            desc = findViewById(R.id.descDetail);
            ratingBar = findViewById(R.id.ratingBarDetail);

            recyclerView1 = findViewById(R.id.rvDetail);
            recyclerView2 = findViewById(R.id.rv2Detail);
            recyclerView3 = findViewById(R.id.rv3Detail);
            recyclerView4 = findViewById(R.id.rv4Detail);

            desc.setText(movies.getOverview());
            date.setText(movies.getReleaseDate());
            title.setText(movies.getTitle());
            ratingBar.setRating(5);
            Glide.with(this)
                    .load(movies.getBackdropPath())
                    .into(backdrop);
            Glide.with(this)
                    .load(movies.getPosterPath())
                    .into(poster);

            MoviesRepository moviesRepository = MoviesRepository.getInstance();
            moviesRepository.getDataDiscover(new MoviesGetCallBack() {
                @Override
                public void onSuccess(ArrayList<Movies> movies) {
                    MoviesAdapter moviesAdapter = new MoviesAdapter(getParent());
                    MoviesAdapterDetail moviesAdapterDetail = new MoviesAdapterDetail(getParent());
                    recyclerView1.setAdapter(moviesAdapterDetail);
                    recyclerView1.setLayoutManager(new LinearLayoutManager(getParent(),LinearLayoutManager.HORIZONTAL,false));
                    recyclerView4.setAdapter(moviesAdapter);
                    recyclerView4.setLayoutManager(new GridLayoutManager(getParent(),2));
                    moviesAdapter.setListNya(movies);
                    MoviesAdapterDate moviesAdapterDate = new MoviesAdapterDate(getParent());
                    recyclerView2.setAdapter(moviesAdapterDate);
                    recyclerView3.setAdapter(moviesAdapterDate);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getParent(),LinearLayoutManager.HORIZONTAL,false));
                    recyclerView3.setLayoutManager(new LinearLayoutManager(getParent(),LinearLayoutManager.HORIZONTAL,false));
                    moviesAdapterDetail.setListNyaa(movies);
                    moviesAdapterDate.setListNya(movies);
                }

                @Override
                public void onError() {

                }
            });
        }
    }
}
