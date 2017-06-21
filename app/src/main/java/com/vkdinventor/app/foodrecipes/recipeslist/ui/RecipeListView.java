package com.vkdinventor.app.foodrecipes.recipeslist.ui;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;

import java.util.List;


/**
 * Created by ykro.
 */
public interface RecipeListView {
    void setRecipes(List<Recipe> data);
    void recipeUpdated();
    void recipeDeleted(Recipe recipe);
}
