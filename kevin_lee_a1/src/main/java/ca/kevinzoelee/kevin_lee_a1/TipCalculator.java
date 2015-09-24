package ca.kevinzoelee.kevin_lee_a1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TipCalculator extends Activity implements AdapterView.OnItemSelectedListener{

    //ArrayAdapters
    private ArrayAdapter<CharSequence> spinTipAdapter;
    private ArrayAdapter<CharSequence> spinPeopleAdapter;

    //UI Components
    //Spinners
    private Spinner spinTip;
    private Spinner spinPeople;

    //Text fields
    private EditText txtAmount;
    private EditText txtOtherTip;

    //Check Boxes
    private CheckBox chkHst;

    //Labels
    private TextView lblOutTip;
    private TextView lblOutTotal;
    private TextView lblOutPerPerson;

    //Pre-built strings for formatting.
    private final String tipString = "Tip is: $%.2f";
    private final String totalString = "Total is: $%.2f";
    private final String hstString = " (%.2f hst)";
    private final String perPersonString = "Price per person: $%.2f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        //set up tip spinner
        spinTipAdapter = ArrayAdapter.createFromResource(this, R.array.tips, android.R.layout.simple_spinner_item);
        spinTipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTip = (Spinner) findViewById(R.id.spinTip);
        spinTip.setAdapter(spinTipAdapter);

        //set up people spinner
        spinPeopleAdapter = ArrayAdapter.createFromResource(this, R.array.numPeople, android.R.layout.simple_spinner_item);
        spinPeopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPeople = (Spinner) findViewById(R.id.spinPeople);
        spinPeople.setAdapter(spinPeopleAdapter);

        //add views to class for use later
        txtOtherTip = (EditText) findViewById(R.id.txtOtherTip);
        txtAmount = (EditText) findViewById(R.id.txtAmount);

        chkHst = (CheckBox) findViewById(R.id.chkHst);

        lblOutTip = (TextView) findViewById(R.id.lblOutTip);
        lblOutTotal = (TextView) findViewById(R.id.lblOutTotal);
        lblOutPerPerson = (TextView) findViewById(R.id.lblOutPerPerson);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_calculator, menu);
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

    public void calculateTip(View view){
        //NOTE: txtAmount is configured as a number input,
        //therefore we can expect a number to be it's value.
        double amount = Double.parseDouble(txtAmount.getText().toString());
        double tipPercent;
        switch (spinTip.getSelectedItemPosition()){
            case 0:
                tipPercent = 0.10;
                break;
            case 1:
                tipPercent = 0.15;
                break;
            case 2:
                tipPercent = 0.20;
                break;
            case 3:
                //NOTE: this input is also a number
                tipPercent = Integer.parseInt(txtOtherTip.getText().toString()) / 100;
                break;
        }
    }

    public void resetCalculator(View view){

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Check if this is the correct adapter)
        if(parent.getAdapter().equals(spinTipAdapter)){
            //Check if the element selected is the last one (will be the "other" option
            if(position == parent.getCount() -1){
                txtOtherTip.setVisibility(View.VISIBLE);
            }else{
                txtOtherTip.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //No implementation required
    }
}
