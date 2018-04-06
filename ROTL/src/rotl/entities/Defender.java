package rotl.entities;

import java.util.Random;

public class Defender extends Soldier {
	
	public Defender(int _life, int _armor, int _attack, int _miss, int _dodge) {
		
		super(_life, _armor, _attack, _miss, _dodge);
	}
	
	public Defender() {
		
		this(0, 0, 0, 0, 0);
	}
	
	@Override
	public void takeDamage(int damage) {
		
		if (!this.isDead()) {
			
			if (new Random().nextInt(101) <= this.dodgeRate)
				return;
			
			int armorDamage = (2 * damage) / 3;
			int lifeDamage = damage - armorDamage;
			
			if (armorDamage > this.armor) {
				
				lifeDamage += (armorDamage - this.armor);
				armorDamage = this.armor;
			}
			
			int remainedArmor = this.armor - armorDamage;
			int remainedLife = Integer.max(this.life - lifeDamage, 0);
			
			this.setArmor(remainedArmor);
			this.setLife(remainedLife);
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
