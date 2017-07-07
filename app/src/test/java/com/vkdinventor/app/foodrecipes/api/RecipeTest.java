package com.vkdinventor.app.foodrecipes.api;

import com.vkdinventor.app.foodrecipes.BaseTest;
import com.vkdinventor.app.foodrecipes.BuildConfig;
import com.vkdinventor.app.foodrecipes.Entity.RecipeSearchResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by einfochips on 7/7/17.
 */

@Config(constants = BuildConfig.class, sdk = 21)
public class RecipeTest  extends BaseTest{

    RecipeService recipeService;



    @Override
    public void setup() throws Exception {
        super.setup();
        RecipeClient client = new RecipeClient();
        recipeService = client.getRecipeService();
    }

    @Test
    public void doSearch_getRecipe() throws Exception{

        Call<RecipeSearchResponse> call = recipeService.search("c6eb7c8aa8019ce92fff536531d75851",
                "r",1,1);

        Response<RecipeSearchResponse> response = call.execute();
        assertTrue(response.isSuccessful());

        RecipeSearchResponse recipeSearchResponse = response.body();

        //assertEquals(1,recipeSearchResponse.getCount());

        assertNotNull(recipeSearchResponse.getFirstRecipe());

    }

    @Test
    public void doSearch_getRandomRecipe() throws Exception {
        int page = new Random().nextInt(10000);

        Call<RecipeSearchResponse> call = recipeService.search("c6eb7c8aa8019ce92fff536531d75851",
                "r",1,page);

        Response<RecipeSearchResponse> response = call.execute();
        assertTrue(response.isSuccessful());

        RecipeSearchResponse recipeSearchResponse = response.body();

        //assertEquals(1,recipeSearchResponse.getCount());

        assertNotNull(recipeSearchResponse.getFirstRecipe());
    }
}
