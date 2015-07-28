package com.androidhuman.example.simpleservice;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class SimpleService extends Service {
	private KeyguardManager km = null; 
	private KeyguardManager.KeyguardLock keylock = null;
	

	private BroadcastReceiver mReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, getString(R.string.serviceAlive), Toast.LENGTH_LONG).show();
            String action = intent.getAction();
			if(action.equals("android.intent.action.SCREEN_OFF")){
            	Intent i = new Intent(context, lockscreendemo.class);
            	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	context.startActivity(i);
            }
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

        /*
		km=(KeyguardManager) this.getSystemService(Activity.KEYGUARD_SERVICE);
        if(km!=null){
        	keylock = km.newKeyguardLock("test");
        	keylock.disableKeyguard();
        }
        */
        
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Toast.makeText(this, getString(R.string.serviceStart), Toast.LENGTH_LONG).show();

		Notification notification = new Notification(R.mipmap.ic_launcher, getString(R.string.serviceRunning), System.currentTimeMillis());
		notification.setLatestEventInfo(getApplicationContext(), getString(R.string.app_name), getString(R.string.serviceStateRunning), null);
		startForeground(1, notification);

		IntentFilter filter = new IntentFilter("com.androidhuman.action.isAlive");
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(mReceiver, filter);
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy(){
/*
		if(keylock!=null){
				keylock.reenableKeyguard();
		}
*/
		if(mReceiver != null)
			unregisterReceiver(mReceiver);
		Toast.makeText(this, getString(R.string.serviceStop), Toast.LENGTH_LONG).show();
	}

}
