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

    public static Sandwich parseSandwichJson(String json, Context context) {

        Sandwich sandwich = new Sandwich();
        if(json!=null) {


            try {
                JSONObject sandwichJsonObj = new JSONObject(json);
                JSONObject nameObj = sandwichJsonObj.getJSONObject("name");
                String mainNameObj = nameObj.getString("mainName");
                JSONArray alsoKnownAsArray = nameObj.getJSONArray("alsoKnownAs");
                String placeOfOriginObj = sandwichJsonObj.getString("placeOfOrigin");
                String descriptionObj = sandwichJsonObj.getString("description");
                String imageObj = sandwichJsonObj.getString("image");
                JSONArray ingredientsArray = sandwichJsonObj.getJSONArray("ingredients");


                List<String> alsoKnownlist = new ArrayList<String>();
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    String alsoKnownObj = alsoKnownAsArray.getString(i);
                    alsoKnownlist.add(alsoKnownObj);
                }


                List<String> ingredientslist = new ArrayList<String>();
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    String ingredientObj = ingredientsArray.getString(i);
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
