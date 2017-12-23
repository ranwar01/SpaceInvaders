import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Asteroids extends Enemies {
	
	private int MAX_HEALTH =25;
	private int current_health = MAX_HEALTH;

	public static final int WIDTH = 100;
	public static final int HEIGHT = 108;
	public static double speed = 2.0;
	private Image img;
	private Image img1;

	public int width;
	
	public Asteroids(int x, int y){
		
		super(x, y, WIDTH, HEIGHT);
		img = Toolkit.getDefaultToolkit().getImage("asteroids_stones.png");
		img1 = Toolkit.getDefaultToolkit().getImage("explosion.png");
		
	}
	
	public void setHealth(){
		
		current_health -= 1;
		
	}

	public int getHealth(){
		
		return current_health;
		
	}

	public void update(double dt){

		if(isAlive()){
			y+=speed;
		}
		else{
			y +=10;
		}
		
	}
	
	public void draw(Graphics g){

		if(isAlive()){
			g.drawImage(img, x, y-(int)Camera2D.y, null);
			g.setColor(Color.green);	
		}
		else if (!isAlive()) {
			g.drawImage(img1, x, y-(int)Camera2D.y, null);
		}

	}


}
