package ca.kevinzoelee.kevin_lee_a1;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculator extends Activity implements AdapterView.OnItemSelectedListener, TextWatcher, CompoundButton.OnCheckedChangeListener{

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

    //Tax rate constant
    private static final double TAX_RATE = 0.13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        //set up tip spinner
        spinTipAdapter = ArrayAdapter.createFromResource(this, R.array.tips, android.R.layout.simple_spinner_item);
        spinTipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTip = (Spinner) findViewById(R.id.spinTip);
        spinTip.setAdapter(spinTipAdapter);
        spinTip.setOnItemSelectedListener(this);

        //set up people spinner
        spinPeopleAdapter = ArrayAdapter.createFromResource(this, R.array.numPeople, android.R.layout.simple_spinner_item);
        spinPeopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPeople = (Spinner) findViewById(R.id.spinPeople);
        spinPeople.setAdapter(spinPeopleAdapter);
        spinPeople.setOnItemSelectedListener(this);

        //add views to class for use later
        txtOtherTip = (EditText) findViewById(R.id.txtOtherTip);
        txtOtherTip.addTextChangedListener(this);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtAmount.addTextChangedListener(this);

        chkHst = (CheckBox) findViewById(R.id.chkHst);
        chkHst.setOnCheckedChangeListener(this);

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
        if(txtAmount.getText().length() == 0){
            Toast.makeText(this, "You didn't enter an amount!", Toast.LENGTH_LONG).show();
            return;
        }

        if(spinTip.getSelectedItemPosition() == spinTip.getCount() - 1){
            if(txtOtherTip.getText().length() == 0){
                Toast.makeText(this, "You didn't enter a percentage to use!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        //NOTE: txtAmount is configured as a number input,
        //therefore we can expect a number to be it's value.
        double amount = Double.parseDouble(txtAmount.getText().toString());

        double tax = 0;
        if(chkHst.isChecked()){
            tax = amount * TAX_RATE;
            amount += tax;
        }

        double tipPercent = 1; //Init to 1 so that multiplying later wouldn't cause the amounts to be 0 if there is a problem.
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
                //NOTE: this input is also a number (this one is a Signed Int, no decimals)
                tipPercent = Integer.parseInt(txtOtherTip.getText().toString());
                tipPercent = tipPercent / 100;
                break;
        }
        //why parse when you know it's off by 1!
        //This would be changed if I knew that the spinner was to be set up different.
        int numPeople = spinPeople.getSelectedItemPosition() + 1;

        double tip = amount * tipPercent;
        lblOutTip.setText(String.format(tipString, tip));

        String outTotalString = String.format(totalString, amount + tip);
        if(tax > 0){
            outTotalString += String.format(hstString, tax);
        }
        lblOutTotal.setText(outTotalString);

        if(numPeople > 1){
            double perPerson = amount / numPeople;
            lblOutPerPerson.setText(String.format(perPersonString, perPerson));
        }else if(lblOutPerPerson.getText().length() != 0){
            lblOutPerPerson.setText("");
        }
    }

    public void resetCalculator(View view){
        txtAmount.setText("");
        spinTip.setSelection(0);
        spinPeople.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Check if this is the correct adapter)
        if(parent.getAdapter().equals(spinTipAdapter)){
            //Check if the element selected is the last one (will be the "other" option
            if(position == parent.getCount() -1){
                txtOtherTip.setVisibility(View.VISIBLE);
                txtOtherTip.setText("");
            }else{
                txtOtherTip.setVisibility(View.INVISIBLE);
                txtOtherTip.setText("");
            }
        }
        if(parent.getAdapter().equals(spinPeopleAdapter)){
            resetTextFields();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //No implementation required
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //No implementation required
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //No implementation required
    }

    @Override
    public void afterTextChanged(Editable s) {
        resetTextFields();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       resetTextFields();
    }

    private void resetTextFields(){
        if(lblOutTotal.getText().length() != 0){
            lblOutPerPerson.setText("");
            lblOutTotal.setText("");
            lblOutTip.setText("");
        }
    }
}
