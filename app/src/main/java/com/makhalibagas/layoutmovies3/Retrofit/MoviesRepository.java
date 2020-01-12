package com.makhalibagas.layoutmovies3.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {
    private static MoviesRepository moviesRepository;
    private MoviesApi moviesApi;

    public MoviesRepository(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    public static MoviesRepository getInstance(){
        if (moviesRepository == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            moviesRepository = new MoviesRepository(retrofit.create(MoviesApi.class));
        }
        return moviesRepository;
    }

    public void getDataDiscover(final MoviesGetCallBack moviesGetCallBack){
        moviesApi.getDiscoverMovies("e8d81f73ff62ea119d3cb4b7eaddf71b","en-US",2)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if (response.isSuccessful()){
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null){
                                moviesGetCallBack.onSuccess(moviesResponse.getMovies());
                            }else {
                                moviesGetCallBack.onError();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        moviesGetCallBack.onError();
                    }
                });
    }
}
