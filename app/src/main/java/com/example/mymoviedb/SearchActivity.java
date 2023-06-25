package com.example.mymoviedb;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private List<Movie> mMovies;
    private MyAdapter adapter;
    private RecyclerView recyclerView;

    public SearchActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: Starting.");

        Button btnNavToMain = (Button) findViewById(R.id.backToMainActivity);
        btnNavToMain.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Clicked btnNavToMain.");
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Intent newIntent = new Intent(SearchActivity.this, MainActivity.class);
            newIntent.putExtra("query", query);
            startActivity(newIntent);
        }
    }


}
