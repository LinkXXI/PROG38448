package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayerInfo extends Activity {

    private EditText txtPlayerName;
    private RadioGroup rdoGrpPosition;
    private EditText txtNumGoals;

    private ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        txtPlayerName = (EditText) findViewById(R.id.txtPlayerName);
        rdoGrpPosition = (RadioGroup) findViewById(R.id.rdoGrpPosition);
        txtNumGoals = (EditText) findViewById(R.id.txtNumGoals);

        players = new ArrayList<>();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("players", players);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        players = savedInstanceState.getParcelableArrayList("players");
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void save(View view){
        if(txtPlayerName.getText().length() == 0){
            Toast.makeText(this, "You must enter a player name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txtNumGoals.getText().length() == 0){
            Toast.makeText(this, "You must enter a number of goals!", Toast.LENGTH_SHORT).show();
            return;
        }

        String playerName = txtPlayerName.getText().toString();

        Player.Position position;
        switch (rdoGrpPosition.getCheckedRadioButtonId()){
            case R.id.rdoGoalie:
                position = Player.Position.GOALIE;
                break;
            case R.id.rdoDefence:
                position = Player.Position.DEFENSE;
                break;
            case R.id.rdoForward:
                position = Player.Position.FORWARD;
                break;
            default:
                //Default to Goalie to avoid compiler warning and runtime errors
                position = Player.Position.GOALIE;
                break;
        }

        int numGoals = Integer.parseInt(txtNumGoals.getText().toString());

        players.add(new Player(playerName, position, numGoals));
        Toast.makeText(this, playerName + " has been saved!", Toast.LENGTH_LONG).show();
        clearFields();
    }

    public void viewAll(View view){
        Intent intent = new Intent(getApplicationContext(), PlayerListing.class);
        intent.putExtra("players", players);
        startActivity(intent);
        clearFields();
    }

    private void clearFields(){
        txtPlayerName.setText("");
        rdoGrpPosition.check(R.id.rdoGoalie);
        txtNumGoals.setText("");
    }
}
