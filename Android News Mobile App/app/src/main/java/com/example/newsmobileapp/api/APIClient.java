package com.example.newsmobileapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// APIClient class is a utility class that provides a Retrofit instance
// Retrofit is a popular Android library for making HTTP requests
public class APIClient {

    // Base URL of the GNews API, all API requests will be made to this base URL
    private static final String BASE_URL = "https://gnews.io/api/v4/";

    // Retrofit instance, used to make API requests
    private static Retrofit retrofit;

    // returns a Retrofit instance, if the instance is null, it creates a new instance and returns it
    public static Retrofit getClient() {
        // check if the Retrofit instance is null
        if (retrofit == null) {
            // create a new Retrofit instance if it's null
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)   // set the base URL for the API requests
                    .addConverterFactory(GsonConverterFactory.create())    // add a converter factory to convert JSON responses to Java objects
                    .build();   // build the Retrofit instance
        }
        return retrofit;    // return the Retrofit instance
    }
}
