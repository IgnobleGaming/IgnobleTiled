package skill;

public class Skill {

	private String name;
	
	private int exp = 0;
	
	private int maxExp = 100;
	
	private int level = 0;
	
	private int maxLevel = 100;

	public String getSkillName() {
		return name;
	}

	/** Add some experience to the skill */
	public void addExp(int amt) {
		exp += amt;
	}
	
	/** Gets the Skills experience */
	public int getExp(){
		return exp;
	}
	
	/**  Sets Experience for Skill */
	public void setExp(int amt){
		exp = amt;
	}
}
