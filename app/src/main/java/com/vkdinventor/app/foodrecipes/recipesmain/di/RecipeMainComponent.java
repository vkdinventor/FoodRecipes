package com.vkdinventor.app.foodrecipes.recipesmain.di;

import com.vkdinventor.app.foodrecipes.libs.base.ImageLoader;
import com.vkdinventor.app.foodrecipes.libs.di.LibsModule;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ykro.
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
    //void inject(RecipeMainActivity activity);
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();
}
