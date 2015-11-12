package ca.kevinzoelee.week4ice;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by darkl on 9/30/2015.
 */
public class ProvinceArrayAdapter extends ArrayAdapter<Province> {
    private Context mContext;

    private int mResourceId;

    private List<Province> mProvinces;

    public ProvinceArrayAdapter(Context context, int resourceId, List<Province> provinces){
        super(context, resourceId, provinces);

        this.mContext = context;
        this.mResourceId = resourceId;
        this.mProvinces = provinces;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(mResourceId, parent, false);
        }

        TextView lblProvince = (TextView) convertView.findViewById(R.id.lblProvince);
        lblProvince.setText(mProvinces.get(position).getName());

        TextView lblCapital = (TextView) convertView.findViewById(R.id.lblCapital);
        lblCapital.setText(mProvinces.get(position).getCapital());

        ImageView imgArms = (ImageView) convertView.findViewById(R.id.imgArms);
        imgArms.setImageResource(mProvinces.get(position).getArmsResourceId());

        return convertView;
    }
}
