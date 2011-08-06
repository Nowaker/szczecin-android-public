package pl.project13;

import android.graphics.*;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import com.google.android.maps.*;

import java.util.List;

public class MyGPSActivity extends MapActivity implements LocationListener {

  private final String TAG = getClass().getSimpleName();

  // views
  private MapView map;

  // services
  private LocationManager locationManager;

  // Szczecin!
  double szczecinLat = 53.44731;
  double szczecinLng = 14.4923;

  private GeoPoint currentLocation = new GeoPoint(asLongPos(szczecinLat), asLongPos(szczecinLng));

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // init fields
    map = (MapView) findViewById(R.id.map_view);

    map.setBuiltInZoomControls(true);

    List<Overlay> overlays = map.getOverlays();
    overlays.add(new MyLocationMapOverlay());

    map.getController().animateTo(currentLocation);

    map.setSatellite(true);

    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    // find location
//    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
  }

  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }

  @Override
  public void onLocationChanged(Location location) {
    if (location != null) {
      double lat = location.getLatitude();
      double lng = location.getLongitude();

      Toast toast = Toast.makeText(getApplicationContext(),
                                   "I'm currently at: " +
                                       "Latitude = " + lat +
                                       "Longitude = " + lng,
                                   Toast.LENGTH_LONG);
      toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
      toast.show();

      recenterMapTo(lat, lng);
    }
  }

  private void recenterMapTo(double lat, double lng) {
    Log.i(TAG, "recenter map to: " + lat + ", " + lng);

    currentLocation = new GeoPoint(asLongPos(lat), asLongPos(lng));

    MapController mapController = map.getController();
    mapController.animateTo(currentLocation);
  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {
  }

  @Override
  public void onProviderEnabled(String s) {
    Toast.makeText(getApplicationContext(),
                   "Gps Enabled",
                   Toast.LENGTH_SHORT)
         .show();
  }

  @Override
  public void onProviderDisabled(String s) {
    Toast.makeText(getApplicationContext(),
                   "Gps Disabled",
                   Toast.LENGTH_SHORT)
         .show();
  }

  private int asLongPos(double langOrLong) {
    return (int) langOrLong * 1000000;
  }

  /**
   * Adds an "Here I am!" marker to the map
   *
   * @author Konrad Malawski
   */
  public class MyLocationMapOverlay extends Overlay {
    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean b, long l) {
      super.draw(canvas, mapView, b, l);

      Paint paint = new Paint();

      if (currentLocation != null) {
        Point myScreenCoords = new Point();
        mapView.getProjection().toPixels(currentLocation, myScreenCoords);

        paint.setStrokeWidth(1);
        paint.setARGB(255, 255, 255, 255);
        paint.setStyle(Paint.Style.STROKE);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
        canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
      }

      return true;
    }
  }
}
