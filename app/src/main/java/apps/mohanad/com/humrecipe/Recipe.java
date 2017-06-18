package apps.mohanad.com.humrecipe;

/**
 * Created by mohanad on 16/06/17.
 * model class for recipe
 */

public class Recipe {

    //the recipe title
    private String mTitle;

    //the recipe category
    private String mCategory;

    //the recipe rating
    private float mRating;

    //the recipe id
    private long mResipeId;

    //the recipe image url
    private String imageUrl;


    /**
     *
     * @param recipId
     * @param mTitle
     * @param mCategory
     * @param imageUrl
     * @param rating
     */
    public Recipe(int recipId ,String mTitle , String mCategory , String imageUrl , float rating ){
        this.mResipeId=recipId;
        this.mTitle = mTitle;
        this.mCategory = mCategory;
        this.imageUrl=imageUrl;
        this.mRating=rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCategory() {
        return mCategory;
    }


    public float getRating() {
        return mRating;
    }

    public long getResipeId() {
        return mResipeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
