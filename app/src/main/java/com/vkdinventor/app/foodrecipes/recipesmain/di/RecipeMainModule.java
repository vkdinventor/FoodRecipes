package com.vkdinventor.app.foodrecipes.recipesmain.di;

import com.vkdinventor.app.foodrecipes.api.RecipeClient;
import com.vkdinventor.app.foodrecipes.api.RecipeService;
import com.vkdinventor.app.foodrecipes.libs.base.EventBus;
import com.vkdinventor.app.foodrecipes.recipesmain.GetNextRecipeInteractor;
import com.vkdinventor.app.foodrecipes.recipesmain.GetNextRecipeInteractorImpl;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainPresenter;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainPresenterImpl;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainRepository;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainRepositoryImpl;
import com.vkdinventor.app.foodrecipes.recipesmain.SaveRecipeInteractor;
import com.vkdinventor.app.foodrecipes.recipesmain.SaveRecipeInteractorImpl;
import com.vkdinventor.app.foodrecipes.recipesmain.ui.RecipeMainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ykro.
 */
@Module
public class RecipeMainModule {
    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeMainView providesRecipeMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor){
        return new RecipeMainPresenterImpl(eventBus, view, saveInteractor, getNextInteractor);
    }

    @Provides
    @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository){
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repository){
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeMainRepository providesRecipeMainRepository(EventBus eventBus, RecipeService service){
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }

}
