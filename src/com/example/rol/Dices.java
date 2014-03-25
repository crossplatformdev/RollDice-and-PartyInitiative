package com.example.rol;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Dices extends Activity {
	
	private ToggleButton dfour;
	private ToggleButton dsix;
	private ToggleButton deight;
	private ToggleButton dten;
	private ToggleButton dtwelve;
	private ToggleButton dtwenty;
	private ToggleButton dhundred;
	private Button diceroll;
	
	private ArrayList<Roll> results;
	
	private LinearLayout container;
	
	//private TextView dicerolltext;
	private Roll d4,d6,d8,d10,d12,d20,d100;
	private Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dices);
		context = this;
		results = new ArrayList<Roll>();
		container = (LinearLayout) findViewById(R.id.diceroll);
		setD4();
		setD6();
		setD8();
		setD10();
		setD12();
		setD20();
		setD100();
		setDiceRollButton();
	}
	
	private void setDiceRollButton() {
		diceroll = (Button) findViewById(R.id.roll);
		diceroll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(container.getChildCount()!= 0) container.removeAllViews();
				for(Roll dice : results) dice.roll();
				Collections.sort(results);
				setDiceRollText(context);
			}
		});
	}

	private void setDiceRollText(Context context) {
		//dicerolltext = (TextView) findViewById(R.id.diceroll);
		//String str = "1D"+results.get(0).dicemax.toString()+"="+results.get(0).result.toString();
		//dicerolltext.setText(str);
		for(Roll r : results){
			TextView dicerolltext = new TextView(context);
			String string = "1D"+r.getDicemax().toString()+" = "+r.getResult().toString();
			dicerolltext.setText(string);
			dicerolltext.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20.0F);
			dicerolltext.setGravity(Gravity.CENTER);			
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);			
			container.addView(dicerolltext,lp);
		}
		
	}

	private void buttonSelector(ToggleButton button, boolean isChecked, Roll roll, int maxdice) {
		if(isChecked){
			//roll = new Roll(maxdice);
			button.setTextColor(Color.RED);
			button.setChecked(true);
			results.add(roll);
		} else {
			button.setTextColor(Color.BLACK);
			button.setChecked(false);
			results.remove(roll);
		}
		
		
	}
	
	private void setD4() {
		dfour = (ToggleButton) findViewById(R.id.d4);
		d4 = new Roll(4);
		dfour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        buttonSelector((ToggleButton) buttonView, isChecked, d4, 4);
		    }
		});
	}
	
	
	private void setD6() {
		dsix = (ToggleButton) findViewById(R.id.d6);
		d6 = new Roll (6);
		dsix.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        buttonSelector((ToggleButton) buttonView, isChecked, d6, 6);
		    }
		});
	}
	
	private void setD8() {
		deight = (ToggleButton) findViewById(R.id.d8);
		d8 = new Roll (8);
		deight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        buttonSelector((ToggleButton) buttonView, isChecked, d8, 8);
		    }
		});
	}
	
	private void setD10() {
		dten = (ToggleButton) findViewById(R.id.d10);
		d10 = new Roll(10);
		dten.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        buttonSelector((ToggleButton) buttonView, isChecked, d10, 10);
		    }
		});
	}
	
	private void setD12() {
		dtwelve = (ToggleButton) findViewById(R.id.d12);
		d12 = new Roll(12);
		dtwelve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        buttonSelector((ToggleButton) buttonView, isChecked, d12, 12);
		    }
		});
	}
	
	private void setD20() {
		dtwenty = (ToggleButton) findViewById(R.id.d20);
		d20 = new Roll (20);
		dtwenty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        buttonSelector((ToggleButton) buttonView, isChecked, d20, 20);
		    }
		});
	}
	
	private void setD100() {
		dhundred = (ToggleButton) findViewById(R.id.d100);
		d100 = new Roll (100);
		dhundred.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        buttonSelector((ToggleButton) buttonView, isChecked, d100, 100);
		    }
		});
	}
}
