/**
 * 
 */
package com.example.rol;

/**
 * @author Elías
 *
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity {
	
	private Button button_donatePaypal;
	private Button button_donatebtc;
	private Button button_send;
	private Button button_exit;
	
	public Context context = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.about);
		
		button_donatePaypal = (Button) findViewById(R.id.donate_paypal);
		button_donatePaypal.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				donatePaypal(null);

			}

			private void donatePaypal(Object object) {
				// TODO Auto-generated method stub
				
			}

		});

		button_donatebtc = (Button) findViewById(R.id.donate_btc);
		button_donatebtc.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				launchDonateBTC();

			}

			private void launchDonateBTC() {
				Intent i = new Intent(context, DonateBTC.class);
				startActivity(i);		
			}

		});

		button_send = (Button) findViewById(R.id.send_email);
		button_send.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				sendEmail(null);

			}

			private void sendEmail(Object object) {
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[]{"elias.angulo.klein@gmail.com"});		  
				email.putExtra(Intent.EXTRA_SUBJECT, "ROL ");
				email.putExtra(Intent.EXTRA_TEXT, "");
				email.setType("message/rfc822");
				startActivity(Intent.createChooser(email, "Choose an Email client :"));
			}

		});

		button_exit = (Button) findViewById(R.id.purchase_premium);
		button_exit.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				purchasePremium(null);

			}

			private void purchasePremium(Object object) {
				// TODO Auto-generated method stub
				
			}

		});


	}

/*
	public void lanzarAcercaDe(View view) {

		Intent i = new Intent(this, About.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);

	}

	public void lanzarPreferencias(View view) {

		Intent i = new Intent(this, Preferencias.class);

		startActivity(i);

	}

	public void lanzarPuntuaciones(View view) {

		Intent i = new Intent(this, Puntuaciones.class);

		startActivity(i);

	}

	public void lanzarJugar(View view) {

		Intent i = new Intent(this, Jugar.class);
		startActivityForResult(i, 1234);

	}

	public void lanzarSalir(View view) {
		finish();
	}
	*/
}