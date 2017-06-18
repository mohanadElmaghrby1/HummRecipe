package apps.mohanad.com.humrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    /**
     * open the Drinks intetn to load
     * a list of drinks
     * @param view
     */
    public void openDrinkIntent(View view) {
        Intent intent = new Intent(this,DrinkActivity.class);
        startActivity(intent);
    }

    public void openRecipesIntent(View view) {
        Intent intent = new Intent(this,RecipesActivity.class);
        startActivity(intent);
    }

    public void openBreakfastIntent(View view) {
        Intent intent = new Intent(this,BreakfastActivity.class);
        startActivity(intent);
    }

    public void openBreadIntent(View view) {
        Intent intent = new Intent(this,BreadActivity.class);
        startActivity(intent);
    }
}
