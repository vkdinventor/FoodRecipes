package com.vkdinventor.app.foodrecipes.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by einfochips on 15/6/17.
 */

public class RecipeResponse {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("recipes")
    @Expose
    public List<Recipe> recipes = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe getFirstRecipe () {
        Recipe first = null;
        if(recipes!=null){
            first= recipes.get(0);
        }
        return first;
    }
}
