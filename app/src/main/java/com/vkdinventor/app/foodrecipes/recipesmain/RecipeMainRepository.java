package com.vkdinventor.app.foodrecipes.recipesmain;


import com.vkdinventor.app.foodrecipes.Entity.Recipe;

/**
 * Created by ykro.
 */
public interface RecipeMainRepository {
    public final static int COUNT = 1;
    public final static String RECENT_SORT = "r";
    public final static int RECIPE_RANGE = 100000;

    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setRecipePage(int recipePage);
}
