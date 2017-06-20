package com.vkdinventor.app.foodrecipes.recipesmain;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.libs.base.EventBus;
import com.vkdinventor.app.foodrecipes.recipesmain.events.RecipeMainEvent;
import com.vkdinventor.app.foodrecipes.recipesmain.ui.RecipeMainView;

import org.greenrobot.eventbus.Subscribe;

import static com.vkdinventor.app.foodrecipes.recipesmain.events.RecipeMainEvent.NEXT_EVENT;

/**
 * Created by einfochips on 16/6/17.
 */

public class RecipeMainPresenterImpl implements RecipeMainPresenter {

    private EventBus eventBus;
    private RecipeMainView view;
    private SaveRecipeInteractor saveInteractor;
    private GetNextRecipeInteractor getNextInteractor;

    public RecipeMainPresenterImpl(EventBus eventBus, RecipeMainView view,
                                   SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveInteractor = saveInteractor;
        this.getNextInteractor = getNextInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void getNextRecipe() {
        if (this.view != null) {
            view.hideUIElements();
            view.showProgress();
        }
        getNextInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if(view != null){
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveInteractor.execute(recipe);
    }

    @Override
    public void dismissRecipe() {
        if(view != null){
            view.dismissAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        getNextRecipe();
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if (this.view != null) {
            String error = event.getError();
            if (error != null) {
                view.hideProgress();
                view.onGetRecipeError(error);
            } else {
                if (event.getType() == RecipeMainEvent.NEXT_EVENT) {
                    view.setRecipe(event.getRecipe());
                } else if (event.getType() == RecipeMainEvent.SAVE_EVENT) {
                    view.onRecipeSaved();
                    getNextInteractor.execute();
                }
            }
        }
    }

    @Override
    public void imageReady() {
        view.hideProgress();
        view.showUIElements();
    }

    @Override
    public void imageError(String error) {
        view.onGetRecipeError(error);

    }

    @Override
    public RecipeMainView getView() {
        return view;
    }
}
