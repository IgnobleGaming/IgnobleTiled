package skill;

public class Level {

	/** Amount of Exp for this level */
	private static int experience = 0;

	/***/
	private final static int maxExp = 100;

	/***/
	private static int level = 0;

	/***/
	private int maxLevel = 100;

	public static void addExp(int amt) {
		experience += amt;

		if (experience >= maxExp) {
			levelUp();
		}
	}

	public void removeExp(int amt) {
		experience -= amt;

		if (experience < 0) {
			experience = 0;
		}
	}

	private static void levelUp() {
		level++;

		System.out.println("Ding");
		
		experience = experience % maxExp;
	}

	public int getExp() {
		return experience;
	}

	public void setExp(int amt) {
		experience = amt;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int newLevel) {
		if (newLevel < maxLevel && newLevel >= 0)
			level = newLevel;
	}
}
