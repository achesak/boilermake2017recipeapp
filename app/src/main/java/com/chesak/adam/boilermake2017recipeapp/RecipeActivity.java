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
        String data = intent.getStringExtra("data");

        TextView textView = (TextView) findViewById(R.id.recipe_data);
        textView.setText(data);
    }
}
