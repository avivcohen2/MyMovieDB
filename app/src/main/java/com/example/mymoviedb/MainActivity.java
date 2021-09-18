package com.example.mymoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements MyAdapter.OnMovieListener {
    private static final String TAG = "MainActivity";
    private List<Movie> mMovies = new ArrayList<>();
    private JsonRet root = new JsonRet();
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RetrofitInterface apiService;
    private Call<JsonRet> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        // create search button
        Button btnNavToSearch = (Button) findViewById(R.id.search_button);
        btnNavToSearch.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Clicked btnNavToSearch.");
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("movies_list", (Serializable) mMovies);
            startActivity(intent);
        });

        // get data from server
        apiService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitInterface.class);
        updateAPICall();
        loadJSON();

    }

    private void loadJSON() {
        call.enqueue(new Callback<JsonRet>() {
            @Override
            public void onResponse(Call<JsonRet> call, Response<JsonRet> response) {
                root = response.body();
                assert root != null;
                mMovies = root.results;
                Log.d("TAG", "Response = " + mMovies);
                initRecyclerView();
            }

            @Override
            public void onFailure(Call<JsonRet> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());

            }
        });
    }

    private void initRecyclerView()
    /**
     * initialize recyclerView object, layout and adapter
     */
    {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this, mMovies, this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int position)
    /**
     * go to movie activity after clicking a certain movie
     */
    {
        Log.d(TAG, "onMovieClick: clicked.");
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra("cur_movie", (Parcelable) mMovies.get(position));
        startActivity(intent);
    }


    private void updateAPICall() {
        if (getIntent().hasExtra("query")) {
             call = apiService.getSearchedMovies(Objects.requireNonNull(getIntent().getExtras()).getString("query"));
        }
        else {
            call = apiService.getAllMovies();
        }
    }

}






