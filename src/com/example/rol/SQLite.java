package com.example.rol;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLite extends SQLiteOpenHelper {

	// Métodos de SQLiteOpenHelper

	public SQLite(Context context) {

		super(context, "personajes", null, 15);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//db = getWritableDatabase();
		
		
		db.execSQL("DROP TABLE IF EXISTS players");
		db.execSQL("DROP TABLE IF EXISTS partys");
		
		db.execSQL("CREATE TABLE players (" + "name VARCHAR(25) PRIMARY KEY, "
				+ "dice VARCHAR(25) NOT NULL," + "bonus VARCHAR(25) NOT NULL,"
				+ "total VARCHAR(25) NOT NULL," + "action1 INTEGER NOT NULL,"
				+ "action2 INTEGER NOT NULL," + "hitpoints VARCHAR(25) NOT NULL,"
				+ "armorclass VARCHAR(25) NOT NULL,"+ "attackbonus VARCHAR(25) NOT NULL," 
				+ "alignment INTEGER NOT NULL,"+ "hitdice VARCHAR(25) NOT NULL,"
				+ "damage VARCHAR(25) NOT NULL,"+"movement VARCHAR(25) NOT NULL,"
				+ "moral VARCHAR(25) NOT NULL,"
				+ "salvation VARCHAR(25) NOT NULL," + "treasure VARCHAR(25) NOT NULL,"
				+ "experience VARCHAR(25) NOT NULL"+ ")");

		db.execSQL("CREATE TABLE partys (" + "indice INTEGER PRIMARY KEY, "
				+ "partyname VARCHAR(25) NOT NULL, "
				+ "playername VARCHAR(25) NOT NULL,"
				+ "FOREIGN KEY (playername) REFERENCES players (name)" + ")");

	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		// NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la
		// opción de
		// eliminar la tabla anterior y crearla de nuevo vacía con el nuevo
		// formato.
		// Sin embargo lo normal será que haya que migrar datos de la tabla
		// antigua
		// a la nueva, por lo que este método debería ser más elaborado.

		// Se elimina la versión anterior de la tabla
		
		//db = getWritableDatabase();

		
		db.execSQL("DROP TABLE IF EXISTS players");
		db.execSQL("DROP TABLE IF EXISTS partys");

		// Se crea la nueva versión de la tabla

		db.execSQL("CREATE TABLE players (" + "name VARCHAR(25) PRIMARY KEY, "
				+ "dice VARCHAR(25) NOT NULL," + "bonus VARCHAR(25) NOT NULL,"
				+ "total VARCHAR(25) NOT NULL," + "action1 INTEGER NOT NULL,"
				+ "action2 INTEGER NOT NULL," + "hitpoints VARCHAR(25) NOT NULL,"
				+ "armorclass VARCHAR(25) NOT NULL,"+ "attackbonus VARCHAR(25) NOT NULL," 
				+ "alignment INTEGER NOT NULL,"+ "hitdice VARCHAR(25) NOT NULL,"
				+ "damage VARCHAR(25) NOT NULL,"+"movement VARCHAR(25) NOT NULL,"
				+ "moral VARCHAR(25) NOT NULL,"
				+ "salvation VARCHAR(25) NOT NULL," + "treasure VARCHAR(25) NOT NULL,"
				+ "experience VARCHAR(25) NOT NULL"+ ")");
		
		db.execSQL("CREATE TABLE partys (" + "indice INTEGER PRIMARY KEY, "
				+ "partyname VARCHAR(25) NOT NULL, "
				+ "playername VARCHAR(25) NOT NULL,"
				+ "FOREIGN KEY (playername) REFERENCES players (name)" + ")");
	}

	// Métodos de AlmacenPuntuaciones

	public void savePlayer(Player plr) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues playerValues = new ContentValues();

		playerValues.put("name", plr.getName());
		playerValues.put("dice", plr.getDice());
		playerValues.put("bonus", plr.getBonus());
		playerValues.put("total", plr.getTotal());
		playerValues.put("action1", plr.getAction1());
		playerValues.put("action2", plr.getAction2());
		playerValues.put("hitpoints", plr.getHitpoints());
		playerValues.put("armorclass", plr.getArmorclass());
		playerValues.put("attackbonus", plr.getAttackbonus());
		playerValues.put("alignment", plr.getAlignment());
		playerValues.put("hitdice", plr.getHitDice());
		playerValues.put("damage", plr.getDamage());
		playerValues.put("movement", plr.getMovement());
		playerValues.put("moral", plr.getMoral());
		playerValues.put("salvation", plr.getSalvation());
		playerValues.put("treasure", plr.getTreasure());
		playerValues.put("experience", plr.getExppoints());
		try {
			Log.i("SQL", playerValues.toString() + db.toString());

			db.insertOrThrow("players", null, playerValues);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveParty_(List<PlayerRow> items, String name) {
		SQLiteDatabase db = getWritableDatabase();

		for (int i = 0; i < items.size(); i++) {
			PlayerRow plr = items.get(i);
			ContentValues partyValues = new ContentValues();

			partyValues.put(String.valueOf("partyname"), name);
			partyValues.put(String.valueOf("playername"), plr.player.getName());

			try {
				Log.i("SQL", partyValues.toString() + db.toString());
				savePlayer(plr.player);
				db.insertOrThrow("partys", null, partyValues);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void saveParty(List<PlayerRow> items, String name) {
		SQLiteDatabase db = getWritableDatabase();

		for (int i = 0; i < items.size(); i++) {
			PlayerRow plr = items.get(i);
			ContentValues partyValues = new ContentValues();
			ContentValues playerValues = new ContentValues();

			playerValues.put("name", plr.player.getName());
			playerValues.put("dice", plr.player.getDice());
			playerValues.put("bonus", plr.player.getBonus());
			playerValues.put("total", plr.player.getTotal());
			playerValues.put("action1", plr.player.getAction1());
			playerValues.put("action2", plr.player.getAction2());
			playerValues.put("hitpoints", plr.player.getHitpoints());
			playerValues.put("armorclass", plr.player.getArmorclass());
			playerValues.put("attackbonus", plr.player.getAttackbonus());
			playerValues.put("alignment", plr.player.getAlignment());
			playerValues.put("hitdice", plr.player.getHitDice());
			playerValues.put("damage", plr.player.getDamage());
			playerValues.put("movement", plr.player.getMovement());
			playerValues.put("moral", plr.player.getMoral());
			playerValues.put("salvation", plr.player.getSalvation());
			playerValues.put("treasure", plr.player.getTreasure());
			playerValues.put("experience", plr.player.getExppoints());
			
			
			partyValues.put("partyname", name);
			partyValues.put("playername", plr.player.getName());
			
			try {
				db.insertOrThrow("partys" , null, partyValues);
				db.insertOrThrow("players", null, playerValues);
				Log.i("SQL", playerValues.toString() + db.toString());
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				String[] whereArgs = { String.valueOf(plr.player.getName()) };
				db.update("players", playerValues, "name = ?", whereArgs);
				
			}
		}
	}

	public ArrayList<Player> loadPlayers(String partyName) {

		ArrayList<Player> result = new ArrayList<Player>();

		SQLiteDatabase db = getReadableDatabase();
		String[] table = null;
		Cursor cursor = db
				.rawQuery(
						"SELECT name, dice, bonus, total, hitpoints, armorclass, attackbonus, action1, action2, alignment, hitdice, damage, movement, moral, salvation, treasure, experience, partyname FROM "
								+ "partys, players WHERE partyname = '"
								+ partyName + "'" + " AND playername = name",
						table);

		while (cursor.moveToNext()) {

			result.add(new Player(cursor.getString(0), cursor.getInt(1), cursor
					.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor
					.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor
					.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getString(11),
					cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15),
					cursor.getString(16)));
			Log.i("SQL", cursor.toString() + db.toString());

		}

		cursor.close();
		return result;
	}

	public List<String> loadPlayerNames(String partyName) {

		List<String> result = new ArrayList<String>();

		SQLiteDatabase db = getReadableDatabase();
		String[] table = null;
		Cursor cursor = db.rawQuery("SELECT playername, partyname FROM "
				+ "partys WHERE partyname = '" + partyName + "'", table);

		while (cursor.moveToNext()) {

			result.add(cursor.getString(0));
			Log.i("SQL", cursor.toString() + db.toString());
		}
		cursor.close();
		return result;
	}

	public Player loadPlayer(String playerName, String partyName) {

		Player result = null;

		SQLiteDatabase db = getReadableDatabase();
		String[] table = null;
		Cursor cursor = db
				.rawQuery(
						"SELECT name, dice, bonus, total, hitpoints, armorclass, attackbonus, action1, action2, alignment, hitdice, damage, movement, moral, salvation, treasure, experience, partyname, playername FROM "
								+ "partys, players WHERE partyname = '"
								+ partyName
								+ "'"
								+ " AND playername = name"
								+ " AND name = '" + playerName + "'", table);

		while (cursor.moveToNext()) {

			result = new Player(cursor.getString(0), cursor.getInt(1), cursor
					.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor
					.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor
					.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getString(11),
					cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15),
					cursor.getString(16));
			Log.i("SQL", cursor.toString() + db.toString());
		}
		cursor.close();
		return result;
	}

	public Player loadPlayer(String playerName) {

		Player result = new Player();

		SQLiteDatabase db = getReadableDatabase();
		String[] table = null;
		Cursor cursor = db
				.rawQuery(
						"SELECT name, dice, bonus, total, hitpoints, armorclass, attackbonus, action1, action2, alignment, hitdice, damage, movement, moral, salvation, treasure, experience FROM "
								+ "players WHERE name = '" + playerName + "'",
						table);

		while (cursor.moveToNext()) {

			result = new Player(cursor.getString(0), cursor.getInt(1), cursor
					.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor
					.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor
					.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getString(11),
					cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15),
					cursor.getString(16));
			Log.i("SQL", cursor.toString() + db.toString());
		}
		cursor.close();
		return result;
	}

	public void deletePlayerByName(String string, SQLiteDatabase db) {
		// SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DELETE FROM players WHERE name = '" + string + "'");
		Log.i("SQL", db.toString());
	}

	public int getPlayersDBSize() {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + "players", null);
		int result = cursor.getCount();
		cursor.close();
		return result;
	}

	public int getPartysDBSize() {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + "partys", null);
		int result = cursor.getCount();
		cursor.close();
		return result;
	}

	public List<String> getPartyNames() {
		SQLiteDatabase db = getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT DISTINCT partyname FROM "
				+ "partys", null);
		List<String> result = new ArrayList<String>();
		while (cursor.moveToNext()) {
			result.add(cursor.getString(0));
			Log.i("SQL", cursor.toString() + db.toString());
		}
		cursor.close();
		return result;
	}

	public void deleteParty(String partyName) {
		SQLiteDatabase db = getWritableDatabase();

		List<Player> players = loadPlayers(partyName);
		for (int i = 0; i < players.size(); i++) {
			Player plr = players.get(i);
			deletePlayerByName(plr.getName(), db);
		}
		db.execSQL("DELETE FROM partys WHERE partyname = '" + partyName + "'");
		Log.i("SQL", db.toString());

	}

	public List<String> getPlayerNames(String partyName) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT name FROM " + "players, party"
				+ "WHERE partyname='" + partyName + "' ORDER BY partyname",
				null);
		ArrayList<String> result = new ArrayList<String>();
		while (cursor.moveToNext()) {
			result.add(cursor.getString(0));
			Log.i("SQL", cursor.toString() + db.toString());
		}
		cursor.close();
		return result;
	}
}