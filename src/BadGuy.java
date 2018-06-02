import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Vector;

public class BadGuy extends Enemies {

	public static final int WIDTH = 75	;
	public static final int HEIGHT = 38;

	public int initial_speed_x = 100; // px per second:
	public int initial_speed_y = 15; // px per second:
	public double vertical_speed_increase = 0.75;
	public double speed_increase =1.01;
	public double initial_bullet_frequency = 1; // BULLETS / UPDATE EVERY 10TH OF SECOND SHOOT A BULLET
	public static double frequency_increase = .6 ; //speed changed to .6


	private double dx, dy;
	private int screenWidth;

	private double bulletFrequency;
	private Image img1;
	private Image img2;


	public BadGuy(int x, int y,int screenWidth,boolean left) {
		
		super(x, y, WIDTH, HEIGHT);
		this.screenWidth = screenWidth;
		img1 = Toolkit.getDefaultToolkit().getImage("space_red.png");
		img2 = Toolkit.getDefaultToolkit().getImage("space_red1.png");
		
		dx = initial_speed_x;
		dy = vertical_speed_increase;
//		if (left) 
//			dx = - initial_speed_x;
//		else 
//			dx = initial_speed_x;
//
//		dy = initial_speed_y;
		bulletFrequency = initial_bullet_frequency;
	}
	public void update(double dt, Vector<Bullet> bullets){

		if (isAlive()){
			
			x += dx*dt;
			y += dy*dt;

			if (x > screenWidth || x < 0){
//				x-= initial_speed_x * dt;
				movingLeftRight();
			}

			if(y - Camera2D.y > -100){
				
				if (Math.random()<bulletFrequency){
					bullets.add(new Bullet(x,y));
				}
				
				bulletFrequency *= frequency_increase;
			}
					
		}
		
		else {
			y+=12;
		}

	}
	
	private void movingLeftRight() {
		dx *= -speed_increase;

		dy *= vertical_speed_increase;

	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.RED);

		if (isAlive()){
		g.drawImage(img1,(int)x,(int)y-(int)Camera2D.y, null);
		}
		else {
			g.drawImage(img2,(int)x,(int) y-(int)Camera2D.y, null);	
		}

	}


}
