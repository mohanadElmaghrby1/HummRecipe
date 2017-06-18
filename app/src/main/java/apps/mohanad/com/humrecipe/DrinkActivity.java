package apps.mohanad.com.humrecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * load a set of drinks and display to UI
 */
public class DrinkActivity extends AppCompatActivity {

    //listview of recipes
    private ListView listView ;

    //the api current page number
    private int current_page=1;

    //list of recipes objects
    private ArrayList<Recipe> recipesList= new ArrayList<>();

    //variable to flag loading process
    private boolean loadingMore=false;

    //create url for a set of recipes
    private String stringUrl="https://api2.bigoven.com/recipes?api_key=axV15293h59oU9Z853fw48CmI1H1Js&any_kw=Drinks&&rpp=5&pg=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);


        //get the listview
        listView = (ListView)findViewById(R.id.recipe_listView);

        //create a task to load and display recipes
        final RecipeAsyncTask recipeTask = new RecipeAsyncTask();
        recipeTask.execute();

        /*
        * load more recipes when  scroll down */
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //check if we reach to the last listview Item
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if((lastInScreen == totalItemCount) && !(loadingMore)){
                    //execute task
                    new RecipeAsyncTask().execute();

                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent details_intent = new Intent(getApplicationContext(),SingleRecipeItem.class);
                Recipe recipe =(Recipe) listView.getItemAtPosition(position);
                details_intent.putExtra("RecipeID",""+recipe.getResipeId());
                startActivity(details_intent);
            }
        });

    }

    /**
     * update the ui with the new recipes
     *
     */
    private void updateUi (){
        //get the first list item postion
        int currentPosition =listView.getFirstVisiblePosition();
        //append the new recipes to listview
        RecipeAdapter adapter = new RecipeAdapter(this , recipesList);
        listView.setAdapter(adapter);
        // Setting new scroll position to continue
        listView.setSelectionFromTop(currentPosition + 1, 0);
    }

    /**
     * thread to get json data and update ui
     */
    private class RecipeAsyncTask extends AsyncTask<Void , Void , Void>{

        //progess dialgo
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog before sending http request
            pDialog = new ProgressDialog(DrinkActivity.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //create runOnUiThread to update ui
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //change the flag to be true
                    loadingMore=true;
                    //load data from url
                    ArrayList<Recipe>  recipe = Utils.fetchListOfRecipeData(stringUrl+current_page);
                    ++current_page;
                    recipesList.addAll(recipe);
                }
            });


            return null;
        }


        @Override
        protected void onPostExecute(Void  recipe) {
            super.onPostExecute(recipe);
            //update ui after the data loaded
            updateUi();

            //close the progress par
            pDialog.dismiss();

            //change the flag of loading to be false
            loadingMore=false;
        }
    }

}
