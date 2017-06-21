package com.vkdinventor.app.foodrecipes.recipeslist.ui.adapters;


import com.vkdinventor.app.foodrecipes.Entity.Recipe;

/**
 * Created by ykro.
 */
public interface OnItemClickListener {
    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
}
