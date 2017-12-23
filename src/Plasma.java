import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;

public class Plasma {
	public  int x;
	public  int  y;
	public Image img;
	public static final int  WIDTH = 45;
	public static final int HEIGHT = 35;
	LinkedList<Plasma> bullet = new LinkedList<Plasma>();
	public static int noOfPlasma = 0;
	public boolean visible = true;

	
	public Plasma (int x, int y){
		this.x = x;
		this.y = y;
		this.img = Toolkit.getDefaultToolkit().getImage("laser.png");
		noOfPlasma+=1;

		
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void addBullet(Plasma e){
		bullet.add(e);
	}
	public int returnBulletSize(){
		return bullet.size();
	}
	public void removeBullet(){
		bullet.remove();
	}
	public void update(double dt){

		if (isVisible()){
		this.y -=10;
	
		}

		

	}
	public boolean isVisible(){
		
		if (getY()> 20){
			visible = true;
		}
		else {
			visible = false;
		}
		return visible;
	}


	public Rectangle getBounds(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void draw(Graphics g){

		g.setColor(Color.green);
		if (isVisible()){
		g.drawImage(img, x, y, null);

		}
		
	}
}
