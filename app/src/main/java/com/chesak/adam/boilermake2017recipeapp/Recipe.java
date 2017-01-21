package com.chesak.adam.boilermake2017recipeapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by adam on 1/21/17.
 */

final public class Recipe implements Serializable {

    private String title;
    private ArrayList<String> ingredients;
    private ArrayList<String> steps;

    public Recipe() {
        this.title = "";
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public Recipe(String title, ArrayList<String> ingredients, ArrayList<String> steps) {
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public String getIngredientsString() {
        String formatted = "";
        for (int i = 0; i < ingredients.size(); i++) {
            formatted += ingredients.get(i);
            if (i != ingredients.size() - 1) {
                formatted += "\n";
            }
        }
        return formatted;
    }

    public String getStepsString() {
        String formatted = "";
        for (int i = 0; i < steps.size(); i++) {
            formatted += steps.get(i);
            if (i != steps.size() - 1) {
                formatted += "\n";
            }
        }
        return formatted;
    }

    public static Recipe parseRecipe(String data) {
        String[] lines = data.split("\n");
        Recipe recipe = new Recipe();

        // Assume first line to be title
        recipe.setTitle(lines[0]);

        // From there, if a line starts with a number, it's probably an ingredient
        // If not, probably a step
        boolean inSteps = false;
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> steps = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.toLowerCase().equals("ingredients")) {
                continue;
            }
            if (line.toLowerCase().equals("instructions") || line.toLowerCase().equals("steps") || line.toLowerCase().equals("directions")) {
                inSteps = true;
                continue;
            }
            if (!inSteps && Character.isDigit(line.charAt(0))) {
                ingredients.add(line);
            } else {
                inSteps = true;
                steps.add(line);
            }
        }
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);

        return recipe;
    }
}
