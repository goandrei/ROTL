package rotl.entities;

public interface GameEntity {
	
	int dealDamage();
	void takeDamage(int damage);
	boolean isDead();
}
