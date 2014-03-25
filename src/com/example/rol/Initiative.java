package com.example.rol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class Initiative extends ListActivity {

	static PlayerListAdapter adapter;
	static int size;
	static View selectedView;

	private static String partyName;
	private String playerName;

	private Button addplr;
	private Button newturn;
	private Button sort;
	private Button save;
	private Button load;
	// private static boolean mItemsCanFocus;
	// private Bundle savedInstanceState;

	private static SQLite sql;
	static ListView listView;
	static int position;
	static long id;
	protected static Bundle savedBundle;
	private static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.initiative);
		sql = new SQLite(getApplicationContext());
		context = this;
		savedBundle = savedInstanceState;
		setupListViewAdapter();
		// position = adapter.position;
		setupAddPlrButton();
		setupSortButton();
		setupSaveButton();
		setupLoadButton();
		setupNewTurnButton();
		


	}
	@Override
	protected void onRestart(){
		super.onRestart();
		listView.setAdapter(adapter);
	}
	
	
	private void setupLoadButton() {
		load = (Button) findViewById(R.id.load);
		load.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadParty();
				// Initiative.setItemsCanFocus(false);
			}
		});
	}

	private void setupSaveButton() {
		save = (Button) findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveParty();
				// Initiative.setItemsCanFocus(false);
			}
		});
	}

	private void setupNewTurnButton() {
		newturn = (Button) findViewById(R.id.newturn);

		newturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (PlayerRow plr : adapter.getItems()) {
					plr.player.setDice((int)(1 + (Math.random() * 20)));
					plr.player.setTotal(plr.player.getDice()
							+ plr.player.getBonus());
					sortAdapter();
					// Initiative.setItemsCanFocus(false);
				}
			}
		});
	}

	// Use onSaveInstanceState(Bundle) and onRestoreInstanceState

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate if the process is
		// killed and restarted.
		savedInstanceState.putInt("size", adapter.getCount());
		savedInstanceState.putInt("position", PlayerListAdapter.index);
		Log.i("POSITION BUNDLE", "" + PlayerListAdapter.index);
		Integer i = 0;
		for (PlayerRow plr : adapter.getItems()) {
			savedInstanceState.putString(i.toString() + "Name",
					plr.player.getName());
			savedInstanceState.putInt(i.toString() + "Dice",
					plr.player.getDice());
			savedInstanceState.putInt(i.toString() + "Bonus",
					plr.player.getBonus());
			savedInstanceState.putInt(i.toString() + "Total",
					plr.player.getTotal());
			savedInstanceState.putInt(i.toString() + "Action1",
					plr.player.getAction1());
			savedInstanceState.putInt(i.toString() + "Action2",
					plr.player.getAction2());
			savedInstanceState.putInt(i.toString() + "Hitpoints",
					plr.player.getHitpoints());
			savedInstanceState.putInt(i.toString() + "ArmorClass",
					plr.player.getArmorclass());
			savedInstanceState.putInt(i.toString() + "AttackBonus",
					plr.player.getAttackbonus());
			savedInstanceState.putString(i.toString() + "Moral",
					plr.player.getMoral());
			savedInstanceState.putString(i.toString() + "Movement",
					plr.player.getMovement());
			savedInstanceState.putString(i.toString() + "Salvation",
					plr.player.getSalvation());
			savedInstanceState.putInt(i.toString() + "HitDice",
					plr.player.getHitDice());
			savedInstanceState.putString(i.toString() + "Damage",
					plr.player.getDamage());
			savedInstanceState.putString(i.toString() + "Treasure",
					plr.player.getTreasure());
			savedInstanceState.putString(i.toString() + "ExpPoints",
					plr.player.getExppoints());
			i += 1;
		}
		// etc.
		super.onSaveInstanceState(savedInstanceState);
	}

	// onRestoreInstanceState
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore UI state from the savedInstanceState.
		// This bundle has also been passed to onCreate.
		int position = savedInstanceState.getInt("size");
		for (Integer i = 0; i < position; i++) {
			PlayerRow plr = new PlayerRow(context);
			plr.player.setName(savedInstanceState.getString(i
					.toString() + "Name"));
			plr.player.setDice(savedInstanceState.getInt(i
					.toString() + "Dice"));
			plr.player.setBonus(savedInstanceState.getInt(i
					.toString() + "Bonus"));
			plr.player.setTotal(savedInstanceState.getInt(i
					.toString() + "Total"));
			plr.player.setAction1(savedInstanceState.getInt(i
					.toString() + "Action1"));
			plr.player.setAction2(savedInstanceState.getInt(i
					.toString() + "Action2"));
			plr.player.setHitpoints(savedInstanceState.getInt(i
					.toString() + "Hitpoints"));
			plr.player.setArmorclass(savedInstanceState.getInt(i
					.toString() + "ArmorClass"));
			plr.player.setAttackbonus(savedInstanceState.getInt(i
					.toString() + "AttackBonus"));
			plr.player.setMoral(savedInstanceState.getString(i.toString() + "Moral"));
			plr.player.setMovement(savedInstanceState.getString(i.toString() + "Movement"));			
			plr.player.setSalvation(savedInstanceState.getString(i.toString() + "Salvation"));
			plr.player.setHitDice(savedInstanceState.getInt(i.toString() + "HitDice"));			
			plr.player.setDamage(savedInstanceState.getString(i.toString() + "Damage"));			
			plr.player.setTreasure(savedInstanceState.getString(i.toString() + "Treasure"));			
			plr.player.setExppoints(savedInstanceState.getString(i.toString() + "ExpPoints"));
			
			adapter.getItems().add(0, plr);
		}
	}

	public static void removeAtomPayOnClickHandler(View v) {
		PlayerRow itemToRemove = (PlayerRow) v.getTag();
		adapter.getItems().remove(itemToRemove);
		Log.i("SQL", itemToRemove.player.getName());
		Initiative.size = adapter.getCount();
		listView.setAdapter(adapter);
	}

	private void setupListViewAdapter() {
		adapter = new PlayerListAdapter(Initiative.this,
				R.layout.initiative_element, new ArrayList<PlayerRow>());

		listView = (ListView) findViewById(android.R.id.list);
		listView.setTag(listView);
		listView.setAdapter(adapter);
		listView.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (v.hasFocus()) {

					Initiative.listView
							.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

					adapter.setSelected(null);
					adapter.selectedPos = -1;

				} else {
					Initiative.listView
							.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
				}

				Log.i("List ", v.getTag().toString() + " " + v.hasFocus());

			}
		});
	}

	private void setupAddPlrButton() {
		addplr = (Button) findViewById(R.id.addplr);
		addplr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setUpNewPlayerAlert();
			}			
		});
	}
	private void setUpNewPlayerAlert() {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);

		alert.setTitle("New Player");
		alert.setMessage("Do you wish to load an existing player or create a new one?");

		alert.setPositiveButton("Load Existing One", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int whichButton) {
				setUpPartyAlert();

			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});


		alert.setNeutralButton("Create New One", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				adapter.getItems().add(0, new PlayerRow(context));
				Initiative.size = adapter.getCount();
				listView.setAdapter(adapter);
			}
		});
		alert.show();
	}
	private void setupSortButton() {
		sort = (Button) findViewById(R.id.sort);
		sort.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sortAdapter();
			}
		});
	}

	private void setUpPlayerAlert() {
		AlertDialog.Builder playerAlert = new AlertDialog.Builder(context);

		playerAlert.setTitle("Players");
		playerAlert.setMessage("Select Player.");

		

		List<String> result = sql.loadPlayerNames(partyName);

		// Set an EditText view to get user input
		ArrayAdapter<String> partyNames = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, result);
		partyNames
				.setDropDownViewResource(android.R.layout.simple_spinner_item);

		// Apply the adapter to the spinner
		Spinner playerSpinner = new Spinner(context);
		playerSpinner.setAdapter(partyNames);

		playerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				playerName = (String) parent.getItemAtPosition(pos);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		playerAlert.setView(playerSpinner);

		
		playerAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				Player result = sql.loadPlayer(playerName, partyName);
				PlayerRow pr = new PlayerRow(context, result);
				//pr.player = result;
				adapter.getItems().add(pr);
				listView.setAdapter(adapter);
		}});
		playerAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		playerAlert.show();
	}
	private void setUpPartyAlert() {
		AlertDialog.Builder partyAlert = new AlertDialog.Builder(context);

		partyAlert.setTitle("Partys");
		partyAlert.setMessage("Select Party.");

		

		List<String> result = sql.getPartyNames();

		// Set an EditText view to get user input
		ArrayAdapter<String> partyNames = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, result);
		partyNames
				.setDropDownViewResource(android.R.layout.simple_spinner_item);

		// Apply the adapter to the spinner
		Spinner partySpinner = new Spinner(context);
		partySpinner.setAdapter(partyNames);

		partySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				partyName = (String) parent.getItemAtPosition(pos);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		partyAlert.setView(partySpinner);
		
		partyAlert.setPositiveButton("Select this party", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog,	int which) {
						setUpPlayerAlert();									
			}
			
		});
		
		partyAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		partyAlert.show();
	}
	static void sortAdapter() {
		Comparator<PlayerRow> c = new Comparator<PlayerRow>() {

			@Override
			public int compare(PlayerRow o1, PlayerRow o2) {
				int resultado = 0;
				if (Integer.valueOf(o1.player.getTotal()) < Integer.valueOf(o2.player.getTotal())) {
					resultado = -1;
				} else if (Integer.valueOf(o1.player.getTotal()) > Integer.valueOf(o2.player.getTotal())) {
					resultado = 1;
				}
				return resultado;
			}

		};
		Collections.sort(adapter.getItems(), c);
		listView.setAdapter(adapter);
	}

	public static void saveParty() {

		AlertDialog.Builder alert = new AlertDialog.Builder(context);

		alert.setTitle("Save");
		alert.setMessage("Enter party name");

		// Set an EditText view to get user input
		final EditText input = new EditText(context);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int whichButton) {
				partyName = input.getText().toString();
				if (partyName != null){
					if (sql.getPartyNames().contains(partyName)) {
						AlertDialog.Builder alert = new AlertDialog.Builder(
								context);

						alert.setTitle("Alert");
						alert.setMessage("Are you sure you want to overwrite "
								+ partyName +"?");
						alert.setPositiveButton("Overwrite",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										sql.deleteParty(partyName);					
										sql.saveParty(adapter.getItems(), partyName);					
									}
								});
						alert.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										// Canceled.
									}
								});

						alert.show();
					}
				sql.saveParty(adapter.getItems(), partyName);					
				// Do something with value!
				}
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();
	}

	public static void loadParty() {

		List<String> result = sql.getPartyNames();

		AlertDialog.Builder alert = new AlertDialog.Builder(context);

		alert.setTitle("Load");
		alert.setMessage("Enter party name ");

		// Set an EditText view to get user input
		ArrayAdapter<String> partyNames = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, result);
		partyNames
				.setDropDownViewResource(android.R.layout.simple_spinner_item);

		// Apply the adapter to the spinner
		Spinner input = new Spinner(context);
		input.setAdapter(partyNames);

		input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				partyName = (String) parent.getItemAtPosition(pos);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int whichButton) {

				// Do something with value!
				if (partyName != null){
					List<Player> result = sql.loadPlayers(partyName);
					for (Player pl : result){
						PlayerRow pr = new PlayerRow(context);
						pr.player = pl;
						adapter.getItems().add(pr);
						listView.setAdapter(adapter);
					}
	
				}
				
				
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.setView(input);
		alert.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (partyName != null)
					sql.deleteParty(partyName);
			}
		});
		alert.show();

		listView.setAdapter(adapter);

	}
}
