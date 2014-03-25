package com.example.rol;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PlayerViews extends LinearLayout{
	//Player player;

	TextView hitpoints;
	TextView armorclass;
	TextView attackbonus;

	EditText name;
	EditText bonus;
	EditText dice;
	EditText total;

	Button sum1hp;
	Button quit1hp;

	Button stats;
	Button delete;

	Spinner action1;
	Spinner action2;
	
	public PlayerViews(Context context) {
		super(context);
	}

    public PlayerViews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerViews(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("SWIPED", "onLayout : " + Boolean.toString(changed));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        super.onInterceptTouchEvent(event);
        Log.e("SWIPED", "onInterceptTouchEvent : " + event.getAction());
        return false;
    }

}
