package com.vkdinventor.app.foodrecipes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by einfochips on 15/6/17.
 */

public class RecipeClient {

    Retrofit retrofit;

    public RecipeClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://food2fork.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RecipeService getRecipeService(){
        return this.retrofit.create(RecipeService.class);
    }
}


