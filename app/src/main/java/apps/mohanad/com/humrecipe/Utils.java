package apps.mohanad.com.humrecipe;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/*** Utility class with methods to help perform the HTTP request and
 * parse the response.
 */
public final class Utils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * Query the url and return an {@link Recipe} object to represent a list of recipes.
     */
    public static ArrayList<Recipe> fetchListOfRecipeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Recipe} object
        ArrayList<Recipe> earthquake = extractFeatureFromJson(jsonResponse);

        // Return the {@link Recipe}
        return earthquake;
    }

    /**
     * Query the url and return an {@link Recipe} object to represent a list of recipes.
     */
    public static SingleRecipeData fetchRecipeData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Recipe} object
        SingleRecipeData singleRecipeData = extractRecipeDetaisFromJson(jsonResponse);
        return singleRecipeData;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the recipes JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link Recipe} object by parsing out information
     * about the a list of recipes from the input recipeJSON string.
     */
    private static ArrayList<Recipe> extractFeatureFromJson(String recipeJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJSON)) {
            return null;
        }

        try {
            //the result list of recipes
            ArrayList<Recipe> resultList = new ArrayList<>();
            //get the base json
            JSONObject baseJsonResponse = new JSONObject(recipeJSON);
            // get the json arrays of recipes
            JSONArray resultsArray = baseJsonResponse.getJSONArray("Results");
            //iterate throw recipes and add to lest
            for (int i = 0; i < resultsArray.length(); ++i) {
                //get the ith json recipes
                JSONObject recipe = resultsArray.getJSONObject(i);
                //extract the recipes data
                int recipId = recipe.getInt("RecipeID");
                String title = recipe.getString("Title");
                String category = recipe.getString("Category");
                String imageUrl = recipe.getString("PhotoUrl");
                double rating = recipe.getDouble("StarRating");

                //create a new recipe object and add to the list
                resultList.add(new Recipe(recipId, title, category, imageUrl, (float) rating));
            }

            //return the list
            return resultList;

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the Recipe JSON results", e);
        }

        return null;
    }

    /**
     * Return an {@link Recipe} object by parsing out information
     * about the recipe from recipeJSON string.
     */
    private static SingleRecipeData extractRecipeDetaisFromJson(String recipeJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJSON)) {
            return null;
        }

        try {
            //get the base json
            JSONObject recipe = new JSONObject(recipeJSON);
            //extract the recipes data
            int recipId = recipe.getInt("RecipeID");
            String title = recipe.getString("Title");
            String category = recipe.getString("Category");
            String subCategory = recipe.getString("Subcategory");
            String imageUrl = recipe.getString("PhotoUrl");
            String description = recipe.getString("Description");
            Log.e("desc",description);
            String instruction = recipe.getString("Instructions");
            double rating = recipe.getDouble("StarRating");

            String ingredients = "";
            //get the Ingredients json array
            JSONArray ingredientsArray = recipe.getJSONArray("Ingredients");
            //iterate and extract data
            for (int i = 0 ; i<ingredientsArray.length() ; ++i){
                //get the ith  jsonObject index
                JSONObject ingredientsObject = ingredientsArray.getJSONObject(i);

                //extract ingredients data
                //get the ingredients name
                String name =ingredientsObject.getString("Name");
                //get the ingredients quantity
                double quantity = ingredientsObject.getDouble("Quantity");
                //get the quantity unit
                String unit = ingredientsObject.getString("Unit");
                //construct the ingredients string
                ingredients+=quantity+" "+unit + " "+name+"\n\n";


            }

            //remove null word from ingredients
//            ingredients.replaceAll("null","");

            //create a new SingleRecipeData object and add to the list
            return new SingleRecipeData(recipId,title,category,subCategory,description,
                    (float)rating,imageUrl,instruction,ingredients);


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the Recipe JSON results", e);
        }

        return null;
    }
}
