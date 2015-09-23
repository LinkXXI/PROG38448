package ca.kevinzoelee.wilhelmservice;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class WilhelmSettings extends Activity implements SensorEventListener{

    private SensorManager mSensorManager;
    private static final String TAG = "Wilhelm Main Activity";
    private Sensor accel;

    private float change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilhelm_settings);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        List<Sensor> accelerometors = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        accel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Log.d(TAG, "Lists initialized.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wilhelm_settings, menu);
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
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
       // mSensorManager.registerListener(this, accel, SensorManager);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        //Log.d(TAG, "Sensor's value is " + value);

        this.change += value;

        if(change >= 100){
            Log.d(TAG, "Changed by 100 (ish)");
            change = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
