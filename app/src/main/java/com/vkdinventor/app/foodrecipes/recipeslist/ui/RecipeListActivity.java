package com.vkdinventor.app.foodrecipes.recipeslist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.FacebookRecipesApp;
import com.vkdinventor.app.foodrecipes.R;
import com.vkdinventor.app.foodrecipes.libs.GlideImageLoader;
import com.vkdinventor.app.foodrecipes.libs.GreenRobotEventBus;
import com.vkdinventor.app.foodrecipes.libs.base.ImageLoader;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListPresenter;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListPresenterImpl;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListRepositoryImpl;
import com.vkdinventor.app.foodrecipes.recipeslist.di.RecipeListComponent;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters.OnItemClickListener;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters.RecipesAdapter;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    RecipesAdapter adapter;
    List<Recipe> recipeList;

    ImageLoader imageLoader;

    RecipeListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupInjection();
        presenter.onCreate();
        setupAdapter();
        presenter.getRecipes();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    void setupInjection(){
        RecipeListComponent  recipeListComponent = FacebookRecipesApp.getRecipeListComponent(this, this, this);
        presenter = recipeListComponent.getRecipeListPresenter();
        adapter = recipeListComponent.getRecipeListAdapter();
    }

    void setupAdapter(){
        recipeList = new ArrayList<Recipe>();
        imageLoader = new GlideImageLoader(Glide.with(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
        Snackbar.make(mainContent,"Recipe deleted",Snackbar.LENGTH_SHORT);
    }
}
