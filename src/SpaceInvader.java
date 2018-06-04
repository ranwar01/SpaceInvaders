
import java.awt.*;
import java.awt.image.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;
import java.awt.event.*;


import javax.swing.*;
public class SpaceInvader extends JApplet implements KeyListener, Runnable{
	//	public static Thread thread;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int PLAYING = 0;
	public static final int WON = 1;
	public static final int LOST = 2;


	//	background
	public static final int BACKGROUND_HEIGHT = 750;
	public ImageLayer layer2;


	//	private Player player;
	public SpaceShip ship;

	//enemies
	//	public BadGuy bg;
	//level
	public Level level;

	private Vector<BadGuy> badGuys;
	private Vector <Bullet> bullets;
	private Vector <Asteroids> asteroid;
	//	private Vector <BadGuy> badGuysGroup;
	private Vector<HealthPack> healthp;
	private int state = PLAYING;


	// plasma
	private Vector <Plasma> plasma;

	private boolean left, right, up, down;

//	String s = "40_smith_.wav";
	String s = "rifleshot.wav";
	Sound c;


	@Override
	public void init(){

		addKeyListener(this);
		this.setFocusable(true);	
		setSize(1024, 750);
	
		

		c = new Sound(s);

		//background
		layer2 = new ImageLayer("space12.jpg",0.0, 0.0, 1, 1911,1515 , 0);
		// player
		ship = new SpaceShip(getWidth()/2, getHeight()-SpaceShip.SPACESHIP_HEIGHT, getWidth());


		badGuys = new Vector<BadGuy>();
		Random m = new Random();
		//		badGuysGroup = new Vector<BadGuy>();
		healthp = new Vector<HealthPack>();

		for (int j = 0; j < 150; j++){
			int bgX_pos = m.nextInt(this.getWidth()-100);
			int bgY_pos = -m.nextInt(25000);
			BadGuy newBg = new BadGuy(bgX_pos,bgY_pos,getWidth(),false);
			//						System.out.println(bgX_pos + " "+ bgY_pos);
			badGuys.add(newBg);
		}
		//		for (int i = 0; i< badGuys.size(); i++){
		//			if (((badGuys).get(i).getY()<-350)&&badGuys.get(i).getY()>-800){
		//			}
		//		}
		for (int i = 0; i< 5; i++){
			int healthX, healthY;
			healthX = m.nextInt(this.getWidth()-100);
			healthY = -m.nextInt(25000);
			HealthPack hp = new HealthPack(healthX, healthY);
			//			System.out.println(healthX +" " + healthY);
			healthp.add(hp);
		}


		// Asteroids
		Random r = new Random();
		asteroid = new Vector<Asteroids>();
		for (int i = 0; i< 150; i++){
			int x_pos = r.nextInt(getWidth());
			int y_pos = -r.nextInt(50000);
			Asteroids aster = new Asteroids(x_pos,y_pos);
			asteroid.add(aster);
		}

		// weapons
		plasma = new Vector<Plasma>();  // new
		bullets = new Vector<Bullet>();
		//level
		level = new Level();

		new Thread(this).start();
		
		
		
	}

