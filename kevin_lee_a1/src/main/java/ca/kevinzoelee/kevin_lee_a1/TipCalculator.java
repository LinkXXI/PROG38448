package ca.kevinzoelee.kevin_lee_a1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TipCalculator extends Activity implements AdapterView.OnItemSelectedListener{

    private ArrayAdapter<CharSequence> spinTipAdapter;
    private ArrayAdapter<CharSequence> spinPeopleAdapter;

    private Spinner spinTip;
    private Spinner spinPeople;

    private EditText txtOtherTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        spinTipAdapter = ArrayAdapter.createFromResource(this, R.array.tips, android.R.layout.simple_spinner_item);
        spinTipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTip = (Spinner) findViewById(R.id.spinTip);
        spinTip.setAdapter(spinTipAdapter);

        spinPeopleAdapter = ArrayAdapter.createFromResource(this, R.array.numPeople, android.R.layout.simple_spinner_item);
        spinPeopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPeople = (Spinner) findViewById(R.id.spinPeople);
        spinPeople.setAdapter(spinPeopleAdapter);

        txtOtherTip = (EditText) findViewById(R.id.txtOtherTip);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.equals(spinTipAdapter)){
            TextView selectedItem = (TextView) view;
            if(position == parent.getCount() -1){
                txtOtherTip.setVisibility(View.VISIBLE);
            }else{
                txtOtherTip.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
