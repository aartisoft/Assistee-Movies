package com.makhalibagas.layoutmovies3.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.makhalibagas.layoutmovies3.Model.Movies;

import java.util.ArrayList;

public class MoviesResponse {
    @SerializedName("results")
    @Expose
    private ArrayList<Movies> movies;

    public ArrayList<Movies> getMovies() {
        return movies;
    }
}
