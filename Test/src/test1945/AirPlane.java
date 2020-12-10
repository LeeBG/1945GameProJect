package test1945;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class AirPlane extends JLabel{
	private AirPlane airPlane = this;
	
	private int lifecount;
	private int sizeX,sizeY;
	private static final String TAG = "Player: ";
	public ImageIcon playerIcon;
	public int x;
	public int y;

	public boolean isRight; 
	public boolean isLeft; 
	public boolean isUp;
	public boolean isDown;
	
	public AirPlane() {
		lifecount =1;
		isRight = false;
		isLeft = false;
		isUp = false;
		isDown = false;
	}
	public AirPlane(int sizeX, int sizeY) {
		super();
		this.sizeX=sizeX;
		this.sizeY=sizeY;
	}
	public void moveRight() {
	}
	public void moveLeft() {
	}
	public void moveDown() {
	}
	public void moveUp() {
	}
	public void shotToEnemy() {
//		new Missile(playerPlain);		
	}
	public void shotToPlayer() {
//		new Missile(enemyplane); 		
	}
	public void crush() {				
							
	}
}
