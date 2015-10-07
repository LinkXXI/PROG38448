package ca.kevinzoelee.week4ice;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkl on 9/30/2015.
 */
public class ProvincialArms {
    private int mResourceId;

    public ProvincialArms(int resourceId) {
        mResourceId = resourceId;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public static List<ProvincialArms> getProvicialArms(Context context){
        List<ProvincialArms> armsList = new ArrayList<>();

        TypedArray arms = context.getResources().obtainTypedArray(R.array.arms);

        for(int i=0; i < armsList.size(); i++){
            int resourceId = arms.getResourceId(i, -1);

            armsList.set(i, new ProvincialArms(resourceId));
        }
        arms.recycle();

        return armsList;
    }
}
