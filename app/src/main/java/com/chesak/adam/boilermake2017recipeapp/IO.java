package com.chesak.adam.boilermake2017recipeapp;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Saves and reads application data.
 *
 * @author Adam Chesak, achesak@yahoo.com
 */
final public class IO {


    final private static String DATA_FILE_NAME = "recipe_data";


    /**
     * Reads the data from storage
     * @param context context
     * @return data
     */
    public RecipeList readData(Context context) {
        FileInputStream fis = null;
        ObjectInputStream is = null;
        RecipeList defaultList = new RecipeList();
        try {
            fis = context.openFileInput(DATA_FILE_NAME);
            is = new ObjectInputStream(fis);
            RecipeList data = (RecipeList) is.readObject();
            is.close();
            fis.close();
            return data;
        } catch (FileNotFoundException e) {
            return defaultList;
        } catch (IOException e) {
            return defaultList;
        } catch (ClassNotFoundException e) {
            return defaultList;
        } finally {
            if (fis != null && is != null) {
                try {
                    fis.close();
                    is.close();
                } catch (Exception e) {
                    // Nothing done here
                }
            }
        }
    }


    /**
     * Writes the data to storage
     * @param context context
     */
    public void saveData(Context context) {
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            fos = context.openFileOutput(DATA_FILE_NAME, Context.MODE_PRIVATE);
            os = new ObjectOutputStream(fos);
            os.writeObject(MainActivity.recipeList);
        } catch (IOException e) {
            // Nothing to do here
        } finally {
            try {
                os.close();
                fos.close();
            } catch (IOException e) {
                //Nothing to do here
            }
        }
    }

}
