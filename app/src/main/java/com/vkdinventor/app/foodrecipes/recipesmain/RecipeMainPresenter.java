package com.vkdinventor.app.foodrecipes.recipesmain;


import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.recipesmain.events.RecipeMainEvent;
import com.vkdinventor.app.foodrecipes.recipesmain.ui.RecipeMainView;

/**
 * Created by ykro.
 */
public interface RecipeMainPresenter {
    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);

    void imageReady();
    void imageError(String error);


    RecipeMainView getView();
}
