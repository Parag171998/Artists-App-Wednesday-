package com.example.wednesdaytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView;

import com.example.wednesdaytask.Models.ArtistSongs;
import com.example.wednesdaytask.Models.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Artists");
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        retrieveJson("jack+johnson");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("Search Artists!");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                retrieveJson(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void retrieveJson(String artist) {

        Call<ArtistSongs> artistSongsCall = ApiClient.getInstance().getApi().getArtist(artist, "25");
        artistSongsCall.enqueue(new Callback<ArtistSongs>() {
            @Override
            public void onResponse(Call<ArtistSongs> call, Response<ArtistSongs> response) {
                ArtistSongs artistSongs = response.body();
                List<Result> resultList = new ArrayList<>();
                resultList.addAll(artistSongs.getResults());

                ArtistAdapter artistAdapter = new ArtistAdapter(getApplicationContext(),resultList);
                recyclerView.setAdapter(artistAdapter);
            }

            @Override
            public void onFailure(Call<ArtistSongs> call, Throwable t) {

            }
        });
    }
}
