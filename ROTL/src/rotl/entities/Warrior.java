package rotl.entities;

import java.util.Random;

public class Warrior extends Soldier {
	
	public Warrior(int _life, int _armor, int _attack, int _miss, int _dodge) {
		
		super(_life, _armor, _attack, _miss, _dodge);
	}
	
	public Warrior() {
		
		this(0, 0, 0, 0, 0);
	}
	
	@Override
	public void takeDamage(int damage) {
		
		if (!this.isDead()) {
			
			if (new Random().nextInt(101) <= this.dodgeRate)
				return;
				
			int armorDamage = damage / 2;
			int lifeDamage = damage - armorDamage;
				
			if (armorDamage > this.armor) {
					
				lifeDamage += (armorDamage - this.armor);
				armorDamage = this.armor;
			}
				
			int remainedArmor = this.armor - armorDamage;
			int remainedLife = Integer.max(this.life - lifeDamage, 0);
				
			this.setArmor(remainedArmor);
			this.setLife(remainedLife);
				
			/** Ability **/
			if (this.isDead()) {
					
				remainedLife = this.armor / 2;
				remainedArmor = this.armor - remainedLife;
					
				this.setArmor(remainedArmor);
				this.setLife(remainedLife);
			}
		}
	}
	
	@Override
	public int dealDamage() {
		
		if (!this.isDead()) {
			
			if (new Random().nextInt(101) <= this.missRate)
				return 0;
			
			return this.attack;
		}
		
		return 0;
	}
}
