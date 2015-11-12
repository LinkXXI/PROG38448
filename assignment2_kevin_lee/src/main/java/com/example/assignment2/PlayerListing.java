package com.example.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class PlayerListing extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_listing);

        ListView lstPlayers = (ListView) findViewById(R.id.lstPlayers);
        PlayerArrayAdapter adapter = new PlayerArrayAdapter(this, R.layout.player_list_item, (List) getIntent().getParcelableArrayListExtra("players"));
        lstPlayers.setAdapter(adapter);
    }
}
