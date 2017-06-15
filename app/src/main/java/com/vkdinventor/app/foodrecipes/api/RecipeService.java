package com.vkdinventor.app.foodrecipes.api;

import com.vkdinventor.app.foodrecipes.Entity.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by einfochips on 15/6/17.
 */

public interface RecipeService {


    @GET("search")
    Call<RecipeResponse> search(@Query("key") String key,
                                @Query("q") String query,
                                @Query("count") int count,
                                @Query("page") int page);
}
