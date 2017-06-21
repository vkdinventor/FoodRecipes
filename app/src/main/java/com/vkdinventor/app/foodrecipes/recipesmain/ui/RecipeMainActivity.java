package com.vkdinventor.app.foodrecipes.recipesmain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.vkdinventor.app.foodrecipes.Entity.Recipe;
import com.vkdinventor.app.foodrecipes.FacebookRecipesApp;
import com.vkdinventor.app.foodrecipes.R;
import com.vkdinventor.app.foodrecipes.UI.login.LoginActivity;
import com.vkdinventor.app.foodrecipes.libs.base.ImageLoader;
import com.vkdinventor.app.foodrecipes.recipeslist.ui.RecipeListActivity;
import com.vkdinventor.app.foodrecipes.recipesmain.RecipeMainPresenter;
import com.vkdinventor.app.foodrecipes.recipesmain.di.RecipeMainComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView, SwipeGestureListener{

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
    RecipeMainComponent component;

    @Inject
    ImageLoader imageLoader;
    @Inject
    RecipeMainPresenter presenter;

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
        setupInjection();
        setupGestureDetector();
        setupImageLoader();
        presenter.getNextRecipe();
        presenter.onCreate();
    }

    private void setupInjection() {
        component = FacebookRecipesApp.getRecipeMainComponent(this, this);
        imageLoader = component.getImageLoader();
        presenter = component.getPresenter();
    }

    private void setupImageLoader() {
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoadingListener(glideRequestListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void setupGestureDetector() {
        gestureDetector = new GestureDetector(this, new SwipeGestureDetector(this));

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
    @Override
    public void onKeep() {
        Snackbar.make(layoutContainer,"Saving Recpie",Toast.LENGTH_SHORT).show();
        if (currentRecipe != null) {
            presenter.saveRecipe(currentRecipe);
        }
    }

    @OnClick(R.id.imgDismiss)
    @Override
    public void onDismiss() {
        Snackbar.make(layoutContainer,"Dismissing Recpie",Toast.LENGTH_SHORT).show();
        presenter.dismissRecipe();
    }

    @Override
    public void hideUIElements() {
        //imgRecipe.setVisibility(View.GONE);
        //linearLayout.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {
        Animation saveAnim = AnimationUtils.loadAnimation(this, R.anim.save_animation);
        imgRecipe.startAnimation(saveAnim);
        saveAnim.setAnimationListener(getAnimationListener());
    }

    @Override
    public void dismissAnimation() {
        Animation dismissAnim = AnimationUtils.loadAnimation(this, R.anim.dismiss_animation);
        imgRecipe.startAnimation(dismissAnim);
        dismissAnim.setAnimationListener(getAnimationListener());
    }

    private Animation.AnimationListener getAnimationListener(){
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgRecipe.setImageResource(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    @Override
    public void onRecipeSaved() {
        currentRecipe.save();
        Snackbar.make(layoutContainer,"Your favoruite recipie saved",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        hideProgress();
        this.currentRecipe = recipe;
        imageLoader.load(imgRecipe,recipe.getImageURL());
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.action_list:
                goToListActivity();
                break;
            case R.id.action_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void goToListActivity(){
        startActivity(new Intent(this, RecipeListActivity.class));
    }

    void logout(){
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
