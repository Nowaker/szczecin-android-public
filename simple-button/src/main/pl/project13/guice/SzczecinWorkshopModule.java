package pl.project13.guice;

import pl.project13.worker.NumberGenerator;
import pl.project13.worker.RandomNumberGenerator;
import roboguice.config.AbstractAndroidModule;
import roboguice.inject.SharedPreferencesName;

/**
 * @author Konrad Malawski
 */
public class SzczecinWorkshopModule extends AbstractAndroidModule {

  @Override
  protected void configure() {

    bind(NumberGenerator.class).to(RandomNumberGenerator.class).asEagerSingleton();

    // BUG need a better way to set default preferences context
    bindConstant().annotatedWith(SharedPreferencesName.class).to("pl.project13");
  }
}
