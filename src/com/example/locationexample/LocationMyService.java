package com.example.locationexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationMyService extends Service {
	public LocationMyService() {
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		try {
			LocationListener listener = new LocationListener() {
				@Override
				public void onLocationChanged(Location location) {
					double lat = location.getLatitude();
					double lon = location.getLongitude();
					double speed = location.getSpeed();
					// do something with this values
					
					String data = lat+" : "+lon+" : "+speed;
					Utility.saveToAFile(getApplicationContext(), data,"data.txt");
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
					stopSelf();
					Log.d("DATA", "PROVIDER DISENABLED " + provider);
				}
			};
			String provider = LocationManager.GPS_PROVIDER;
			manager.requestLocationUpdates(provider, 5000, 5, listener);
		} catch (SecurityException e) {
			Log.e("DATA", "Could Not Fetch The Location");
		}

		// manager.removeUpdates();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
