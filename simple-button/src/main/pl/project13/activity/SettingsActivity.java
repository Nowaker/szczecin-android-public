package pl.project13.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import pl.project13.R;
import roboguice.activity.RoboPreferenceActivity;

/**
 * @author Konrad Malawski
 */
public class SettingsActivity extends RoboPreferenceActivity {

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preference);
  }
}