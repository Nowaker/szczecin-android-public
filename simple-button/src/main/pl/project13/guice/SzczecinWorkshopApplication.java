package pl.project13.guice;

import android.preference.PreferenceManager;
import com.google.inject.Module;
import roboguice.application.RoboApplication;

import java.util.List;

/**
 * @author Konrad Malawski
 */
public class SzczecinWorkshopApplication extends RoboApplication {
  @Override
  protected void addApplicationModules(List<Module> modules) {
    modules.add(new SzczecinWorkshopModule());
  }
}
