import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Vector;

public class Bullet extends Enemies {
	
	public static final int SPEED = 150; //pxls/s
	public Image img;
	public static final int  WIDTH = 10;
	public static final int HEIGHT = 8;
	
	public Bullet(int x, int y){
		
		super(x, y, WIDTH, HEIGHT);
		img = Toolkit.getDefaultToolkit().getImage("laser1.png");
		
	}
	
	public void update(double dt){
		
			y += dt * SPEED;
			
	}

	public void draw(Graphics g){

		g.setColor(Color.ORANGE);
		g.drawImage(img,(int) x,(int) y-(int)Camera2D.y, null);
		
	}
	
}
