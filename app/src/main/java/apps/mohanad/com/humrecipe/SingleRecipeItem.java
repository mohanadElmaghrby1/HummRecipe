package apps.mohanad.com.humrecipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleRecipeItem extends AppCompatActivity {

    private String RECIPE_URL="https://api2.bigoven.com/recipe/";
    private String API_KEY="?api_key=axV15293h59oU9Z853fw48CmI1H1Js";

    //the textview title of recipe
    private TextView title;

    //the textview recipe category
    private TextView category;

    //the textview recipe category
    private TextView description;

    //the textview recipe category
    private TextView Ingredients;

    //the textview recipe category
    private TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe_item);
        String recipId= getIntent().getStringExtra("RecipeID");
        SingleRecipeData recipeData = Utils.fetchRecipeData(RECIPE_URL+recipId+API_KEY);


         /*get the recipe image and set display to the ui*/
        ImageView img=(ImageView)findViewById(R.id.recipe_image);
        String imageUrl=recipeData.getImageUrl();
        //load image from url and dsiplay to ui
        Picasso.with(getApplicationContext()).load(imageUrl).placeholder(R.drawable.image_placeholder).into(img);

        //get the title textView
        title=(TextView)findViewById(R.id.recipe_title);
        title.setText(recipeData.getTitle());

        //get the category textView
        category=(TextView)findViewById(R.id.recipe_category);
        category.setText(recipeData.getCategory());

        //get the description textView
        description=(TextView)findViewById(R.id.recipe_subcategory);
        description.setText(recipeData.getSubCategory());

        //get the title textView
        Ingredients=(TextView)findViewById(R.id.recipe_ingredients);
        Ingredients.setText(recipeData.getIngredients());

        //get the title textView
        instruction=(TextView)findViewById(R.id.recipe_instruction);
        instruction.setText(recipeData.getInstructions());

        //get the recipe rating image and set it to new rating
        ImageView recipe_rating = (ImageView)findViewById(R.id.recipe_rating);
        float rating=recipeData.getRating();

        //check the rating and add the proper image
        if (rating>=0&&rating<=.5){
            recipe_rating.setImageResource(R.drawable.ratin__5);
        }
        else if (rating>.5&&rating<=1){
            recipe_rating.setImageResource(R.drawable.ratin_1);
        }
        else if (rating>1&&rating<=1.5){
            recipe_rating.setImageResource(R.drawable.ratin_1_5);
        }
        else if (rating>1.5&&rating<=2){
            recipe_rating.setImageResource(R.drawable.ratin_2);
        }
        else if (rating>2&&rating<=2.5){
            recipe_rating.setImageResource(R.drawable.ratin_2_5);
        }
        else if (rating>2.5&&rating<=3){
            recipe_rating.setImageResource(R.drawable.ratin_3);
        }
        else if (rating>3&&rating<=4.5){
            recipe_rating.setImageResource(R.drawable.ratin_3_5);
        }
        else if (rating>3.5&&rating<=4){
            recipe_rating.setImageResource(R.drawable.ratin_4);
        }
        else if (rating>4&&rating<4.5){
            recipe_rating.setImageResource(R.drawable.ratin_4_5);
        }
        else{
            recipe_rating.setImageResource(R.drawable.rating_5);
        }


    }
}
