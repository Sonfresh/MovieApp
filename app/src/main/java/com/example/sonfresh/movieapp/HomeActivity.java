package com.example.sonfresh.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toast.makeText(HomeActivity.this, "onFaillure call", Toast.LENGTH_LONG).show();

        onRequestMovieAPI();

    }

    private void onRequestMovieAPI(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",  new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                throwable.printStackTrace();
                Toast.makeText(HomeActivity.this, "onFaillure call", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(HomeActivity.this, "onSucess call", Toast.LENGTH_LONG).show();
                JSONArray myJsonArray = null;
                try {
                    myJsonArray = response.getJSONArray("results");
                    ArrayList<Movie> movies = Movie.fromJson(myJsonArray);
                    ListView lvMovies = (ListView)findViewById(R.id.lvMovies);
                    MoviesAdapter adapter = new MoviesAdapter(HomeActivity.this, movies);

                    lvMovies.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
        );
    }
}
