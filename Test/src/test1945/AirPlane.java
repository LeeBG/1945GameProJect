package test1945;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class AirPlane extends JLabel{
	private AirPlane airPlane = this;
	private PlayerPlane playerPlane;
	private int lifecount;
	private int sizeX,sizeY;
	private static final String TAG = "Player: ";
	public ImageIcon playerIcon;
	public int x;
	public int y;

	public boolean isRight; // 오른쪽으로 움직이는지 아닌지의 상태
	public boolean isLeft; // 왼쪽으로 움직이는지 아닌지의 상태
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
//		new Missile(playerPlain);		//x,y좌표를 받아서 적방향으로 일직선 날아가는 미사일 공격
	}
	public void shotToPlayer() {
//		new Missile(enemyplane); 		//x,y좌표를 받아서 적방향으로 일직선 날아가는 미사일 공격
	}
	public void crush() {				//충돌
							
	}
}
