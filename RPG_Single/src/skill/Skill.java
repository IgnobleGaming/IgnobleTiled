package skill;

public class Skill {
	
	private String name;
	
	private static Level level;
	
	 public String getSkillName(){
		 return name;
	 }
	 
	 public static Level getLevelSkill(){
		 return level;
	 }
	 
	 public static void addExp(int amt){
		 Level.addExp(amt);
	 }
	 
	 public int getLevel(){
		 return level.getLevel();
	 }
}
