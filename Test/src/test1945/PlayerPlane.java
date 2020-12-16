package test1945;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PlayerPlane extends AirPlane {

	public PlayerPlane playerPlane = this;
	private static final String TAG = "Player: ";
	public ImageIcon playerIcon;
	private int lifecount;
	private int x; 
	private int y; 
	private int sizeX; 
	private int sizeY; 

	public boolean isRight; 
	public boolean isLeft; 
	public boolean isUp;
	public boolean isDown;
	public boolean ismove;				//기체가 움직일 수 있는 상태라면 true

	public void setLifecount(int lifecount) {
		this.lifecount=lifecount;
	}
	public int getLifecount() {
		return lifecount;
	}
	public void setSizeX(int sizeX) {
		this.sizeX=sizeX;
	}
	public void setSizeY(int sizeY) {
		this.sizeY=sizeY;
	}
	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	
	public PlayerPlane(String Plane) {							//비행기의 종류를 인수로 받아서 게임 실행
		playerIcon = new ImageIcon("images/"+Plane+".png");
		init();
	}

	private void init() {
		ismove = true;
		lifecount = 3;
		x = 305;
		y = 820;
		sizeX = 70;
		sizeY = 59;
		setIcon(playerIcon);
		setLocation(x, y);
		setSize(sizeX, sizeY);
		isRight = false;
		isLeft = false;
		isUp = false;
		isDown = false;
	}

	public void moveLeft() {
		System.out.println(TAG + "왼쪽으로");
		if (isLeft == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isLeft = true;
					while (isLeft && ismove) {
						x--;
						setLocation(x, y);
						try {
							Thread.sleep(5);
							System.out.println(TAG+"X:"+getX()+"Y:"+getY());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}
			}).start();
		}
	}

	public void moveRight() {
		System.out.println(TAG + "오른쪽으로");
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isRight = true;
					while (isRight && ismove) {
						x++;
						setLocation(x, y);
						try {
							Thread.sleep(5);
							System.out.println(TAG+"X:"+getX()+"Y:"+getY());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void moveUp() {
		System.out.println(TAG + "위로");
		if (isUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isUp = true;
					while (isUp && ismove) {
						y--;
						setLocation(x, y);
						try {
							Thread.sleep(5);
							System.out.println(TAG+"X:"+getX()+"Y:"+getY());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}
			}).start();
		}
	}

	public void moveDown() {
		System.out.println(TAG + "아래로");
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while (isDown && ismove) {
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(5);
							System.out.println(TAG+"X:"+getX()+"Y:"+getY());
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}).start();
		}
	}

//	public void shotToEnemy() {
//		new Missile(this);		//미구현
//	}

}
