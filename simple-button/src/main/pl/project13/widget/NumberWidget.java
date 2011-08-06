package pl.project13.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import pl.project13.R;
import pl.project13.service.RandomNumbersService;

/**
 * @author Konrad Malawski
 */
public class NumberWidget extends AppWidgetProvider {

  int myNumber = 0;

  public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals(RandomNumbersService.NUMBER_INTENT_ACTION_NAME)) {

      Bundle extras = intent.getExtras();
      myNumber = extras.getInt(RandomNumbersService.EXTRA_NUMBER);

      Log.i("NumberWidget", "Got " + RandomNumbersService.NUMBER_INTENT_ACTION_NAME + " [" + myNumber + "] Intent!");

      AppWidgetManager mngr = AppWidgetManager.getInstance(context);
      int[] appWidgetIds = mngr.getAppWidgetIds(intent.getComponent());
      onUpdate(context, mngr, appWidgetIds);
    } else {
      super.onReceive(context, intent);
    }
  }

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    Log.i("NumberWidget", "Performing update");

    for (int appWidgetId : appWidgetIds) {
      // Build the intent to call the service
      RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.number_widget);
      remoteViews.setTextViewText(R.id.number_text, "[" + myNumber + "]");

      Log.i("NumberWidget", "Set number: " + myNumber);

      appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    Log.i("NumberWidget", "Done with update");
    super.onUpdate(context, appWidgetManager, appWidgetIds);
  }
}
