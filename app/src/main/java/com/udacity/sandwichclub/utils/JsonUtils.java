package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN = "alsoKnownAs";
    public static final String KEY_ORIGIN_PLACE = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";



    public static Sandwich parseSandwichJson(String json, Context context) {

        Sandwich sandwich = new Sandwich();
        if(json!=null) {


            try {
                JSONObject sandwichJsonObj = new JSONObject(json);
                JSONObject nameObj = sandwichJsonObj.getJSONObject(KEY_NAME);
                String mainNameObj = nameObj.optString(KEY_MAIN_NAME);
                JSONArray alsoKnownAsArray = nameObj.optJSONArray(KEY_ALSO_KNOWN);
                String placeOfOriginObj = sandwichJsonObj.optString(KEY_ORIGIN_PLACE);
                String descriptionObj = sandwichJsonObj.optString(KEY_DESCRIPTION);
                String imageObj = sandwichJsonObj.optString(KEY_IMAGE);
                JSONArray ingredientsArray = sandwichJsonObj.optJSONArray(KEY_INGREDIENTS);


                List<String> alsoKnownlist = new ArrayList<String>();
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    String alsoKnownObj = alsoKnownAsArray.optString(i);
                    alsoKnownlist.add(alsoKnownObj);
                }


                List<String> ingredientslist = new ArrayList<String>();
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    String ingredientObj = ingredientsArray.optString(i);
                    ingredientslist.add(ingredientObj);
                }

                sandwich.setMainName(mainNameObj);
                sandwich.setAlsoKnownAs(alsoKnownlist);
                sandwich.setImage(imageObj);
                sandwich.setDescription(descriptionObj);
                sandwich.setPlaceOfOrigin(placeOfOriginObj);
                sandwich.setIngredients(ingredientslist);


            } catch (final JSONException e) {

                Log.e("json", "Json parsing error: " + e.getMessage());
            }

        } else {
            Toast.makeText(context,
                    "Couldn't get json from server. Check LogCat for possible errors!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        return sandwich;
    }
}
