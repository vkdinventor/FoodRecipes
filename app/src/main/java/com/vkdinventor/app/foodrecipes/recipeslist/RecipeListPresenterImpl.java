package com.vkdinventor.app.foodrecipes.recipeslist;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.libs.base.EventBus;
import com.vkdinventor.app.foodrecipes.recipeslist.events.RecipeListEvent;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.RecipeListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by einfochips on 20/6/17.
 */

public class RecipeListPresenterImpl implements RecipeListPresenter {

    EventBus eventBus;
    RecipeListRepository repository;
    RecipeListView view;

    public RecipeListPresenterImpl(EventBus eventBus, RecipeListRepository repository, RecipeListView view) {
        this.eventBus = eventBus;
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(true);

    }

    @Override
    public void getRecipes() {
        repository.getSavedRecipes();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        repository.deleteRecipe(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean isFav =  recipe.getFavorite();
        recipe.setFavorite(!isFav);
        repository.updateRecipe(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        int id = event.getType();

        switch (id){
            case RecipeListEvent.LIST:
                view.setRecipes(event.getRecipeList());
                break;
            case RecipeListEvent.DELETE:
                view.recipeDeleted(event.getRecipeList().get(0));
                break;
            case RecipeListEvent.UPDATE:
                view.recipeUpdated();
                break;
        }
    }

    @Override
    public RecipeListView getView() {
        return view;
    }
}
