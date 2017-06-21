package com.vkdinventor.app.foodrecipes.recipeslist.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.R;
import com.vkdinventor.app.foodrecipes.libs.GlideImageLoader;
import com.vkdinventor.app.foodrecipes.libs.GreenRobotEventBus;
import com.vkdinventor.app.foodrecipes.libs.base.ImageLoader;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListPresenter;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListPresenterImpl;
import com.vkdinventor.app.foodrecipes.recipeslist.RecipeListRepositoryImpl;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters.OnItemClickListener;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters.RecipesAdapter;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView {

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


        com.vkdinventor.app.foodrecipes.libs.base.EventBus eventBus = new GreenRobotEventBus(EventBus.getDefault());

        presenter =  new RecipeListPresenterImpl(eventBus,new RecipeListRepositoryImpl(eventBus),this);
        presenter.onCreate();
        setupAdapter();
        presenter.getRecipes();

    }

    void setupAdapter(){
        recipeList = new ArrayList<Recipe>();
        imageLoader = new GlideImageLoader(Glide.with(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipesAdapter(recipeList, imageLoader, new OnItemClickListener() {
            @Override
            public void onFavClick(Recipe recipe) {
                recipe.setFavorite(true);
            }

            @Override
            public void onItemClick(Recipe recipe) {

            }

            @Override
            public void onDeleteClick(Recipe recipe) {
                recipe.delete();
            }
        });

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {

    }

    @Override
    public void recipeDeleted(Recipe recipe) {

    }
}
