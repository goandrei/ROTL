package rotl.simulate;

import rotl.entities.SoldierType;

public final class SoldierInfoArena {
	
	 private final int life;
	 private final int armor;
	 private final int attack;
	 private final int gold;
	 private final SoldierType soldierType;
	 
	 private SoldierInfoArena(int life, int armor, int attack, int gold, SoldierType soldierType) {
		 
		 this.life = life;
		 this.armor = armor;
		 this.attack = attack;
		 this.gold = gold;
		 this.soldierType = soldierType;
	 }
	 
	 public int getLife() {
		 return this.life;
	 }
	 
	 public int getArmor() {
		 return this.armor;
	 }
	 
	 public int getAttack() {
		 return this.attack;
	 }
	 
	 public int getGold() {
		 return this.gold;
	 }
	 
	 public SoldierType getSoldierType() {
		 return this.soldierType;
	 }
	 
	 public static Builder builder() {
		 return new Builder();
	 }
	 
	 public static final class Builder {
		 
		 private int life;
		 private int armor;
		 private int attack;
		 private int gold;
		 private SoldierType soldierType = null;
		 
		 private Builder() {}
		 
		 public Builder withLife(int life) {
			 this.life = life;
			 return this;
		 }
		 
		 public Builder withArmor(int armor) {
			 this.armor = armor;
			 return this;
		 }
		 
		 public Builder withAttack(int attack) {
			 this.attack = attack;
			 return this;
		 }
		 
		 public Builder withGold(int gold) {
			 this.gold = gold;
			 return this;
		 }
		 
		 public Builder withSoldierType(SoldierType soldierType) {
			 this.soldierType = soldierType;
			 return this;
		 }
		 
		 public SoldierInfoArena build() {
			 return new SoldierInfoArena(life, armor, attack, gold, soldierType);
		 }
	 }
}
