import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class healthPack {
	
	public int x;
	public int y;
	public final int WIDTH = 49;
	public final int HEIGHT = 63;
	Image img;
	
	public healthPack(int x, int y){
		
		this.x = x;
		this.y = y;
		img = Toolkit.getDefaultToolkit().getImage("Reach_Health_Pack.png");
		
	}

	public Rectangle getBounds(){
		
		return new Rectangle (x,y-(int)Camera2D.y,WIDTH,HEIGHT);
		
	}
	
	public void draw (Graphics g){
		
		g.drawImage(img, x, y-(int)Camera2D.y ,null);
		
	}
	
	
}
