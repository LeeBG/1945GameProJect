package test1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerPlane extends JLabel {

	public PlayerPlane player = this;
	public final static String TAG = "Player: ";

	public ImageIcon icPlayer;
	public int x = 55;
	public int y = 400;

	public boolean isRight = false, isLeft = false, isUP = false, isDown = false; // is 붙여라

	public PlayerPlane() {
		icPlayer = new ImageIcon("images/PLANE4.png");
		setIcon(icPlayer);
		setSize(50, 50);
		setLocation(x, y);
	}

	public void moveRight() {
		System.out.println(TAG + "moveRight()");

		if (isRight == false) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					
					isRight = true;
					while (isRight) {
						x++;
						setLocation(x, y);
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

		if (isLeft == false) {
			new Thread(new Runnable() {

				@Override
				public void run() {
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

					while (isUP) {
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
		System.out.println(TAG + "moveDown()");

		if (isDown == false) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					isDown = true;
					while (isDown) {
						y++;
						setLocation(x, y);
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



	public void attack() {

	}

}
