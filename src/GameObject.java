import java.awt.Rectangle;

public class GameObject {
	
	protected int x;
	protected int y;
	private boolean alive;
	private final int width, height;
	
	public GameObject(int x, int y, int width, int height){
		this. x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Rectangle getBounds(){		
		return new Rectangle (x,y-(int)Camera2D.y,width,height);	
	}
	


}
