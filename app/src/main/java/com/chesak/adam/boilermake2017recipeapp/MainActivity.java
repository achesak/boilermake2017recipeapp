package com.chesak.adam.boilermake2017recipeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialcamera.MaterialCamera;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Camera request code
    private final static int CAMERA_RQ = 2017;
    public static RecipeList recipeList = null;
    public static IO io = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (io == null) {
            io = new IO();
            recipeList = io.readData(MainActivity.this);
        }

        // Make sure the data directory exists
        File saveDir = new File(Environment.getExternalStorageDirectory(), "BoilerMake2017RecipeApp");
        saveDir.mkdirs();

        final Button resumeButton = (Button) findViewById(R.id.main_resume);
        if (recipeList.size() == 0) {
            resumeButton.setText(getString(R.string.main_resume, "No previous recipe"));
        } else {
            resumeButton.setText(getString(R.string.main_resume, recipeList.getMostRecent().getTitle()));
        }

        // Take a new picture
        Button takePictureButton = (Button) findViewById(R.id.main_take_picture);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialCamera(MainActivity.this)
                        .stillShot()
                        .start(CAMERA_RQ);
            }
        });

        // Resume last recipe
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe lastRecipe = MainActivity.recipeList.getMostRecent();
                Intent resumeIntent = new Intent(MainActivity.this, RecipeActivity.class);
                resumeIntent.putExtra("recipe", lastRecipe);
                startActivity(resumeIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Received recording or error from MaterialCamera
        if (requestCode == CAMERA_RQ) {

            if (resultCode == RESULT_OK) {

                // Load the bitmap
                final File file = new File(data.getData().getPath());
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                // OCR: get the text of the image
                String value = OCR.getText(MainActivity.this, bitmap);

                // Build a new recipe
                //Recipe recipe = Recipe.parseRecipe(value);
                Recipe recipe = Demo.getDemoRecipe();
                MainActivity.recipeList.add(recipe);
                MainActivity.io.saveData(MainActivity.this);

                // Go to the recipe screen
                Intent dataIntent = new Intent(MainActivity.this, RecipeActivity.class);
                dataIntent.putExtra("recipe", recipe);
                startActivity(dataIntent);

            } else if (data != null) {

                // Error here!
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
