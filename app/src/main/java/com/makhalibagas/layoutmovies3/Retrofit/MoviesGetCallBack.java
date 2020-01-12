package com.makhalibagas.layoutmovies3.Retrofit;

import com.makhalibagas.layoutmovies3.Model.Movies;

import java.util.ArrayList;

public interface MoviesGetCallBack {
    void onSuccess (final ArrayList<Movies> movies);
    void onError ();
}
