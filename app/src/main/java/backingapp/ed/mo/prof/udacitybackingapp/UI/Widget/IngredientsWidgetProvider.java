package backingapp.ed.mo.prof.udacitybackingapp.UI.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import backingapp.ed.mo.prof.udacitybackingapp.R;
import backingapp.ed.mo.prof.udacitybackingapp.Services.IngredientsWidgetService;
import backingapp.ed.mo.prof.udacitybackingapp.UI.Activities.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    public static void updateAppWidget(Context context, String widgetText , AppWidgetManager appWidgetManager,
                                       int []appWidgetId) {
        Intent intent=new Intent(context, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(context,0,intent,0);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        IngredientsWidgetService.startActionFillWidget(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}