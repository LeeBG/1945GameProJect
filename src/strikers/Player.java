package strikers;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Player extends JLabel {
//implements Initable, �̹����ϱ� ��, �̹����ϱ� imp���ص� ��

	private Player player = this;
	private final static String TAG = "Player: ";
	ImageIcon icPlayerR;
	private ImageIcon icPlayerL;
	private ImageIcon icPlayerU;
	private ImageIcon icPlayerD;
	private int x=200;
	private int y=480;
	
	private int sizeX=79; //�ӽ� �ڵ�
	private int sizeY=69; //�ӽ� �ڵ�
	
	private int lifecount;//�ӽ� �ڵ�
	
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

//�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�
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
							System.out.println("x ��ǥ: " + x);
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
		System.out.println("x ��ǥ: " + x);

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
		System.out.println("y ��ǥ: " + y);
		
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
		System.out.println("y ��ǥ: " + y);
		
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