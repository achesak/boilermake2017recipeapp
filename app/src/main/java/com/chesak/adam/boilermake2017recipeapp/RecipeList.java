package com.chesak.adam.boilermake2017recipeapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by adam on 1/21/17.
 */

final public class RecipeList implements Serializable {

    private ArrayList<Recipe> recipes;

    public RecipeList() {
        this.recipes = new ArrayList<>();
    }

    public RecipeList(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe get(int index) {
        return recipes.get(index);
    }

    public void add(Recipe recipe) {
        recipes.add(recipe);
    }

    public void remove(int index) {
        recipes.remove(index);
    }

    public int size() {
        return recipes.size();
    }

    public Recipe getMostRecent() {
        return recipes.get(recipes.size() - 1);
    }
}
