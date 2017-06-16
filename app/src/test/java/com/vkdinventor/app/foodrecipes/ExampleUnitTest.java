package com.vkdinventor.app.foodrecipes;

import com.vkdinventor.app.foodrecipes.Entity.RecipeSearchResponse;
import com.vkdinventor.app.foodrecipes.api.RecipeClient;

import org.junit.Test;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {


        RecipeClient recipeClient = new RecipeClient();
        Call<RecipeSearchResponse> responseCall = recipeClient.getRecipeService().search("c6eb7c8aa8019ce92fff536531d75851",
                "shredded chicken",1,1);

        System.out.print(responseCall.request().url()+" "+ responseCall.execute().body().getFirstRecipe().getTitle());
    }
}