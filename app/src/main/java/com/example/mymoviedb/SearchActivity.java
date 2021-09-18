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
//        ListActivity {

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

//        initSearchTextView();
    }


//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
//
//        // Get the SearchView and set the searchable configuration
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.mySearchView).getActionView();
//        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false);
//        searchView.setSubmitButtonEnabled(true);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d(TAG, "onQueryTextSubmit: query submitted.");
//                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
//                intent.putExtra("query", query);
//                startActivity(intent);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }


//    private void initSearchTextView()
//    /**
//     * initialize recyclerView object, layout and adapter
//     */
//    {
//        Log.d(TAG, "initSearchTextView: init search textView.");
//        recyclerView = findViewById(R.id.recyclerView);
//        adapter = new MyAdapter(this, mMovies);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(adapter);
//    }

//    private void getIncomingIntent(){
//        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
//        if (getIntent().hasExtra("movies_list")) {
//            mMovies = (List<Movie>) getIntent().getSerializableExtra("movies_list");
//        }
//    }

//    private void initSearchTextView() {
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String value = extras.getString("key");
//            //The key argument here must match that used in the other activity
//        }


        //Creating the instance of ArrayAdapter containing list of language names
//        MyAdapter adapter = new MyAdapter(this, mMovies);
//        Getting the instance of AutoCompleteTextView
//        AutoCompleteTextView actv =  (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
//        actv.setThreshold(1);//will start working from first character
//        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//        actv.setTextColor(Color.RED);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.example_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d(TAG, "onQueryTextSubmit: query submitted.");
//                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
//                intent.putExtra("query", query);
//                startActivity(intent);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }



//
//
//
//
//
//
//
//
//
//
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.example_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
////            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        }
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }


}
