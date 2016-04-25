package com.example.shane.mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMoviesPage(View View)
    {
        Intent moviesPage = new Intent(MainActivity.this , MoviesPage.class);
        startActivity(moviesPage);
    }

    public void goToGamesPage(View View)
    {
        Intent gamesPage = new Intent(MainActivity.this , GamesPage.class);
        startActivity(gamesPage);
    }

    public void goToWatchList(View View)
    {
        Intent WatchList = new Intent(MainActivity.this , WatchListPage.class);
        startActivity(WatchList);
    }

    public void goToAboutUs(View View)
    {
        Intent AboutUs = new Intent(MainActivity.this , AboutAppPage.class);
        startActivity(AboutUs);
    }

}
