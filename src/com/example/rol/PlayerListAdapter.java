package com.example.rol;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class PlayerListAdapter extends ArrayAdapter<PlayerRow> {

	protected static final String LOG_TAG = PlayerListAdapter.class
			.getSimpleName();

	protected static List<PlayerRow> items;
	protected static int index;

	private int layoutResourceId;
	// private int currentlyFocusedRow;
	private Context context;
	// static PlayerViews playerViews;
	// protected PlayerViews views;
	static PlayerRow currentPlayer;
	// protected PlayerRow playerRow;

	static int position;
	private ArrayAdapter<CharSequence> adapter1;
	private ArrayAdapter<CharSequence> adapter2;

	int selectedPos = -1;

	ViewGroup layout;

	private static PlayerRow selected;

	public PlayerListAdapter(Context context, int layoutResourceId,
			List<PlayerRow> items) {
		super(context, layoutResourceId);
		this.context = context;
		PlayerListAdapter.items = items;
		this.layoutResourceId = layoutResourceId;
		// views = new PlayerViews();
		// items.add(player);
		// setSelected(currentPlayer);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		PlayerListAdapter.position = position;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		currentPlayer = items.get(position);

		row.setTag(currentPlayer.views);

		layout = (ViewGroup) row;
		layout.setTag(currentPlayer);

		Log.i("Adapter",
				"Focus changed to "
						+ items.indexOf((PlayerRow) layout.getTag()) + " hf"
						+ layout.hasFocus() + " isf" + layout.isFocused());

		layout.setFocusable(false);
		layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				PlayerRow selected = (PlayerRow) v.getTag();
				if (selected.views != null){
					if (!v.isFocusable()) {
						if (!v.hasFocus()) {
							v.setFocusable(true);
							
							Initiative.listView
									.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
							index = items.indexOf(selected);
							Initiative.position = index;
							setSelectedPosition(index);
							setSelected(selected);
						
						} else {
							v.setFocusable(false);
		
							v.clearFocus();
							Initiative.listView
									.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
							setSelectedPosition(-1);
							setSelected(null);
							// pw.views.setClickable(false);
						}
	

						
						Log.i("Player ",
								v.getTag().toString() + " " + v.hasFocus());
						

						
					}
					
				}
			}

		});

		currentPlayer.views.delete = (Button) row.findViewById(R.id.delete);
		currentPlayer.views.delete.setTag(currentPlayer);

		currentPlayer.views.name = (EditText) row.findViewById(R.id.name);

		currentPlayer.views.bonus = (EditText) row.findViewById(R.id.bonus);

		currentPlayer.views.dice = (EditText) row.findViewById(R.id.dice);

		currentPlayer.views.total = (EditText) row.findViewById(R.id.total);
		
		if (currentPlayer != selected){
			currentPlayer.views.name.setEnabled(false);
			currentPlayer.views.dice.setEnabled(false);
			currentPlayer.views.total.setEnabled(false);
			currentPlayer.views.bonus.setEnabled(false);
			layout.setBackgroundColor(Color.DKGRAY);
		
		} else {
			currentPlayer.views.name.setEnabled(true);
			currentPlayer.views.dice.setEnabled(true);
			currentPlayer.views.total.setEnabled(true);
			currentPlayer.views.bonus.setEnabled(true);
			layout.setBackgroundColor(Color.RED);
		}
		currentPlayer.views.stats = (Button) row
				.findViewById(R.id.stats_button);

		currentPlayer.views.delete = (Button) row.findViewById(R.id.delete);

		currentPlayer.views.action1 = (Spinner) row.findViewById(R.id.action1);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		adapter1 = ArrayAdapter.createFromResource(getContext(),
				R.array.action, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);

		// Apply the adapter to the spinner
		currentPlayer.views.action1.setAdapter(adapter1);

		currentPlayer.views.action2 = (Spinner) row.findViewById(R.id.action2);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		adapter2 = ArrayAdapter.createFromResource(getContext(),
				R.array.action, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
		// Apply the adapter to the spinner
		currentPlayer.views.action2.setAdapter(adapter2);

		String str;

		currentPlayer.views.armorclass = (TextView) row
				.findViewById(R.id.armor_class);
		str = row.getResources().getString(R.string.armor_class) + " "
				+ currentPlayer.player.getArmorclass().toString();
		if(str != null) currentPlayer.views.armorclass.setText(str);

		currentPlayer.views.attackbonus = (TextView) row
				.findViewById(R.id.attack_bonus);
		str = row.getResources().getString(R.string.attack_bonus) + " "
				+ currentPlayer.player.getAttackbonus().toString();
		if(str != null) currentPlayer.views.attackbonus.setText(str);

		currentPlayer.views.hitpoints = (TextView) row
				.findViewById(R.id.hitpoints);
		str = row.getResources().getString(R.string.hitpoints) + " "
				+ currentPlayer.player.getHitpoints().toString();
		if(str != null) currentPlayer.views.hitpoints.setText(str);

		currentPlayer.views.sum1hp = (Button) row.findViewById(R.id.sum1hp);

		currentPlayer.views.quit1hp = (Button) row.findViewById(R.id.quit1hp);

		if (selected != null) {
			setNameTextListener(selected);
			setBonusTextListeners(selected);
			setDiceTextListeners(selected);
			setTotalTextListeners(selected);
			setDeleteListeners(selected);
			setStatsListeners(selected);
			setAction1Listeners(selected);
			setAction2Listeners(selected);
			setSum1hpListeners(selected);
			setQuit1hpListeners(selected);

		} else {

		}

		setupItem(currentPlayer);

		return row;
	}

	private void setQuit1hpListeners(PlayerRow pr) {
		pr.views.quit1hp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					pr.player.quit1HP();
					refreshHitpointsText(pr, v);
				}
			}

		});
	}

	private void setSum1hpListeners(PlayerRow pr) {
		pr.views.sum1hp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// PlayerListAdapter pla =
				// (PlayerListAdapter)Initiative.listView.getSelectedItem();
				// PlayerViews pla = (PlayerViews) v.getTag();
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					pr.player.sum1HP();
					refreshHitpointsText(pr, v);
				}
			}

		});
	}

	private void setAction2Listeners(final PlayerRow pr) {
		pr.views.action2
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						// An item was selected. You can retrieve the selected
						// item using

						pr.player.setAction2(pos);
						// Log.i("INFO",
						// parent.getItemAtPosition(pos).toString());

					}

					public void onNothingSelected(AdapterView<?> parent) {
						// Another interface callback
					}

				});
	}

	private void setAction1Listeners(final PlayerRow pr) {
		pr.views.action1
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						// An item was selected. You can retrieve the selected
						// item using

						pr.player.setAction1(pos);
						// Log.i("INFO",
						// parent.getItemAtPosition(pos).toString());
					}

					public void onNothingSelected(AdapterView<?> parent) {
						// Another interface callback
					}

				});
	}

	private void setStatsListeners(final PlayerRow pr) {
		pr.views.stats.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, Stats.class);
				context.startActivity(i);
			}
		});
	}

	private void setDeleteListeners(PlayerRow pr) {
		pr.views.delete.setOnClickListener(new OnClickListener() {
			// private SQLite sql;

			@Override
			public void onClick(View v) {
				Initiative.removeAtomPayOnClickHandler(v);
				// Initiative.loadParty();
			}

		});
	}

	private void setupItem(PlayerRow pr) {
		pr.views.name.setText(pr.player.getName().toString());
		pr.views.dice.setText(pr.player.getDice().toString());
		pr.views.bonus.setText(pr.player.getBonus().toString());
		pr.views.total.setText(pr.player.getTotal().toString());
		pr.views.action1.setSelection(pr.player.getAction1().intValue());
		pr.views.action2.setSelection(pr.player.getAction2().intValue());
	}

	static void setNameTextListener(PlayerRow pr) {
/*
		pr.views.name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// v.setFocusable(true);
				// v.setClickable(true);
				// v.requestFocus();
			}
		});
*/
		pr.views.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					pr.player.setName(s.toString());
				}
				// Toast.makeText(getContext(), "Name changed! To " +
				// player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		/*
		pr.views.name.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {

				}
			}
		});*/
		
	}

	static void setTotalTextListeners(PlayerRow pr) {
/*
		pr.views.total.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// v.setFocusable(true);
				// v.setClickable(true);
				// v.requestFocus();
			}
		});
		*/
		pr.views.total.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					PlayerRow pr = getSelected();
					// Player plr = getItem(PlayerListAdapter.position);
					if (pr != null) {
						pr.player.setTotal(Integer.valueOf(s.toString()));
					}
				} catch (NumberFormatException e) {
					Log.e("ERROR",
							"error reading double value: " + s.toString());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		pr.views.total.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Toast.makeText(getContext(), "Focus changed! To " +
				// v.getTag(), Toast.LENGTH_LONG).show();
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					calculateTotal(pr);
				}
			}
		});
	}

	static void setDiceTextListeners(PlayerRow pr) {
/*
		pr.views.dice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// v.setFocusable(true);
				// v.setClickable(true);
				// v.requestFocus();
			}
		});*/
		pr.views.dice.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					PlayerRow pr = getSelected();
					// Player plr = getItem(PlayerListAdapter.position);
					if (pr != null) {
						pr.player.setDice(Integer.valueOf(s.toString()));
					}
				} catch (NumberFormatException e) {
					Log.e("ERROR",
							"error reading double value: " + s.toString());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					calculateTotal(pr);
				}
			}
		});/*
		pr.views.dice.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Toast.makeText(getContext(), "Focus changed! To " +
				// v.getTag(), Toast.LENGTH_LONG).show();
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					calculateTotal(pr);
				}

			}
		});*/
	}

	public static void calculateTotal(PlayerRow pr) {
		Player plr = pr.player;
		
		Integer num = 0;
		if(plr.getBonus() == null && plr.getDice() == null){
			num = 0;
		} else if (plr.getBonus() == null){
			num = Integer.valueOf(plr.getDice());
		} else if (plr.getDice() == null){
			num = Integer.valueOf(plr.getBonus());
		} else {
			num = Integer.valueOf(plr.getBonus()) + Integer.valueOf(plr.getDice());
		}
		plr.setTotal(num);
		pr.views.total.setText(plr.getTotal().toString());
	}

	static void setBonusTextListeners(PlayerRow pr) {
/*
		pr.views.bonus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// v.setFocusable(true);
				// v.setClickable(true);
				// v.requestFocus();
			}
		});
		*/
		pr.views.bonus.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					PlayerRow pr = getSelected();
					// Player plr = getItem(PlayerListAdapter.position);
					if (pr != null) {
						pr.player.setBonus(Integer.valueOf(s.toString()));
					}
				} catch (NumberFormatException e) {
					Log.e("ERROR",
							"error reading double value: " + s.toString());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				// playerViews.total.setText(Integer.valueOf(playerViews.bonus.getText().toString()
				// + playerViews.dice.getText().toString()));
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					calculateTotal(pr);
				}
			}
		});
		/*
		pr.views.bonus.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Toast.makeText(getContext(), "Focus changed! To " +
				// v.getTag(), Toast.LENGTH_LONG).show();
				PlayerRow pr = getSelected();
				// Player plr = getItem(PlayerListAdapter.position);
				if (pr != null) {
					calculateTotal(pr);
				}

			}
		});*/
	}

	public int getCount() {

		return items.size();

	}

	public long getItemId(int position) {

		return position;

	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		PlayerListAdapter.index = index;
	}

	public static PlayerRow getSelected() {
		return selected;
	}

	public void setSelected(PlayerRow selected) {
		PlayerListAdapter.selected = selected;
	}

	public List<PlayerRow> getItems() {
		return items;
	}

	public void setItems(List<PlayerRow> items) {
		PlayerListAdapter.items = items;
	}

	private void refreshHitpointsText(PlayerRow plr, View v) {
		String str = v.getResources().getString(R.string.hitpoints).toString()
				+ " " + plr.player.getHitpoints().toString();
		plr.views.hitpoints.setText(str);
	}

	public void setSelectedPosition(int pos) {
		selectedPos = pos;
		// inform the view of this change
		notifyDataSetChanged();
	}

	public int getSelectedPosition() {
		return selectedPos;
	}

}
