package com.example.assignment2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kevin on 11/5/2015.
 *
 * Array adapter for player
 */
public class PlayerArrayAdapter extends ArrayAdapter<Player> {

    private Context context;
    private int resourceId;
    private List<Player> players;

    public PlayerArrayAdapter(Context context, int resource, List<Player> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.players = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        Resources res = context.getResources();

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        if(convertView == null){
            convertView = inflater.inflate(resourceId, parent, false);
        }

        TextView lblPlayerName = (TextView) convertView.findViewById(R.id.lblPlayerName);
        lblPlayerName.setText(players.get(position).getPlayerName());

        TextView lblPosition = (TextView) convertView.findViewById(R.id.lblPosition);
        lblPosition.setText(players.get(position).getPosition().getFriendlyName());

        TextView lblGoals = (TextView) convertView.findViewById(R.id.lblGoals);
        //NOTE: Apparently, concatenating in setText is bad. Resources should be used instead.
        lblGoals.setText(res.getString(R.string.goals_placeholder, players.get(position).getNumGoals()));

        return  convertView;
    }
}
