package com.vkdinventor.app.foodrecipes;

import com.vkdinventor.app.foodrecipes.libs.base.EventBus;
import com.vkdinventor.app.foodrecipes.recipesmain.GetNextRecipeInteractor;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainPresenter;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainPresenterImpl;
import com.vkdinventor.app.foodrecipes.recipesmain.SaveRecipeInteractor;
import com.vkdinventor.app.foodrecipes.recipesmain.ui.RecipeMainView;

import org.bouncycastle.jce.provider.symmetric.ARC4;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;


/**
 * Created by einfochips on 7/7/17.
 */

public class RecipeMainPresenterImplTest  extends BaseTest{

    @Mock
    private EventBus eventBus;

    @Mock
    private RecipeMainView view;

    @Mock
    private SaveRecipeInteractor saveInteractor;
    @Mock
    private GetNextRecipeInteractor getNextInteractor;

    RecipeMainPresenter presenter;

    @Override
    public void setup() throws Exception {
        super.setup();
        presenter = new RecipeMainPresenterImpl(eventBus,view,saveInteractor,getNextInteractor);
    }

    @Test
    public void test_onCreate(){
        presenter.onCreate();
        verify(eventBus).register(presenter);
    }
}
