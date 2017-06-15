package com.vkdinventor.app.foodrecipes.Entity;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.vkdinventor.app.foodrecipes.db.RecipeDatabase;

/**
 * Created by einfochips on 15/6/17.
 */

@Table(database = RecipeDatabase.class)
public class Recipe extends BaseModel{

    @SerializedName("recipe_id")
    @PrimaryKey
    private String recipeId;

    @SerializedName("title")
   @Column private String title;

    @SerializedName("source_url")
    @Column private String sourceUrl;
    @SerializedName("image_url")
    @Column private String imageUrl;

    private Boolean favoruite;


    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getFavoruite() {
        return favoruite;
    }

    public void setFavoruite(Boolean favoruite) {
        this.favoruite = favoruite;
    }

    public boolean equal(Object o){
        boolean isEqual = false;
        if(o instanceof Recipe){
            Recipe recipe = (Recipe)o;
            isEqual = this.recipeId.equals(recipe.getRecipeId());
        }
        return isEqual;
    }

}