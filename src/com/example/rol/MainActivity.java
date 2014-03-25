package com.example.rol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button initiative;
	private Button dices;
	private Button about;
	private Button exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prepareInitiativeButton();
		prepareDicesButton();
		prepareAboutButton();
		prepareExitButton();
	}

	private void prepareAboutButton() {
		about = (Button) findViewById(R.id.about);
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), About.class);
				startActivity(i);
			}
		});
		
	}

	private void prepareExitButton(){
		exit = (Button) findViewById(R.id.exit);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	private void prepareDicesButton() {
		dices = (Button) findViewById(R.id.dices);
		dices.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), Dices.class);
				startActivity(i);
				
			}
		});
	}

	private void prepareInitiativeButton() {
		initiative = (Button) findViewById(R.id.initiative);
		initiative.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			  Intent i = new Intent(getApplicationContext(), Initiative.class);
			  startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
