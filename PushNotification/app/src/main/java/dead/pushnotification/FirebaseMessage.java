package dead.pushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessage extends FirebaseMessagingService{

    private static final int REQuest_code=38;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("TAg","Message From" +remoteMessage.getFrom() );
        Log.d("TAg","Message From" +remoteMessage.getNotification().getBody() );
        sendNotifiaction(remoteMessage.getFrom(),remoteMessage.getNotification().getBody());


    }
    private void sendNotifiaction(String from,String body) {


        Intent explicitIntent=new Intent(getApplicationContext(), MainActivity.class);


        PendingIntent intent=PendingIntent.getActivity(this,REQuest_code,explicitIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);

        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        builder.setContentTitle("From : "+from);
        builder.setContentText("Message Body: "+body);
        builder.setContentIntent(intent);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

        builder.setAutoCancel(true);//Automaticlly gets cancelled on click

        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(REQuest_code,builder.build());
    }
}
