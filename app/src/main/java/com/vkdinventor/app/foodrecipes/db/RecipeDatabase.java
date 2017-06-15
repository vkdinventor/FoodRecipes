package com.vkdinventor.app.foodrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by einfochips on 15/6/17.
 */

@Database(name = RecipeDatabase.NAME, version = RecipeDatabase.VERSION)
public class RecipeDatabase {

    public static final String NAME = "recipiedb";
    public static final int VERSION = 1;
}
