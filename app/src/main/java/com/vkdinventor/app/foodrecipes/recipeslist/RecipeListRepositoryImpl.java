package com.vkdinventor.app.foodrecipes.recipeslist;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.libs.base.EventBus;
import com.vkdinventor.app.foodrecipes.recipeslist.events.RecipeListEvent;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by einfochips on 20/6/17.
 */

public class RecipeListRepositoryImpl implements RecipeListRepository{

    EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        List<Recipe> recipeList = new Select().from(Recipe.class).queryList();
        post(recipeList);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        recipe.delete();
        RecipeListEvent recipeListEvent = new RecipeListEvent(RecipeListEvent.DELETE,
                Arrays.asList(recipe));
        eventBus.post(recipeListEvent);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        RecipeListEvent recipeListEvent = new RecipeListEvent(RecipeListEvent.UPDATE,null);
        eventBus.post(recipeListEvent);
    }

    void post(List<Recipe> recipeList){
        RecipeListEvent recipeListEvent = new RecipeListEvent(RecipeListEvent.LIST,recipeList);
        eventBus.post(recipeListEvent);
    }
}
