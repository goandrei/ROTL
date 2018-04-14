package rotl.player;

public class Player {

	private int gold;

	/** Getters **/

	public int getGold() {

		return this.gold;
	}

	/** Setters **/

	public void setGold(int _gold) {

		_gold = Integer.max(_gold, 0);
		this.gold = _gold;
	}
}
