package com.chesak.adam.boilermake2017recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");

        setTitle(recipe.getTitle());

        TextView titleText = (TextView) findViewById(R.id.recipe_title);
        TextView ingredientsText = (TextView) findViewById(R.id.recipe_ingredients);
        TextView dataText = (TextView) findViewById(R.id.recipe_data);

        titleText.setText(recipe.getTitle());
        ingredientsText.setText(recipe.getIngredientsString());
        dataText.setText(recipe.getStepsString());
    }
}
