package pl.project13.service;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.inject.Inject;
import pl.project13.worker.NumberGenerator;
import pl.project13.worker.RandomNumberGenerator;
import roboguice.service.RoboService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Konrad Malawski
 */
public class RandomNumbersService extends RoboService {

  public static final String NUMBER_INTENT_ACTION_NAME = "pl.project.NEW_RANDOM_NUMBER";
  public static final String EXTRA_NUMBER = "number";

  @Inject
  NumberGenerator numberGenerator;

  Timer myTimer = new Timer();

  public RandomNumbersService() {
  }

  @Override
  public void onStart(Intent intent, int startId) {
    myTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        int number = numberGenerator.getRandomNumber();

        Intent intent = new Intent(NUMBER_INTENT_ACTION_NAME);
        intent.putExtra(EXTRA_NUMBER, number);

        Log.i(getClass().getSimpleName(), "Sent number: " + number);

        sendBroadcast(intent);
      }
    }, 3000, 3000);
  }

  public IBinder onBind(Intent intent) {
    return null;
  }
}


// advanced example
//    Log.i("UpdateWidgetService", "Called");
//		// Create some random data
//		String fakeUpdate = null;
//		Random random = new Random();
//
//		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
//				.getApplicationContext());
//
//		int[] appWidgetIds = intent
//				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
//		if (appWidgetIds.length > 0) {
//			for (int widgetId : appWidgetIds) {
//				int nextInt = random.nextInt(100);
//				fakeUpdate = "Random: " + String.valueOf(nextInt);
//				RemoteViews remoteViews = new RemoteViews(getPackageName(),
//						R.layout.widget_layout);
//				remoteViews.setTextViewText(R.id.TextView01, fakeUpdate);
//				appWidgetManager.updateAppWidget(widgetId, remoteViews);
//			}
//			stopSelf();
//		}
//		super.onStart(intent, startId);