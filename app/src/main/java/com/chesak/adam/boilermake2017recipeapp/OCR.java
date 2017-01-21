package com.chesak.adam.boilermake2017recipeapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

/**
 * Created by adam on 1/21/17.
 */

public class OCR {

    public static String getText(Context context, Bitmap bitmap) {

        // OCR: get the text of the image
        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
        Frame imageFrame = new Frame.Builder()
                .setBitmap(bitmap)
                .build();

        SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);
        String value = "";
        for (int i = 0; i < textBlocks.size(); i++) {
            TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
            value += textBlock.getValue() + "\n";
        }

        return value;
    }
}
