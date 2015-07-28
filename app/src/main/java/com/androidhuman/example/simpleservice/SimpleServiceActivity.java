package com.androidhuman.example.simpleservice;

//NewComment

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SimpleServiceActivity extends Activity implements OnClickListener{
	private Button startServiceButton;
	private Button stopServiceButton;
	private Button checkAliveButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = (Button)findViewById(R.id.start_Service);
        stopServiceButton = (Button)findViewById(R.id.stop_Service);
        checkAliveButton = (Button)findViewById(R.id.check_Alive);
        
        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);
        checkAliveButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.start_Service:
			startService(new Intent(this, SimpleService.class));
			break;
		
		case R.id.stop_Service:
			stopService(new Intent(this, SimpleService.class));
			break;
			
		case R.id.check_Alive:
			sendBroadcast(new Intent("com.androidhuman.action.isAlive"));
			break;
		}
	}
}