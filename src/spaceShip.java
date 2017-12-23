import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Rectangle;

import java.awt.Toolkit;
import java.util.Vector;

public class spaceShip {
	
	
	public static final double SHOT_FREQUENCY = 100; // BULLET FREQUENCY;
	public static final double SPEED = 300; //px per second
	public static final int  SPACESHIP_WIDTH = 67;
	public static final int SPACESHIP_HEIGHT = 60;
	public static int scoreCard = 0;
	
	//health
	public static final int MAX_HEALTH = 300;
	public static boolean alive = true;
	
	//sound
	String s1 = "bullet_sound.wav";
	String s2 = "rifleshot.wav";
	private Sound u;

	private static double x;
	private static double y;

	private double maxX; // screenwidth;
	private int health = MAX_HEALTH;

	//SPACE SHIP.PNG
    Image img;
	
	public spaceShip (double x_, double y_, int maxX){
		
		x = x_;
		y = y_;
		this.maxX = maxX;
		img = Toolkit.getDefaultToolkit().getImage("spaceShip.png");
		u = new Sound(s2);
		
	}
	
	public static double getY(){
		return y;
	}
	
	public static double getX(){
		return x;
	}
	
	public void moveRight(double dt){
		
		x += dt*SPEED;
		
		if (x > maxX){
			x = maxX-10;
		}
		
	}
	
	public void moveLeft(double dt){
		
		x -= dt*SPEED;
		
		if (x < 0){
			x = 0;
		}
		
	}
	public void moveUp(double dt){
//		x -= dt*SPEED;
		
//		y -= SPEED*dt;
//		y -= 5;
//	screenHeight -= SPEED + dt;

	}
	public void moveDown(double dt){
//		x -= dt*SPEED;
		y += SPEED;
		if (x < 0){
			x = 0;
		}
	}
	public void hit(){
		health -= 2;
	}

	public void shootBullets(Vector<Plasma> plasma){
		plasma.add(new Plasma((int)getX()+43,(int) getY()-7));
		u.play();
	}
	public boolean hitBy(Bullet b){
		if (this.getBounds().intersects(b.getBounds())){
//		if (this.getBounds().contains(new Point((int)b.getX(), (int) b.getY()))){
		
			return true;
		}
		return false;
	}
	public void setHealth(int moreHealth){
		health +=100;
	}

	public int getHealth(){
		return health;
	}

	public Rectangle getBounds(){
        return new Rectangle((int)x+15,(int) y, SPACESHIP_WIDTH,SPACESHIP_HEIGHT);
	}

	public void hitByAsteroids() {
		health -= 25;
		if(health==0){
			SetCurrenStateDead();
		}
		
	}
	public String ToStringGetScoreCard(){
		return Integer.toString(scoreCard);
	}
	public void SetCurrenStateDead(){
		alive = false;
	}

	public void draw(Graphics g){
		
		g.setColor(Color.MAGENTA);
		g.drawImage(img,(int) x + 20,(int)y, null);
//		g.drawRect((int)x+15,(int) y, 68, 60);
		
		//HEALTH BAR
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(getHealth()),(health*100) / MAX_HEALTH+20 , 20);
		g.fillRect(10, 10, (health*100) / MAX_HEALTH, 10);
		
		//SCORE CARD
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		g.drawString(ToStringGetScoreCard(), 10, 40);

		

	}
}
