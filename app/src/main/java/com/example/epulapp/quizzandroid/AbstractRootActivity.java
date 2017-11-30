package com.example.epulapp.quizzandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class AbstractRootActivity extends AppCompatActivity implements MenuFragment.MenuCallback {

    private BroadcastReceiver receiver;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quizz);

        receiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("notification", "Received broadcast");

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

                Intent result = new Intent(context, AbstractRootActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(AbstractRootActivity.class);
                stackBuilder.addNextIntent(result);

                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1, builder.build());
            }
        };

        fragmentManager = getFragmentManager();
        MenuFragment menu = new MenuFragment();
        changeFragment(menu, false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("notification"));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    public void onButtonClicked(View view) {
        QuizzFragment question = new QuizzFragment();
        changeFragment(question, true);
    }

    private void changeFragment(Fragment frag, boolean isReplace){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isReplace){
            fragmentTransaction.replace(R.id.fragment_container, frag);
        } else {
            fragmentTransaction.add(R.id.fragment_container, frag);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
