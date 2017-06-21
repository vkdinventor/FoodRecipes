package com.vkdinventor.app.foodrecipes.recipeslist;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.libs.base.EventBus;
import com.vkdinventor.app.foodrecipes.recipeslist.events.RecipeListEvent;

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

    void post(List<Recipe> recipeList){
        RecipeListEvent recipeListEvent = new RecipeListEvent(RecipeListEvent.LIST,recipeList);
        eventBus.post(recipeListEvent);
    }
}
