package com.example.rol;

import android.app.Activity;
import android.content.Context;

public class PlayerRow {

	protected Player player;
	protected PlayerViews views;
	protected Activity stats;
	
	public PlayerRow (Context context) {
		player = new Player();
		views = new PlayerViews(context);
		stats = new Stats();
	}

	public PlayerRow(Context context, Player player) {
		this.player = player;
		stats = new Stats();
		views = new PlayerViews(context);
	}
}
