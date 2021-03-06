import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class HealthPack {
	
	public int x;
	public int y;
	
	//* Width and the height of the Image changed to static will reduce memory foot print  *//
	private static final int WIDTH = 49;  
	private static final int HEIGHT = 63;
	private Image img;
	
	public HealthPack(int x, int y){
		
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
