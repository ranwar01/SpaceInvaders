import java.awt.Rectangle;

public class Enemies {
	
	protected int x = 0;
	protected int y = 0;
	private boolean alive;
	private final int WIDTH, HEIGHT;

	public Enemies ( int x, int y, int WIDTH, int HEIGHT) {
		this. x = x;
		this.y = y;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		alive = true;
		
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
	
	public Rectangle getBounds(){
		return new Rectangle(x, y-(int)Camera2D.y, WIDTH, HEIGHT);	
	}
	
	public boolean hitBy(Plasma p){
		
		if (this.getBounds().intersects(p.getBounds())){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void setAlive(boolean b){	
		alive = b;
	}
	
	public boolean isAlive(){	
		return alive;
	}
	
	public int getWidth(){
		return WIDTH;	
	}
	
	public int getHeight(){
		return HEIGHT;
	}

}
	
	