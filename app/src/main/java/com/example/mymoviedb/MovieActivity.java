package com.example.mymoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    private static final String TAG = "MovieActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();

        Button btnNavToMain = (Button) findViewById(R.id.backButton);
        btnNavToMain.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Clicked btnNavToMain.");
            Intent intent = new Intent(MovieActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if (getIntent().hasExtra("cur_movie")) {
            Movie movie = getIntent().getParcelableExtra("cur_movie");
            assert movie != null;
            setMovie(movie.poster_path, movie.title, movie.overview, movie.release_date, movie.vote_average);
        }
    }


    private void setMovie(String imageUrl, String imageName, String imageDescription,
                          String releaseDate, double movieRating){
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView name = findViewById(R.id.name);
        name.setText(imageName);

        TextView description = findViewById(R.id.description);
        description.setText(imageDescription);

        TextView date = findViewById(R.id.release_date);
        date.setText(releaseDate);

        TextView rating = findViewById(R.id.movie_rating);
        rating.setText(String.valueOf(movieRating));

        ImageView image = findViewById(R.id.image);
        String imgUrl = "https://image.tmdb.org/t/p/original" + imageUrl;
//        String imgUrl = "https://www.themoviedb.org/t/p/w188_and_h282_bestv2/" + imageUrl;
        Picasso.with(this.getApplicationContext()).load(imgUrl).placeholder(R.drawable.ic_launcher_foreground).into(image);

    }

}
