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

    }

    @Override
    public void toggleFavorite(Recipe recipe) {

    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if(event.getType() == RecipeListEvent.LIST){
            view.setRecipes(event.getRecipeList());
        }
    }

    @Override
    public RecipeListView getView() {
        return view;
    }
}
