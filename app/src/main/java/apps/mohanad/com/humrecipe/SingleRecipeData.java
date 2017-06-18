package apps.mohanad.com.humrecipe;

/**
 * Created by mohanad on 17/06/17.
 * model class to represent a single recipe full data
 */

public class SingleRecipeData {

    //the recipe id
    private long mResipeId;

    //the recipe title
    private String mTitle;

    //the recipe category
    private String mCategory;

    //the recipe subcategory
    private String subCategory;

    //the recipe description
    private String mDescription;

    //the recipe rating
    private float mRating;

    //the recipe image url
    private String imageUrl;

    //the recipe Instructions
    private String mInstructions;

    //the recipe Ingredients
    private String mIngredients;

    /**
     *
     * @param mResipeId
     * @param mTitle
     * @param mCategory
     * @param subCategory
     * @param mDescription
     * @param mRating
     * @param imageUrl
     * @param mInstructions
     * @param mIngredients
     */
    public SingleRecipeData(long mResipeId, String mTitle, String mCategory, String subCategory, String mDescription, float mRating,
                            String imageUrl, String mInstructions, String mIngredients) {
        this.mResipeId = mResipeId;
        this.mTitle = mTitle;
        this.mCategory = mCategory;
        this.subCategory = subCategory;
        this.mDescription = mDescription;
        this.mRating = mRating;
        this.imageUrl = imageUrl;
        this.mInstructions = mInstructions;
        this.mIngredients = mIngredients;
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

    public String getSubCategory() {
        return subCategory;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public String getIngredients() {
        return mIngredients;
    }

    @Override
    public String toString() {
        return "SingleRecipeData{" +
                "ResipeId=" + mResipeId +
                ", Title='" + mTitle + '\'' +
                ", Category='" + mCategory + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", Description='" + mDescription + '\'' +
                ", Rating=" + mRating +
                ", imageUrl='" + imageUrl + '\'' +
                ", Instructions='" + mInstructions + '\'' +
                ", Ingredients='" + mIngredients + '\'' +
                '}';
    }
}
