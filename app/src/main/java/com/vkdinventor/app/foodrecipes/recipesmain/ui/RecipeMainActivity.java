package com.vkdinventor.app.foodrecipes.recipesmain.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.FacebookRecipesApp;
import com.vkdinventor.app.foodrecipes.R;
import com.vkdinventor.app.foodrecipes.libs.base.ImageLoader;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainPresenter;
import com.vkdinventor.app.foodrecipes.recipesmain.di.RecipeMainComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView {

    @BindView(R.id.imgRecipe)
    ImageView imgRecipe;
    @BindView(R.id.imgDismiss)
    ImageButton imgDismiss;
    @BindView(R.id.imgKeep)
    ImageButton imgKeep;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutContainer)
    RelativeLayout layoutContainer;

    private Recipe currentRecipe;
    private RecipeMainPresenter mainPresenter;
    RecipeMainComponent component;

    @Inject
    ImageLoader imageLoader;
    @Inject
    RecipeMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);
        setupInjection();
        presenter.getNextRecipe();
        presenter.onCreate();
    }

    private void setupInjection() {
        component = FacebookRecipesApp.getRecipeMainComponent(this, this);
        imageLoader = component.getImageLoader();
        presenter = component.getPresenter();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showUIElements() {
        imgRecipe.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.imgKeep)
    void saveImage(){

    }

    @Override
    public void hideUIElements() {
        //imgRecipe.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {

    }

    @Override
    public void dismissAnimation() {

    }

    @Override
    public void onRecipeSaved() {

    }

    @Override
    public void setRecipe(Recipe recipe) {
        this.currentRecipe = currentRecipe;
        imageLoader.load(imgRecipe,recipe.getImageUrl());


    }

    @Override
    public void onGetRecipeError(String error) {
        Snackbar.make(layoutContainer,"error: "+error, Snackbar.LENGTH_SHORT);

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
