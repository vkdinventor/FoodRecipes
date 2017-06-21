package com.vkdinventor.app.foodrecipes.recipeslist.events;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;

import java.util.List;

/**
 * Created by einfochips on 20/6/17.
 */

public class RecipeListEvent {
    private int type;
    private List<Recipe> recipeList;

    public static final int LIST = 0;
    public static final int DELETE = 1;
    public static final int UPDATE = 2;

    public RecipeListEvent(int type, List<Recipe> recipeList) {
        this.type = type;
        this.recipeList = recipeList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
