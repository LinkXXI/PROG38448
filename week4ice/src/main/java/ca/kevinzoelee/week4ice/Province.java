package ca.kevinzoelee.week4ice;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;

/**
 * Created by darkl on 9/30/2015.
 */
public class Province {
    private String mName;
    private String mCapital;
    private int mArmsResourceId;

    public Province(String name, String capital, int armsResourceId) {
        mName = name;
        mCapital = capital;
        mArmsResourceId = armsResourceId;
    }

    public String getName() {
        return mName;
    }


    public String getCapital() {
        return mCapital;
    }

   public int getArmsResourceId(){
       return mArmsResourceId;
   }

    public static ArrayList<Province> getProvinces(Context context) {

        ArrayList<Province> provinces = new ArrayList<>();

        String[] provinceNames = context.getResources().getStringArray(R.array.provinces);
        String[] provinceCapitals = context.getResources().getStringArray(R.array.capitals);

        TypedArray armsList = context.getResources().obtainTypedArray(R.array.arms);


        for (int i = 0; i < provinceNames.length; i++) {
            provinces.add(i, new Province(provinceNames[i], provinceCapitals[i], armsList.getResourceId(i, -1)));
        }

        return provinces;
    }
}
