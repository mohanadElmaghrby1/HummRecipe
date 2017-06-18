package apps.mohanad.com.humrecipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mohanad on 17/06/17.
 */

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(@NonNull Context context , @NonNull ArrayList<Recipe> places ) {
        super(context, 0, places);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.recipe_list_item, parent, false);
        }

        //get the recipe object
        final Recipe recipe = getItem(position);

        /*get the recipe title and display it to the list item tile */
        String title =recipe.getTitle();
        TextView titleTextView = (TextView)listItemView.findViewById(R.id.recipe_title);
        titleTextView.setText(title);

        /*get the recipe category and display it to the list item category */
        String category =recipe.getCategory();
        TextView categoryTextView = (TextView)listItemView.findViewById(R.id.recipe_category);
        categoryTextView.setText(category);

        /*get the recipe image and set display to the ui*/
        ImageView img=(ImageView)listItemView.findViewById(R.id.recipe_image);
        String imageUrl=recipe.getImageUrl();
        //load image from url and dsiplay to ui
        Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.image_placeholder).into(img);

        //get the recipe rating image and set it to new rating
        ImageView recipe_rating = (ImageView)listItemView.findViewById(R.id.recipe_rating);
        float rating=recipe.getRating();

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
//(rating>4.5&&rating<=5)
        return listItemView;
    }
}
