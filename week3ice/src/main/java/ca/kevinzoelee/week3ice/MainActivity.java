package ca.kevinzoelee.week3ice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeOrder(View view){
        String output = "One ";

        RadioGroup size = (RadioGroup) findViewById(R.id.rdioGrpSize);

        switch(size.getCheckedRadioButtonId()){
            case R.id.rdioSmall:
                output += "small ";
                break;
            case R.id.rdioMedium:
                output += "medium ";
                break;
            case R.id.rdioLarge:
                output += "large ";
                break;
        }

        output += "for ";

        Switch pickupOrDelivery = (Switch) findViewById(R.id.swPickDel);
        output += pickupOrDelivery.isChecked() ? "delivery ": "pickup ";

        output +="with ";

        CheckBox cheese = (CheckBox) findViewById(R.id.chkCheese);
        CheckBox peperoni = (CheckBox) findViewById(R.id.chkPeperoni);
        CheckBox greenPepper = (CheckBox) findViewById(R.id.chkGreenPepper);

        ArrayList<String> toppings = new ArrayList<>();
        if(cheese.isChecked()){
            toppings.add("cheese");
        }
        if(peperoni.isChecked()){
            toppings.add("peperoni");
        }
        if(greenPepper.isChecked()){
            toppings.add("green pepper");
        }

        if(toppings.size() == 0){
            output += "no toppings";
        }
        for(int i=0; i < toppings.size(); i++){
            if(i == toppings.size() -1){
                output += "and ";
            }
            output += toppings.get(i) + " ";
        }

        TextView outputLabel = (TextView) findViewById(R.id.lblOutput);
        outputLabel.setText(output);
    }
}
