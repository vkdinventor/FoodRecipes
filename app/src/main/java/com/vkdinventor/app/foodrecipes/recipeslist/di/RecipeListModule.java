package com.vkdinventor.app.foodrecipes.recipeslist.di;

import android.support.v7.widget.RecyclerView;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.libs.base.EventBus;
import com.vkdinventor.app.foodrecipes.libs.base.ImageLoader;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListPresenter;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListPresenterImpl;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListRepository;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListRepositoryImpl;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.RecipeListView;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters.OnItemClickListener;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters.RecipesAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by einfochips on 21/6/17.
 */


@Module
public class RecipeListModule {

    RecipeListView view;
    OnItemClickListener onItemClickListener;

    public RecipeListModule(RecipeListView view,OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;

    }

    @Provides
    RecipeListRepository provideRepository (EventBus eventBus){
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides
    RecipeListPresenter providePresenter(EventBus eventBus, RecipeListRepository recipeListRepository){
        return new RecipeListPresenterImpl(eventBus,recipeListRepository,view);
    }

    @Provides
    RecipesAdapter provideAdapter(List<Recipe> recipeList, ImageLoader imageLoader){
        return new RecipesAdapter(recipeList,imageLoader,onItemClickListener);
    }

    @Provides
    List<Recipe> provideRecipeList(){
        return new ArrayList<Recipe>();
    }

}
