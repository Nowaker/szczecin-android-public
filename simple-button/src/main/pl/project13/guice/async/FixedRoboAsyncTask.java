package pl.project13.guice.async;

import android.os.Handler;
import roboguice.inject.InjectorProvider;
import roboguice.util.RoboAsyncTask;

import java.util.concurrent.Executor;

/**
 * @author Konrad Malawski
 */
public abstract class FixedRoboAsyncTask<Result> extends RoboAsyncTask<Result> {

  protected FixedRoboAsyncTask() {
    ((InjectorProvider)context).getInjector().injectMembers(this);
  }

  protected FixedRoboAsyncTask(Handler handler) {
    super(handler);
    ((InjectorProvider)context).getInjector().injectMembers(this);
  }

  protected FixedRoboAsyncTask(Handler handler, Executor executor) {
    super(handler, executor);
    ((InjectorProvider)context).getInjector().injectMembers(this);
  }

  protected FixedRoboAsyncTask(Executor executor) {
    super(executor);
    ((InjectorProvider)context).getInjector().injectMembers(this);
  }
}
