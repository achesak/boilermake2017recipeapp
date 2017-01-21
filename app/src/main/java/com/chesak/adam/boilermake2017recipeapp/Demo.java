package com.chesak.adam.boilermake2017recipeapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by adam on 1/21/17.
 */

final public class Demo {

    public static Recipe getDemoRecipe() {

        Recipe recipe = new Recipe();
        recipe.setTitle("Chocolate Chip Cookies");

        String[] ingredients = {"1 stick unsalted butter", "3/4 cup dark brown sugar", "3/4 cup sugar", "2 large eggs",
                                "1 t vanilla extract", "1 bag semisweet chocolate chips", "2 1/4 cups flour",
                                "3/4 t baking soda", "1 t fine salt"};
        String[] steps = {
                "Evenly position 2 racks in the middle of the oven and preheat to 375 degrees F.",
                "Line 2 baking sheets with parchment paper or silicone sheets.",
                "Put the butter in a microwave safe bowl, cover and microwave on medium power until melted. Cool slightly.",
                "Whisk the sugars, eggs, butter and vanilla in a large bowl until smooth.",
                "Whisk the flour, baking soda and salt in another bowl.",
                "Stir the dry ingredients into the wet ingredients with a wooden spoon; take care not to over mix.",
                "Stir in the chocolate chips or chunks.",
                "Scoop heaping tablespoons of the dough onto the prepared pans. Wet hands slightly and roll the dough into balls.",
                "Space the cookies about 2-inches apart on the pans.",
                "Bake, until golden, but still soft in the center, 12 to 16 minutes, depending on how chewy or crunchy you like your cookies.",
                "Transfer hot cookies with a spatula to a rack to cool."};

        recipe.setIngredients(new ArrayList<String>(Arrays.asList(ingredients)));
        recipe.setSteps(new ArrayList<String>(Arrays.asList(steps)));

        return recipe;
    }

}
