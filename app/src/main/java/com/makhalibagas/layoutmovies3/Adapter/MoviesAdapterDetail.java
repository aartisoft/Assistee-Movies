package com.makhalibagas.layoutmovies3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makhalibagas.layoutmovies3.Activity.DetailActivity;
import com.makhalibagas.layoutmovies3.Model.Movies;
import com.makhalibagas.layoutmovies3.R;

import java.util.ArrayList;

public class MoviesAdapterDetail extends RecyclerView.Adapter<MoviesAdapterDetail.MoviesViewHolder> {

    public MoviesAdapterDetail(Context context) {
        this.context = context;
        this.moviesArrayList = new ArrayList<>();
    }

    public void setListNyaa(ArrayList<Movies> movies){
        this.moviesArrayList = movies;
        notifyDataSetChanged();
    }

    private Context context;
    private ArrayList<Movies> moviesArrayList;
    @NonNull
    @Override
    public MoviesAdapterDetail.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail,parent,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterDetail.MoviesViewHolder holder, int position) {
        Movies movies = moviesArrayList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movies.getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
         MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detail_poster);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movies movies = moviesArrayList.get(position);
            Intent data = new Intent(v.getContext(), DetailActivity.class);
            data.putExtra(DetailActivity.EXTRA_MOVIES,movies);
            v.getContext().startActivity(data);
        }
    }
}
