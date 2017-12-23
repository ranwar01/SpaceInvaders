import java.awt.Graphics;

public class Level {
	
	public static int level=1;
	
	public void generateLevel() {
	
		if (Camera2D.y <=-10000){
			if (Camera2D.y % -10000 == 0){			
			 setLevel();
			}	
		}	
		
	}
	
	public void setLevel(){		
		 level++;
		 Asteroids.speed++;
	}
	
	public static int getLevel(){
		return level;
	}
	
	public static  int getLevelInt(){
		return level;
	}
	
	public void draw(Graphics g){
		 g.drawString(Integer.toString(getLevel()), 25, 730);
		 g.drawString(Double.toString(Camera2D.y), 25, 710);
	}
	
}
