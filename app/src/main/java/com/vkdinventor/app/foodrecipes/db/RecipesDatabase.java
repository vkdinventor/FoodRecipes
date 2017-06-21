package com.vkdinventor.app.foodrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by einfochips on 15/6/17.
 */

@Database(name = RecipesDatabase.NAME, version = RecipesDatabase.VERSION)
public class RecipesDatabase {

    public static final String NAME = "recipiedb";
    public static final int VERSION = 1;
}
