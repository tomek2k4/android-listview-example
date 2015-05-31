package test.tomek.com.losowalistaprzyklad;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by tomasz on 24.05.2015.
 */
public class PowerStatusReceiver  extends BroadcastReceiver {


    private static final int NOTIFICATION_ID = 666;

    @Override
    public void onReceive(Context context, Intent intent) {
        String powerStatus ="";
        Toast.makeText(context,"Power Status Changed.",Toast.LENGTH_LONG).show();


        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            powerStatus = context.getString(R.string.connected);

            //Zapamietaj czas podlaczenia
        }else{
            powerStatus = context.getString(R.string.disconnected);
        }

        Notification.Builder nbuilder = new Notification.Builder(context);

        nbuilder.setSmallIcon(R.drawable.select_shape);
        nbuilder.setContentTitle(context.getString(R.string.power_status_changed));
        nbuilder.setContentText(powerStatus);
        nbuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(context,RandomListActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,1,resultIntent,0);

        nbuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIFICATION_ID,nbuilder.build());



    }
}
