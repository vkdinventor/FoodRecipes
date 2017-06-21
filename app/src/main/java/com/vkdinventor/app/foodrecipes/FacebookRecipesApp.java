package com.vkdinventor.app.foodrecipes;

import android.app.Activity;
import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.vkdinventor.app.foodrecipes.libs.di.LibsModule;
import com.vkdinventor.app.foodrecipes.recipesmain.di.DaggerRecipeMainComponent;
import com.vkdinventor.app.foodrecipes.recipesmain.di.RecipeMainComponent;
import com.vkdinventor.app.foodrecipes.recipesmain.di.RecipeMainModule;
import com.vkdinventor.app.foodrecipes.recipesmain.ui.RecipeMainView;

/**
 * Created by einfochips on 14/6/17.
 */

public class FacebookRecipesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        FlowManager.init(this);
    }

    public static RecipeMainComponent getRecipeMainComponent(RecipeMainView view, Activity activity){
        RecipeMainComponent recipeMainComponent  = DaggerRecipeMainComponent.builder()
                .recipeMainModule(new RecipeMainModule(view))
                .libsModule(new LibsModule(activity))
                .build();
        return recipeMainComponent;
    }
}
