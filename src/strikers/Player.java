package strikers;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	//private final static String TAG = "Player: ";
	
	private Player player = this;
	ImageIcon icPlayerR;
	private ImageIcon icPlayerL;
	private ImageIcon icPlayerU;
	private ImageIcon icPlayerD;
	private int x;
	private int y;
	
	private int sizeX;
	private int sizeY;
	
	private int lifecount;//�ӽ� �ڵ�
	
	public boolean isRight, isLeft, isUp, isDown;

	public Player() {
		init();
	
	}
	
	public void init() {
		x=200;
		y=480;
		sizeX=79; //�ӽ� �ڵ�
		sizeY=69; //�ӽ� �ڵ�
		
		isRight = false;
		isLeft = false;
		isUp = false;
		isDown = false;
		
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
		//System.out.println(TAG + "moveRight");
		

		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerR);
					isRight = true;
					while (isRight) {
						if (x < StrikersApp.XAXIS - 87) { //StrikersApp.X = 500
							//System.out.println("x ��ǥ: " + x);
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
		//System.out.println(TAG + "moveLeft");
		//System.out.println("x ��ǥ: " + x);

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
		//System.out.println(TAG + "moveUp");
		//System.out.println("y ��ǥ: " + y);
		
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
		//System.out.println(TAG + "moveDown");
		//System.out.println("y ��ǥ: " + y);
		
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

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
	
}