package com.vkdinventor.app.foodrecipes.recipesmain;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;

/**
 * Created by einfochips on 15/6/17.
 */

public class SaveRecipeInteractorImpl implements SaveRecipeInteractor{

    RecipeMainRepository repository;

    public SaveRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Recipe recipe) {
        repository.getNextRecipe();
    }
}
