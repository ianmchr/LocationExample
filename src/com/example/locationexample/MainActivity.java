package com.example.locationexample;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView tv1, tv2, tv3;;
	LocationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv1 = (TextView) findViewById(R.id.textViewLongitude);
		tv2 = (TextView) findViewById(R.id.textViewLatitude);
		tv3 = (TextView) findViewById(R.id.textViewSpeed);
		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void start(View v) {

		try {
			LocationListener listener = new LocationListener() {
				@Override
				public void onLocationChanged(Location location) {
					double lat = location.getLatitude();
					double lon = location.getLongitude();
					double speed = location.getSpeed();
					// do something with this values
					tv1.setText("Longi : "+lon);
					tv2.setText("Lati  : "+lat);
					tv3.setText("Speed : "+speed);
				}

				@Override
				public void onStatusChanged(String provider, int status,
						Bundle extras) {
					Log.d("DATA", "STATUS CHANGED " + provider);
				}

				@Override
				public void onProviderEnabled(String provider) {
					Log.d("DATA", "PROVIDER ENABLED " + provider);
				}

				@Override
				public void onProviderDisabled(String provider) {
					Log.d("DATA", "PROVIDER DISABLED " + provider);
				}
			};
			String provider = LocationManager.GPS_PROVIDER; // can be changed to network provider but this is more accurate
			
			manager.requestLocationUpdates(provider, 3000, 2, listener); // 3000 is the interval in milliseconds
			                                                             // 2 is the distance in meters 
			                                                             // don't make the distance and the time too small as that will use up the users battery
		} catch (SecurityException e) {
			Log.e("DATA", "Could Not Fetch The Location");
		}

		// manager.removeUpdates();

	}

	public void start_service (View v) {
		
		Intent service = new Intent(this, LocationMyService.class);
		startService(service);

	}
	
   public void check (View v) {
		
	String data = Utility.readFromFile(this, "data.txt");
	Toast.makeText(this, data, Toast.LENGTH_LONG).show();

	}
}
