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
    private final static int CAMERA_RQ = 6969;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Make sure the data directory exists
        File saveDir = new File(Environment.getExternalStorageDirectory(), "BoilerMake2017RecipeApp");
        saveDir.mkdirs();

        Button resumeButton = (Button) findViewById(R.id.main_resume);
        resumeButton.setText(getString(R.string.main_resume, "No previous recipe"));

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
                final File file = new File(data.getData().getPath());
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();
                Frame imageFrame = new Frame.Builder()
                        .setBitmap(bitmap)
                        .build();

                SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);

                String value = "";
                for (int i = 0; i < textBlocks.size(); i++) {
                    TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
                    value += textBlock.getValue() + "\n";
                }

                Intent dataIntent = new Intent(MainActivity.this, RecipeActivity.class);
                dataIntent.putExtra("data", value);
                startActivity(dataIntent);
            } else if(data != null) {
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
