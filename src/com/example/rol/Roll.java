package com.example.rol;

import java.util.Comparator;

class Roll implements Comparable<Roll>,Comparator<Roll> {
	private Integer result;
	private Integer dicemax;
	
	public Roll(){
		result = 0;
		dicemax = 0;
	}
	
	public Roll(int dicemax){
		this.dicemax = dicemax;
		result = roll();
	}

	public int roll () {
		return result = Math.round((int)(1 + (Math.random()*(dicemax))));
	}

	@Override
	public int compareTo(Roll o) {
		// TODO Auto-generated method stub
		return dicemax.compareTo(o.getDicemax());
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getDicemax() {
		return dicemax;
	}

	public void setDicemax(Integer dicemax) {
		this.dicemax = dicemax;
	}

	@Override
	public int compare(Roll o1, Roll o2) {
		// TODO Auto-generated method stub
		int cmp = Integer.compare(o1.getDicemax(), o2.getDicemax());
		return cmp;
	}
}