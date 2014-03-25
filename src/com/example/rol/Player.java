package com.example.rol;

import java.util.Comparator;

public class Player implements Comparator<Player>, Comparable<Player> {

	// PlayerViews views;

	//private String hitDice = "";
	private Integer hitDice = 0;
	
	private String damage = "";
	private String salvation = "";
	private String treasure = "";
	private String exppoints = "";

	private String moral = "";
	private Integer alignment = 0;
	private String movement = "";
	
	private String name = "";

	private Integer dice = 0;
	private Integer bonus = 0;
	private Integer total = 0;

	private Integer hitpoints = 0;
	private Integer armorclass = 0;
	private Integer attackbonus = 0;
/*
	private String dice = "";
	private String bonus = "";
	private String total = "";
	private String hitpoints = "";
	private String armorclass = "";
	private String attackbonus = "";
*/

	private Integer action1 = 0;
	private Integer action2 = 0;

	public Player() {
		this.setName("");
		this.setBonus(0);
		this.setDice(0);
		this.setTotal(0);
		this.setArmorclass(0);
		this.setAttackbonus(0);
		this.setHitpoints(0);
		this.setAction1(0);
		this.setAction2(0);
		this.setAlignment(0);
		this.setMoral("");
		this.setMovement("");
		this.setTreasure("0gp");
		this.setExppoints("0xp");
		this.setDamage("");
		this.setHitDice(0);
		this.setSalvation("");
		// views = new PlayerViews();
	}

	public Player(String name, Integer dice, Integer bonus, Integer total) {
		this.setName(name);
		this.setBonus(bonus);
		this.setDice(dice);
		this.setTotal(total);
		this.setArmorclass(0);
		this.setAttackbonus(0);
		this.setHitpoints(0);
		this.setAction1(0);
		this.setAction2(0);
		this.setAlignment(0);
		this.setMoral("");
		this.setMovement("");
		this.setTreasure("0gp");
		this.setExppoints("0xp");
		this.setDamage("");
		this.setHitDice(0);
		this.setSalvation("");
		// views = new PlayerViews();
	}

	public Player(String name, Integer dice, Integer bonus, Integer total,
			Integer hitpoints, Integer armorclass, Integer attackbonus,
			Integer action1, Integer action2, Integer alignment) {
		this.setName(name);
		this.setBonus(bonus);
		this.setDice(dice);
		this.setTotal(total);
		this.setArmorclass(armorclass);
		this.setAttackbonus(attackbonus);
		this.setHitpoints(hitpoints);
		this.setAction1(action1);
		this.setAction2(action2);
		this.setAlignment(alignment);
		this.setMoral("");
		this.setMovement("");
		this.setTreasure("0gp");
		this.setExppoints("0xp");
		this.setDamage("");
		this.setHitDice(0);
		this.setSalvation("");
		// views = new PlayerViews();
	}
	public Player(String name, Integer dice, Integer bonus, Integer total,
			Integer hitpoints, Integer armorclass, Integer attackbonus,
			Integer action1, Integer action2, Integer alignment, Integer hitdice,
			String damage, String movement, String moral, String salvation,
			String treasure, String exppoints) {
		this.setName(name);
		this.setBonus(bonus);
		this.setDice(dice);
		this.setTotal(total);
		this.setArmorclass(armorclass);
		this.setAttackbonus(attackbonus);
		this.setHitpoints(hitpoints);
		this.setAction1(action1);
		this.setAction2(action2);
		this.setAlignment(alignment);
		this.setHitDice(hitdice);
		this.setDamage(damage);
		this.setMovement(movement);
		this.setMoral(moral);
		this.setSalvation(salvation);
		this.setTreasure(treasure);
		this.setExppoints(exppoints);
		// views = new PlayerViews();
	}

	public void sum1HP() {
		Integer hp = Integer.valueOf(hitpoints);
		hitpoints = hp+1;
	}

	public void quit1HP() {
		Integer hp = Integer.valueOf(hitpoints);
		hitpoints = hp-1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDice() {
		return dice;
	}

	public void setDice(Integer dice) {
		this.dice = dice;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

	@Override
	public int compareTo(Player o) {
		return total.compareTo(o.total);
	}

	@Override
	public int compare(Player o1, Player o2) {
		int resultado = 0;
		if (Integer.valueOf(o1.getTotal()) < Integer.valueOf(o2.getTotal())) {
			resultado = -1;
		} else if (Integer.valueOf(o1.getTotal()) > Integer.valueOf(o2.getTotal())) {
			resultado = 1;
		}
		return resultado;
	}

	public Integer getAction1() {
		return action1;
	}

	public void setAction1(Integer action1) {
		this.action1 = action1;
	}

	public Integer getAction2() {
		return action2;
	}

	public void setAction2(Integer action2) {
		this.action2 = action2;
	}

	public Integer getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(Integer hitpoints) {
		this.hitpoints = hitpoints;
	}

	public Integer getArmorclass() {
		return armorclass;
	}

	public void setArmorclass(Integer armorclass) {
		this.armorclass = armorclass;
	}

	public Integer getAttackbonus() {
		return attackbonus;
	}

	public void setAttackbonus(Integer attackbonus) {
		this.attackbonus = attackbonus;
	}

	public Integer getHitDice() {
		return hitDice;
	}

	public void setHitDice(Integer hitDice) {
		this.hitDice = hitDice;
	}

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getSalvation() {
		return salvation;
	}

	public void setSalvation(String salvation) {
		this.salvation = salvation;
	}

	public String getMoral() {
		return moral;
	}

	public void setMoral(String moral) {
		this.moral = moral;
	}

	public String getTreasure() {
		return treasure;
	}

	public void setTreasure(String treasure) {
		this.treasure = treasure;
	}

	public String getExppoints() {
		return exppoints;
	}

	public void setExppoints(String exppoints) {
		this.exppoints = exppoints;
	}

	public Integer getAlignment() {
		return alignment;
	}

	public void setAlignment(Integer alignment) {
		this.alignment = alignment;
	}

}