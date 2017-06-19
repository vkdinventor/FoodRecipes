package com.vkdinventor.app.foodrecipes.recipesmain;

import java.util.Random;

/**
 * Created by einfochips on 16/6/17.
 */

public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor {

    private static final int RECIPE_RANGE = 100;
    RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.setRecipePage(new Random().nextInt(RECIPE_RANGE));
        repository.getNextRecipe();
    }
}
