package test1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {

	public Player player = this;
	public final static String TAG = "Player: ";

	public ImageIcon icPlayerR, icPlayerL;
	public int x = 100;
	public int y = 535;

	public boolean isRight = false, isLeft = false, isUP = false, isDown = false; // is 붙여라

	public int floor = 1;// 535, 415, 295, 177 (1층, 2층, 3층, 4층) 나중에 enum으로

	public Player() {
		icPlayerR = new ImageIcon("images/PLANE4.png");
		icPlayerL = new ImageIcon("images/PLANE4.png");
		setIcon(icPlayerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	public void moveRight() { // 행위를 먼저넣고 뒤에 명사 통일 그래야 자동완성 편함
		System.out.println(TAG + "moveRight()");

		if (isRight == false) { // isRight가 false일 때만 시행하게 위에 걸면, 스레드 끝나면 다시 true 되니까, 일정한 속도를 유지
			new Thread(new Runnable() {

				@Override
				public void run() {
					setIcon(icPlayerR);
					isRight = true;
					while (isRight) {
						x++;
						setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();

		}

	}

	public void moveLeft() {
		System.out.println(TAG + "moveLeft()");

		if (isLeft == false) { // isRight가 false일 때만 시행하게 위에 걸면, 스레드 끝나면 다시 true 되니까, 일정한 속도를 유지
			new Thread(new Runnable() {

				@Override
				public void run() {
					setIcon(icPlayerL);
					isLeft = true;
					while (isLeft) {
						x--;
						setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

	}

	public void moveUp() {
		System.out.println(TAG + "moveUp()");

		if (isUP == false) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					isUP = true;
				
					for (int i = 0; i < 130; i++) {
						y--;
						setLocation(x, y); 
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				
					moveDown();
					

				}
			}).start();
		}
	}

	public void moveDown() {
		System.out.println(TAG + "moveDown()");

		if (isDown == false) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					isDown = true;
					while (isDown) {
						y++;
						setLocation(x, y);
						if (floor==1 &&/*x좌표*/ y > 535) {
							isDown = false;
							isUP = false;
							break;
						}
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}).start();
		}
	}

	public void moveJump() {

		// moveUp();

		// moveDown();

	}

	public void attack() {

	}

}
