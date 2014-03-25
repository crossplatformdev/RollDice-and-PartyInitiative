package com.example.rol;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Stats extends Activity {

	private EditText editname;
	private EditText editinitiativebonus;
	private EditText editarmorclass;
	private EditText editattackbonus;
	private EditText edithitpoints;
	
	
	private EditText edithitdice;
	private EditText editmovement;
	private EditText editdamage;
	private EditText editsalvation;
	private EditText editmoral;
	private EditText edittreasure;
	private EditText editexppoints;
	
	private Spinner alignmentSpinner;
	
	

	private PlayerRow pRow;
	//private ArrayAdapter<PlayerRow> adapter;
	private Context context;

	
	public Stats(){
		pRow = PlayerListAdapter.getSelected();
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_stats);
		pRow = PlayerListAdapter.getSelected();
		context = this;
		prepareViews();
		setViewListeners();
		
	}


	private void setViewListeners() {
		setEditNameTextListener(pRow.player);
		setEditInitiativeListeners(pRow.player);
		setEditArmorClassListeners(pRow.player);
		setEditAttackBonusListeners(pRow.player);
		setEditHitpointsListeners(pRow.player);	
		setEditHitDiceListeners(pRow.player);
		setEditMovementListeners(pRow.player);
		setEditHitDamageListeners(pRow.player);
		setEditSalvationListeners(pRow.player);
		setEditMoralListeners(pRow.player);
		setEditTreasureListeners(pRow.player);
		setEditExpPointsListeners(pRow.player);
		setEditAlignmentListeners(pRow.player);
	}


	private void prepareViews() {
		alignmentSpinner =(Spinner)findViewById(R.id.editAlignment);

		ArrayAdapter<CharSequence> alignments = ArrayAdapter.createFromResource(context,
				 R.array.alignment, android.R.layout.simple_spinner_item);
		
		alignments
				.setDropDownViewResource(android.R.layout.simple_spinner_item);

		// Apply the adapter to the spinner
		alignmentSpinner.setAdapter(alignments);
		
		
		edithitdice = (EditText) findViewById(R.id.editHitDice);
		String str = pRow.player.getHitDice().toString();
		if (str!= null) edithitdice.setText(str);
		
		editmovement = (EditText) findViewById(R.id.editMovement);
		str = pRow.player.getMovement().toString();
		if (str!= null) editmovement.setText(str);
		
		editdamage = (EditText) findViewById(R.id.editDamage);
		str = pRow.player.getDamage().toString();
		if (str != null) editdamage.setText(str);
		
		editsalvation = (EditText) findViewById(R.id.editSalvation);
		str = pRow.player.getSalvation().toString();
		if (str != null) editsalvation.setText(str);
		
		editmoral = (EditText) findViewById(R.id.editMoral);
		str = pRow.player.getMoral().toString();
		if (str != null) editmoral.setText(str);
		
		edittreasure = (EditText) findViewById(R.id.editTreasure);
		str = pRow.player.getTreasure().toString();
		if (str != null) edittreasure.setText(str);
		
		editexppoints = (EditText) findViewById(R.id.editExpPoints);
		str = pRow.player.getExppoints().toString();
		if (str!= null) editexppoints.setText(str);
		
		editname = (EditText) findViewById(R.id.editName);
		str = pRow.player.getName().toString();
		if (str!= null) editname.setText(str);

		editinitiativebonus = (EditText) findViewById(R.id.editInitiativeBonus);
		str = pRow.player.getBonus().toString();
		if (str!= null) editinitiativebonus.setText(str);

		editarmorclass = (EditText) findViewById(R.id.editArmorClass);
		str = pRow.player.getArmorclass().toString();
		if (str!= null) editarmorclass.setText(str);

		editattackbonus = (EditText) findViewById(R.id.editAttackBonus);
		str = pRow.player.getAttackbonus().toString();
		if (str!= null) editattackbonus.setText(str);

		edithitpoints = (EditText) findViewById(R.id.editHitpoints);
		str = pRow.player.getHitpoints().toString();
		if (str!= null) edithitpoints.setText(str);
	}

	private void setEditAlignmentListeners(final Player plr) {
		// TODO Auto-generated method stub
		alignmentSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				plr.setAlignment(position);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void setEditExpPointsListeners(final Player plr) {
		// TODO Auto-generated method stub
		editexppoints.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setExppoints(s.toString());
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void setEditTreasureListeners(final Player plr) {
		// TODO Auto-generated method stub
		edittreasure.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setTreasure(s.toString());
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});		
	}

	private void setEditMoralListeners(final Player plr) {
		// TODO Auto-generated method stub
		editmoral.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setMoral(Integer.valueOf(s.toString()).toString());
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void setEditSalvationListeners(final Player plr) {
		// TODO Auto-generated method stub
		editsalvation.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setSalvation(s.toString());
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void setEditHitDamageListeners(final Player plr) {
		// TODO Auto-generated method stub
		editdamage.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setDamage(s.toString());
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void setEditMovementListeners(final Player plr) {
		// TODO Auto-generated method stub
		editmovement.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setMovement(Integer.valueOf(s.toString()).toString());
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void setEditHitDiceListeners(final Player plr) {
		// TODO Auto-generated method stub
		edithitdice.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setHitDice(Integer.valueOf(s.toString()));
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	void setEditNameTextListener(final Player plr) {
		editname.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		editname.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				plr.setName(s.toString());
				// Toast.makeText(getContext(), "Name changed! To " +
				// playerViews.player.getName(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		editname.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Toast.makeText(getContext(), "Focus changed! To " +
				// v.getTag(), Toast.LENGTH_LONG).show();

			}
		});
	}

	void setEditInitiativeListeners(final Player plr) {
		editinitiativebonus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		editinitiativebonus.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					plr.setBonus(Integer.valueOf(s.toString()));
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
		editinitiativebonus
				.setOnFocusChangeListener(new OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// Toast.makeText(getContext(), "Focus changed! To " +
						// v.getTag(), Toast.LENGTH_LONG).show();

					}
				});
	}

	void setEditArmorClassListeners(final Player plr) {
		editarmorclass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		editarmorclass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					plr.setArmorclass(Integer.valueOf(s.toString()));
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
		editarmorclass.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Toast.makeText(getContext(), "Focus changed! To " +
				// v.getTag(), Toast.LENGTH_LONG).show();

			}
		});
	}

	void setEditAttackBonusListeners(final Player plr) {
		editattackbonus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		editattackbonus.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					plr.setAttackbonus(Integer.valueOf(s.toString()));
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
		editattackbonus.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Toast.makeText(getContext(), "Focus changed! To " +
				// v.getTag(), Toast.LENGTH_LONG).show();

			}
		});
	}

	void setEditHitpointsListeners(final Player plr) {
		edithitpoints.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		edithitpoints.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					plr.setHitpoints(Integer.valueOf(s.toString()));
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
		edithitpoints.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Toast.makeText(getContext(), "Focus changed! To " +
				// v.getTag(), Toast.LENGTH_LONG).show();

			}
		});
	}

	// Use onSaveInstanceState(Bundle) and onRestoreInstanceState

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate if the process is
		// killed and restarted.

		Integer i = savedInstanceState.getInt("position");

		savedInstanceState.putString(String.valueOf(i.toString() + "Name"),
				pRow.player.getName());
		savedInstanceState.putInt(String.valueOf(i.toString() + "Dice"),
				pRow.player.getDice());
		savedInstanceState.putInt(String.valueOf(i.toString() + "Bonus"),
				pRow.player.getBonus());
		savedInstanceState.putInt(String.valueOf(i.toString() + "Total"),
				pRow.player.getTotal());
		savedInstanceState.putInt(String.valueOf(i.toString() + "Action1"),
				pRow.player.getAction1());
		savedInstanceState.putInt(String.valueOf(i.toString() + "Action2"),
				pRow.player.getAction2());
		savedInstanceState.putInt(String.valueOf(i.toString() + "Hitpoints"),
				pRow.player.getHitpoints());
		savedInstanceState.putInt(String.valueOf(i.toString() + "ArmorClass"),
				pRow.player.getArmorclass());
		savedInstanceState.putInt(String.valueOf(i.toString() + "AttackBonus"),
				pRow.player.getAttackbonus());
		savedInstanceState.putString(i.toString() + "Moral",
				pRow.player.getMoral());
		savedInstanceState.putString(i.toString() + "Movement",
				pRow.player.getMovement());
		savedInstanceState.putString(i.toString() + "Salvation",
				pRow.player.getSalvation());
		savedInstanceState.putInt(i.toString() + "HitDice",
				pRow.player.getHitDice());
		savedInstanceState.putString(i.toString() + "Damage",
				pRow.player.getDamage());
		savedInstanceState.putString(i.toString() + "Treasure",
				pRow.player.getTreasure());
		savedInstanceState.putString(i.toString() + "ExpPoints",
				pRow.player.getExppoints());
		
		i += 1;

		// etc.
		super.onSaveInstanceState(savedInstanceState);
	}

	// onRestoreInstanceState
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore UI state from the savedInstanceState.
		// This bundle has also been passed to onCreate.
		Integer i = savedInstanceState.getInt("position");

		pRow.player.setName(savedInstanceState.getString(String.valueOf(i
				.toString() + "Name")));
		pRow.player.setDice(savedInstanceState.getInt(String.valueOf(i
				.toString() + "Dice")));
		pRow.player.setBonus(savedInstanceState.getInt(String.valueOf(i
				.toString() + "Bonus")));
		pRow.player.setTotal(savedInstanceState.getInt(String.valueOf(i
				.toString() + "Total")));
		pRow.player.setAction1(savedInstanceState.getInt(String.valueOf(i
				.toString() + "Action1")));
		pRow.player.setAction2(savedInstanceState.getInt(String.valueOf(i
				.toString() + "Action2")));
		pRow.player.setHitpoints(savedInstanceState.getInt(String.valueOf(i
				.toString() + "Hitpoints")));
		pRow.player.setArmorclass(savedInstanceState.getInt(String.valueOf(i
				.toString() + "ArmorClass")));
		pRow.player.setAttackbonus(savedInstanceState.getInt(String.valueOf(i
				.toString() + "AttackBonus")));
		pRow.player.setMoral(savedInstanceState.getString(i.toString() + "Moral"));
		pRow.player.setMovement(savedInstanceState.getString(i.toString() + "Movement"));			
		pRow.player.setSalvation(savedInstanceState.getString(i.toString() + "Salvation"));
		pRow.player.setHitDice(savedInstanceState.getInt(i.toString() + "HitDice"));			
		pRow.player.setDamage(savedInstanceState.getString(i.toString() + "Damage"));			
		pRow.player.setTreasure(savedInstanceState.getString(i.toString() + "Treasure"));			
		pRow.player.setExppoints(savedInstanceState.getString(i.toString() + "ExpPoints"));
	}
}
