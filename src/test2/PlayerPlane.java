package test2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerPlane extends JLabel {

	public PlayerPlane player = this;
	public final static String TAG = "Player: ";

	public ImageIcon icPlayer;
	private int x = 250;
	private int y = 400;
	private int playerWidth;
	private int playerHeight;
	private int life;
	
	
	public boolean isRight = false, isLeft = false, isUP = false, isDown = false; // is 붙여라

	public PlayerPlane() {
		life = 10;
		playerWidth = 80;
		playerHeight= 60;
		
		
		icPlayer = new ImageIcon("images/PLANE1.png");
		setIcon(icPlayer);
		setLocation(x, y);
		setSize(playerWidth, playerHeight);
		
	}

	

	
	public int getPlayerWidth() {
		return playerWidth;
	}




	public void setPlayerWidth(int playerWidth) {
		this.playerWidth = playerWidth;
	}



	public int getPlayerHeight() {
		return playerHeight;
	}



	public void setPlayerHeight(int playerHeight) {
		this.playerHeight = playerHeight;
	}





	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