	@Override
	public void paint(Graphics graphics){
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = image.getGraphics();

		layer2.draw(g);
		level.draw(g);
		ship.draw(g);

		for(int i = 0; i < plasma.size(); i++){
			plasma.get(i).draw(g);
		}


		for(int i = 0; i < badGuys.size(); i++){
			badGuys.get(i).draw(g);
		}
		
		for(int i = 0; i < healthp.size(); i++){
			healthp.get(i).draw(g);
		}

		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).draw(g);
		}
		
		for(int i = 0; i < asteroid.size(); i++){
			asteroid.get(i).draw(g);
		}
		for(int i = 0; i < healthp.size(); i++){
			healthp.get(i).draw(g);
		}
		if (state ==WON){
			g.drawString("You Win!", getWidth()/2-30, getHeight()/2+6);
		}
		else if (state == LOST){
			g.drawString("You Lose", getWidth()/2 -30, getHeight()/2 + 6);
		}


		//DRAW THE BUFFERED
		graphics.drawImage(image,0,0, null);

	}

	// dt in seconds
	public void update(double dt){
		if (state == PLAYING){

			if (left){
				ship.moveLeft(dt);
			}

			if (right){
				ship.moveRight(dt);
			}

			if (up){
				Camera2D.moveUpBy(10);
			}
			
			if (down){}


			for(int i = 0; i < badGuys.size(); i++){
				badGuys.get(i).update(dt, bullets);
			}


			for(int i = 0; i < bullets.size(); i++){
				bullets.get(i).update(dt);
			}
			
			for(int i = 0; i < asteroid.size(); i++){
				asteroid.get(i).update(dt);
			}
			
			for(int i = 0; i < plasma.size(); i++){
				plasma.get(i).update(dt);
			}

			// collission detection
			level.generateLevel();
			testForBulletCollisions();
			checkForAsteroidColission();
			checkForplayerColission();
			checkForplayerHealthPack();

			if (hasWon()){
				state = WON;
			}
			else if (hasLost()){
				state = LOST;
			}
		}
	}
	private void checkForplayerColission() {

		for (int i = 0; i< asteroid.size(); i++){
			
			if (( asteroid.get(i).getBounds()).intersects(ship.getBounds())){

				if (asteroid.get(i).isAlive()){
					ship.hitByAsteroids();
					c.play();
					asteroid.get(i).setAlive(false);
					//					asteroid.remove(i);
					i--;
				}
				
			}

		}
	}
	
	private void checkForplayerHealthPack(){
		for (int i = 0; i < healthp.size(); i++){
			
			if (ship.getBounds().intersects((healthp.get(i).getBounds()))){
				ship.setHealth(100);
				healthp.remove(i);
			}
			
		}
	}

	public void checkForAsteroidColission(){
		top: for (int i =0; i < plasma.size(); i++){
			for(int j =0; j< asteroid.size();j++){

				if(asteroid.get(j).hitBy(plasma.get(i))&&(plasma.get(i).isVisible())){
					asteroid.get(j).setAlive(false);
					SpaceShip.scoreCard++;
					c.play();
					plasma.remove(i);
					i--;
					continue top;
				}

			}

		}
	}

		// if badguy hits by plasma
	public void testForBulletCollisions(){

		top:	for (int i = 0; i <plasma.size();i++){
			for (int j = 0; j < badGuys.size(); j++){

				if ((badGuys.get(j).hitBy(plasma.get(i)) )&&(plasma.get(i).isVisible())) {
					badGuys.get(j).setAlive(false);
					c.play();
					SpaceShip.scoreCard++;
					plasma.remove(i);
					j--;
					i--;
					continue top;
				}
			}

		}
	// if player gets hit by bullets
	for (int i = 0; i< bullets.size(); i++){
		
		if (ship.getBounds().intersects(bullets.get(i).getBounds())){
			c.play();
			ship.hit();
	
		}
	}


	}
	public boolean hasWon(){
		
		if (SpaceShip.scoreCard >= 200){
			return true;
		}
		
		else {
			return false;
		}

	}
	public boolean hasLost(){
		
		if (ship.getHealth() < 0){
			return true;
		}
		
		else {
			return false;
		}

	}

	@Override
	public void run() {

		long time = System.nanoTime();

		while (true){

			try {Thread.sleep(16);} catch(Throwable t) {}

			double dt = (System.nanoTime()-time)*1E-9;  // update every sixteenth of a second;
			//			System.out.println("the time is " + dt);
			time = System.nanoTime();
			update(dt);
			repaint();			
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyChar();
		if (keyCode == KeyEvent.VK_SPACE){
			//		player.shoot(bullets);
			//		ship.shoot(bullets);
			ship.shootBullets(plasma);
			//		player.shoot()

		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if(keyCode == KeyEvent.VK_LEFT){
			left = true;
		}

		if(keyCode == KeyEvent.VK_RIGHT){
			right = true;
		}


		if(keyCode == KeyEvent.VK_UP){
			up = true;
		}

		if (keyCode == KeyEvent.VK_DOWN){
			down = true;
		}
//		if (keyCode == KeyEvent.VK_S){
//			start = true;
//		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if(keyCode == KeyEvent.VK_LEFT){
			left = false;
		}

		if(keyCode == KeyEvent.VK_RIGHT){
			right = false;
		}

		if(keyCode == KeyEvent.VK_UP){
			up = false;
		}

		if (keyCode == KeyEvent.VK_DOWN){
			down = false;
		}
	}

}
