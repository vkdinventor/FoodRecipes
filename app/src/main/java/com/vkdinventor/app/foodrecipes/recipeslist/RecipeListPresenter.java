package com.vkdinventor.app.foodrecipes.recipeslist;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.recipeslist.events.RecipeListEvent;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.RecipeListView;

/**
 * Created by einfochips on 20/6/17.
 */

public interface RecipeListPresenter {
    void onCreate();
    void onDestroy();

    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);

    RecipeListView getView();
}