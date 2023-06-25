package com.example.mymoviedb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private static final String TAG = "adapter";

    private List<Movie> moviesList;
    private List<Movie> filteredMoviesList;
    private OnMovieListener mOnMovieListener;
    Context mContext;

    public MyAdapter(Context context, List<Movie> moviesLst, OnMovieListener onMovieListener) {
        mContext = context;
        moviesList = moviesLst;
        filteredMoviesList = new ArrayList<>(moviesList);
        mOnMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view, mOnMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Movie curMovie = moviesList.get(position);
        holder.movieName.setText(curMovie.title);
        holder.movieDescription.setText(curMovie.overview);
        String imgUrl = "https://image.tmdb.org/t/p/original" + curMovie.poster_path;
        Picasso.with(mContext).load(imgUrl).placeholder(R.drawable.ic_launcher_foreground).into(holder.movieImage);
//        https://api.themoviedb.org/3/CGJAj5kNWQZypNgUSTTQrFlnG.jpg
    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public Filter getFilter() {
        return searchedMovies;
    }

    private Filter searchedMovies = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Movie> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(moviesList);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Movie movie: moviesList) {
                    if (movie.title.toLowerCase().contains(filterPattern)) {
                        filteredList.add(movie);
                    }
                }
                filteredMoviesList = filteredList;
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredMoviesList.clear();
                filteredMoviesList.addAll((List) results.values);
                notifyDataSetChanged();
        }
    };


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movieName;
        TextView movieDescription;
        ImageView movieImage;
        public final View mView;
        OnMovieListener onMovieListener;
//        RelativeLayout parentLayout;

        public MyViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            mView = itemView;

            movieName = itemView.findViewById(R.id.name);
            movieDescription = itemView.findViewById(R.id.movie_description);
            movieImage = itemView.findViewById(R.id.image);
            this.onMovieListener = onMovieListener;

            itemView.setOnClickListener(this);
//            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

        @Override
        public void onClick(View v) {
            onMovieListener.onItemClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnMovieListener
            /* parent activity will implement this method to respond to click events */
    {
        void onItemClick(int position);
    }
}



