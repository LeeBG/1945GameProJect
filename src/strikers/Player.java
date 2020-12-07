package strikers;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
//implements Initable{ //이미지니까 라벨, 이미지니까 imp안해도 됨

	public Player player = this;
	public final static String TAG = "Player: ";

	public ImageIcon icPlayerR, icPlayerL, icPlayerU, icPlayerD;
	public int x = 55;
	public int y = 535;
	
	public boolean isRight = false;
	public boolean isLeft = false;
	public boolean isUp = false;
	public boolean isDown = false;

	public int floor = 1; // 535, 415, 295, 177 enum

	public Player() {
		icPlayerR = new ImageIcon("image/playerR.png");
		icPlayerL = new ImageIcon("image/playerL.png");
		icPlayerU = new ImageIcon("image/playerU.png");
		icPlayerD = new ImageIcon("image/playerD.png");
		setIcon(icPlayerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	public void moveRight() {
		System.out.println(TAG + "moveRight");
		System.out.println("x 좌표: " + x);
		
		if (isRight == false) {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerR);
					isRight = true;
					while (isRight) {
						if(x >=50 && x <100) {
						x++;
						setLocation(x, y);
						}// 내부에 repaint()가 있음
						try {
							Thread.sleep(10);
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
						if(x >=50 && x <100) {
						x--;
						setLocation(x, y);
						}
						try {
							Thread.sleep(110);
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

		if (isUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerU);
					isUp = true;
					while (isUp) {
						
							y--;
							setLocation(x, y);
							
						try {
							Thread.sleep(5);
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

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerD);
					isDown = true;
					while (isDown) {
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void attack() {
		System.out.println(TAG + "attack");
	}

	public void moveJump() {
		System.out.println(TAG + "jump");
	}
}