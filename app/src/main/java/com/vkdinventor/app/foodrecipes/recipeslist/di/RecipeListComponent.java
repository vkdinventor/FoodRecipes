package com.vkdinventor.app.foodrecipes.recipeslist.di;

import com.vkdinventor.app.foodrecipes.libs.di.LibsModule;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListPresenter;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListRepository;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters.RecipesAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by einfochips on 21/6/17.
 */

@Singleton
@Component (modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {

    RecipesAdapter getRecipeListAdapter();
    RecipeListRepository getRecipeListRepo();
    RecipeListPresenter getRecipeListPresenter();

}
