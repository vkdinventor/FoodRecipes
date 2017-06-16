package com.vkdinventor.app.foodrecipes.recipesmain;

/**
 * Created by einfochips on 16/6/17.
 */

public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor {

    RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getNextRecipe();
    }
}
