package com.example.sonfresh.movieapp;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    public String title;
    public String posterUrl;
    public String overView;
    public String backdrop_path;

    //Constructeur
    public Movie(String title, String posterUrl, String overView) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.overView = overView;
    }

    //Constructor for json
    public Movie(JSONObject objectJson){
        try{
            this.title = objectJson.getString("title");
            this.overView = objectJson.getString("overview");
            this.posterUrl = objectJson.getString("poster_path");
            this.backdrop_path = objectJson.getString("backdrop_path");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Movie> fromJson(JSONArray jsonObjects) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                movies.add(new Movie(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }


    //For return a arrayList of movie
    public static ArrayList<Movie> getFakeMovie(){
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("X-Men", "", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem"));
        movies.add(new Movie("Blade", "", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem"));
        movies.add(new Movie("Le roi scorpion", "", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem"));
        movies.add(new Movie("Avengers", "", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem"));
        movies.add(new Movie("Iron Man", "", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem"));

        return movies;
    }
}
