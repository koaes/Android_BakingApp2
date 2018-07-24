package com.example.android.baking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.android.baking.model.Recipe;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Recipe currentRecipe;
    int recipeImage;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Recipe currentRecipe, int recipeImage) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.currentRecipe = currentRecipe;
        this.recipeImage = recipeImage;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                IngredientFragment tab1 = new IngredientFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Recipe", currentRecipe);
                bundle.putInt("Image",recipeImage);
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                StepFragment tab2 = new StepFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("Recipe", currentRecipe);
                tab2.setArguments(bundle2);
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
