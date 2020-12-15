package strikers;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Player extends JLabel {
//implements Initable, 이미지니까 라벨, 이미지니까 imp안해도 됨

	private Player player = this;
	private final static String TAG = "Player: ";
	ImageIcon icPlayerR;
	private ImageIcon icPlayerL;
	private ImageIcon icPlayerU;
	private ImageIcon icPlayerD;
	private int x=200;
	private int y=480;
	
	private int sizeX=79; //임시 코드
	private int sizeY=69; //임시 코드
	
	private int lifecount;//임시 코드
	
	public boolean isRight, isLeft, isUp, isDown = false;

	public Player() {
		icPlayerR = new ImageIcon("image/PLANE1.png");
		icPlayerL = new ImageIcon("image/PLANE1.png");
		icPlayerU = new ImageIcon("image/PLANE1.png");
		icPlayerD = new ImageIcon("image/PLANE1.png");
		setIcon(icPlayerR);
		setSize(sizeX, sizeY);
		setLocation(x, y);
	}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public void moveRight() {
		System.out.println(TAG + "moveRight");
		

		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerR);
					isRight = true;
					while (isRight) {
						if (x < StrikersApp.XAXIS - 87) { //StrikersApp.X = 500
							System.out.println("x 좌표: " + x);
							x++;
							setLocation(x, y);
						}
						try {
							Thread.sleep(StrikersApp.speed2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void moveLeft() {
		System.out.println(TAG + "moveLeft");
		System.out.println("x 좌표: " + x);

		if (isLeft == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerL);
					isLeft = true;
					while (isLeft) {
						if (x >= StrikersApp.ZAXIS) { //StrikersApp.Z = 0
							x--;
							setLocation(x, y);
						}
						try {
							Thread.sleep(StrikersApp.speed2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void moveUp() {
		System.out.println(TAG + "moveUp");
		System.out.println("y 좌표: " + y);
		
		if (isUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerU);
					isUp = true;
					while (isUp) {
						if (y > StrikersApp.ZAXIS) { //StrikersApp.Z = 0
							y--;
							setLocation(x, y);
						}
						try {
							Thread.sleep(StrikersApp.speed2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void moveDown() {
		System.out.println(TAG + "moveDown");
		System.out.println("y 좌표: " + y);
		
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerD);
					isDown = true;
					while (isDown) {
						if (y < StrikersApp.YAXIS - 100) { //StrikersApp.Y = 700
							y++;
							setLocation(x, y);
						}
						try {
							Thread.sleep(StrikersApp.speed2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	
	
}